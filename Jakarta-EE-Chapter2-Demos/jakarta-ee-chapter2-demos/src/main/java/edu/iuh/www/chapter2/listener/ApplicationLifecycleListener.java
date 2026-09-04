package edu.iuh.www.chapter2.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@WebListener
public class ApplicationLifecycleListener implements ServletContextListener {
    public static final String APPLICATION_REQUEST_COUNT = "applicationRequestCount";
    private static final Logger LOGGER = Logger.getLogger(ApplicationLifecycleListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute(APPLICATION_REQUEST_COUNT, new AtomicLong());
        LOGGER.info("Ứng dụng chapter2-demos đã khởi tạo");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        LOGGER.info("Ứng dụng chapter2-demos đang dừng");
    }
}

