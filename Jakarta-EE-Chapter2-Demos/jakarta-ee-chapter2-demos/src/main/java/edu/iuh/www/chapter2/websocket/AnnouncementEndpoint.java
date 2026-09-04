package edu.iuh.www.chapter2.websocket;

import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws/announcements")
public class AnnouncementEndpoint {
    @Inject
    private ConnectionRegistry registry;

    @OnOpen
    public void onOpen(Session session) {
        registry.add(session);
        session.getAsyncRemote().sendText(Json.createObjectBuilder()
                .add("type", "connected")
                .add("sessionId", session.getId())
                .add("connectedClients", registry.size())
                .build().toString());
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        return Json.createObjectBuilder()
                .add("type", "echo")
                .add("sessionId", session.getId())
                .add("message", message)
                .build().toString();
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        registry.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        if (session != null) {
            registry.remove(session);
        }
    }
}

