package edu.iuh.www.chapter2.websocket;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.Session;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class ConnectionRegistry {
    private final Set<Session> sessions = ConcurrentHashMap.newKeySet();

    public void add(Session session) {
        sessions.add(session);
    }

    public void remove(Session session) {
        sessions.remove(session);
    }

    public int size() {
        return sessions.size();
    }

    public void broadcast(String message) {
        sessions.stream()
                .filter(Session::isOpen)
                .forEach(session -> session.getAsyncRemote().sendText(message, result -> {
                    if (!result.isOK()) {
                        sessions.remove(session);
                    }
                }));
    }
}

