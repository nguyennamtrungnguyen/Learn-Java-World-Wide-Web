package edu.iuh.www.chapter2.servlet;

import edu.iuh.www.chapter2.listener.ApplicationLifecycleListener;
import edu.iuh.www.chapter2.listener.SessionLifecycleListener;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/demo/servlet/runtime", loadOnStartup = 1)
public class RuntimeServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RuntimeServlet.class.getName());
    private final String servletInstanceId = UUID.randomUUID().toString();
    private final AtomicLong servletRequestCount = new AtomicLong();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        LOGGER.info(() -> "init RuntimeServlet instanceId=" + servletInstanceId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long servletCount = servletRequestCount.incrementAndGet();
        AtomicInteger sessionCount = getSessionCounter(request.getSession(true));
        long currentSessionCount = sessionCount.incrementAndGet();
        AtomicLong applicationCounter = (AtomicLong) getServletContext()
                .getAttribute(ApplicationLifecycleListener.APPLICATION_REQUEST_COUNT);
        long applicationCount = applicationCounter.incrementAndGet();

        JsonObject body = Json.createObjectBuilder()
                .add("demo", "Servlet lifecycle và sharing information")
                .add("timestamp", Instant.now().toString())
                .add("requestId", String.valueOf(request.getAttribute("requestId")))
                .add("thread", Thread.currentThread().getName())
                .add("servletInstanceId", servletInstanceId)
                .add("servletRequestCount", servletCount)
                .add("sessionId", request.getSession().getId())
                .add("sessionRequestCount", currentSessionCount)
                .add("applicationRequestCount", applicationCount)
                .add("activeSessions", SessionLifecycleListener.activeSessions())
                .add("note", "Atomic counters được dùng vì servlet instance và application state có thể được nhiều request dùng chung.")
                .build();

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(body.toString());
    }

    private AtomicInteger getSessionCounter(HttpSession session) {
        synchronized (session) {
            AtomicInteger counter = (AtomicInteger) session.getAttribute("sessionRequestCount");
            if (counter == null) {
                counter = new AtomicInteger();
                session.setAttribute("sessionRequestCount", counter);
            }
            return counter;
        }
    }

    @Override
    public void destroy() {
        LOGGER.info(() -> "destroy RuntimeServlet instanceId=" + servletInstanceId);
    }
}

