package edu.iuh.www.chapter2.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@ApplicationScoped
public class DefaultGreetingService implements GreetingService {
    private static final Logger LOGGER = Logger.getLogger(DefaultGreetingService.class.getName());
    private final String instanceId = UUID.randomUUID().toString();
    private final AtomicLong invocationCount = new AtomicLong();

    @PostConstruct
    void initialize() {
        LOGGER.info(() -> "CDI @PostConstruct GreetingService instanceId=" + instanceId);
    }

    @Override
    public String greet(String name) {
        long callNumber = invocationCount.incrementAndGet();
        return "Xin chào %s! Đây là lần gọi dịch vụ thứ %d.".formatted(name, callNumber);
    }

    @Override
    public String instanceId() {
        return instanceId;
    }

    @PreDestroy
    void shutdown() {
        LOGGER.info(() -> "CDI @PreDestroy GreetingService instanceId=" + instanceId);
    }
}

