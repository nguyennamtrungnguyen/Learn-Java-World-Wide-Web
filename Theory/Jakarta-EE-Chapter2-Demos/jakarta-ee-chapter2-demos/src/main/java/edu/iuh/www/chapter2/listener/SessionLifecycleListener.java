package edu.iuh.www.chapter2.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@WebListener
public class SessionLifecycleListener implements HttpSessionListener {
    private static final Logger LOGGER = Logger.getLogger(SessionLifecycleListener.class.getName());
    private static final AtomicInteger ACTIVE_SESSIONS = new AtomicInteger();

    public static int activeSessions() {
        return ACTIVE_SESSIONS.get();
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        int active = ACTIVE_SESSIONS.incrementAndGet();
        LOGGER.info(() -> "Tạo HTTP session; activeSessions=" + active);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        int active = ACTIVE_SESSIONS.decrementAndGet();
        LOGGER.info(() -> "Hủy HTTP session; activeSessions=" + active);
    }
}

