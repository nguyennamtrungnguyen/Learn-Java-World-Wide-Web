package edu.iuh.www.chapter2.servlet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;

@WebServlet(urlPatterns = "/demo/async/report", asyncSupported = true)
public class AsyncReportServlet extends HttpServlet {
    private static final long ASYNC_TIMEOUT_MS = 5_000;
    private static final long MAX_DELAY_MS = 4_500;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long delayMs;
        try {
            delayMs = parseDelay(request.getParameter("delayMs"));
        } catch (IllegalArgumentException invalidDelay) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, invalidDelay.getMessage());
            return;
        }

        String requestId = String.valueOf(request.getAttribute("requestId"));
        String entryThread = Thread.currentThread().getName();
        long startedAt = System.nanoTime();

        AsyncContext async = request.startAsync();
        async.setTimeout(ASYNC_TIMEOUT_MS);
        async.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) {
                // CorrelationFilter ghi tổng thời gian khi async hoàn tất.
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                HttpServletResponse asyncResponse = (HttpServletResponse) event.getAsyncContext().getResponse();
                if (!asyncResponse.isCommitted()) {
                    asyncResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                    asyncResponse.setContentType("application/json;charset=UTF-8");
                    asyncResponse.getWriter().write(Json.createObjectBuilder()
                            .add("status", 503)
                            .add("title", "Async request timed out")
                            .add("requestId", requestId)
                            .build().toString());
                }
                event.getAsyncContext().complete();
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                HttpServletResponse asyncResponse = (HttpServletResponse) event.getAsyncContext().getResponse();
                if (!asyncResponse.isCommitted()) {
                    asyncResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }

            @Override
            public void onStartAsync(AsyncEvent event) {
                // Không dùng dispatch lặp trong demo này.
            }
        });

        async.start(() -> {
            try {
                Thread.sleep(delayMs); // Chỉ mô phỏng I/O chậm; không dùng trong mã sản xuất.
                long durationMs = (System.nanoTime() - startedAt) / 1_000_000;
                HttpServletResponse asyncResponse = (HttpServletResponse) async.getResponse();
                JsonObject body = Json.createObjectBuilder()
                        .add("demo", "Servlet asynchronous processing")
                        .add("timestamp", Instant.now().toString())
                        .add("requestId", requestId)
                        .add("delayMs", delayMs)
                        .add("durationMs", durationMs)
                        .add("entryThread", entryThread)
                        .add("workerThread", Thread.currentThread().getName())
                        .add("note", "Công việc chạy bằng thread do container quản lý qua AsyncContext.start().")
                        .build();
                asyncResponse.setContentType("application/json;charset=UTF-8");
                asyncResponse.getWriter().write(body.toString());
                async.complete();
            } catch (InterruptedException interrupted) {
                Thread.currentThread().interrupt();
                async.complete();
            } catch (IOException | IllegalStateException completedOrDisconnected) {
                async.complete();
            }
        });
    }

    private long parseDelay(String value) {
        if (value == null || value.isBlank()) {
            return 1_500;
        }
        try {
            long parsed = Long.parseLong(value);
            if (parsed < 0 || parsed > MAX_DELAY_MS) {
                throw new IllegalArgumentException("delayMs phải nằm trong khoảng 0..4500");
            }
            return parsed;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("delayMs phải là số nguyên");
        }
    }
}

