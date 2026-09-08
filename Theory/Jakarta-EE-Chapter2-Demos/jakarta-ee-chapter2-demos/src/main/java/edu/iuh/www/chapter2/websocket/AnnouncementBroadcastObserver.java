package edu.iuh.www.chapter2.websocket;

import edu.iuh.www.chapter2.announcement.Announcement;
import edu.iuh.www.chapter2.announcement.AnnouncementCreated;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.json.Json;

@ApplicationScoped
public class AnnouncementBroadcastObserver {
    @Inject
    private ConnectionRegistry registry;

    public void onAnnouncementCreated(@Observes AnnouncementCreated event) {
        Announcement announcement = event.announcement();
        String json = Json.createObjectBuilder()
                .add("type", "announcement-created")
                .add("connectedClients", registry.size())
                .add("data", Json.createObjectBuilder()
                        .add("id", announcement.id())
                        .add("title", announcement.title())
                        .add("content", announcement.content())
                        .add("createdAt", announcement.createdAt().toString()))
                .build()
                .toString();
        registry.broadcast(json);
    }
}

