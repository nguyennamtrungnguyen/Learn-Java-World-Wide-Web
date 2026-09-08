package edu.iuh.www.chapter2.filter;

import edu.iuh.www.chapter2.context.RequestMetadata;
import jakarta.inject.Inject;
import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@WebFilter(urlPatterns = "/*", asyncSupported = true)
public class CorrelationFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(CorrelationFilter.class.getName());

    @Inject
    private RequestMetadata requestMetadata;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestId = normalizeRequestId(httpRequest.getHeader("X-Correlation-Id"));
        long startedAt = System.nanoTime();

        requestMetadata.setRequestId(requestId);
        httpRequest.setAttribute("requestId", requestId);
        httpResponse.setHeader("X-Correlation-Id", requestId);

        try {
            chain.doFilter(request, response);
        } finally {
            if (httpRequest.isAsyncStarted()) {
                try {
                    httpRequest.getAsyncContext().addListener(new AsyncListener() {
                        @Override
                        public void onComplete(AsyncEvent event) {
                            logDuration(httpRequest, requestId, startedAt, "async-complete");
                        }

                        @Override
                        public void onTimeout(AsyncEvent event) {
                            logDuration(httpRequest, requestId, startedAt, "async-timeout");
                        }

                        @Override
                        public void onError(AsyncEvent event) {
                            logDuration(httpRequest, requestId, startedAt, "async-error");
                        }

                        @Override
                        public void onStartAsync(AsyncEvent event) {
                            // Không cần xử lý trong demo một chu kỳ async duy nhất.
                        }
                    });
                } catch (IllegalStateException alreadyCompleted) {
                    logDuration(httpRequest, requestId, startedAt, "async-already-completed");
                }
            } else {
                logDuration(httpRequest, requestId, startedAt, "complete");
            }
        }
    }

    private String normalizeRequestId(String candidate) {
        if (candidate == null || candidate.isBlank() || candidate.length() > 100) {
            return UUID.randomUUID().toString();
        }
        return candidate.replaceAll("[^A-Za-z0-9._-]", "_");
    }

    private void logDuration(HttpServletRequest request, String requestId, long startedAt, String phase) {
        long durationMs = (System.nanoTime() - startedAt) / 1_000_000;
        LOGGER.info(() -> "%s %s requestId=%s durationMs=%d phase=%s"
                .formatted(request.getMethod(), request.getRequestURI(), requestId, durationMs, phase));
    }
}

