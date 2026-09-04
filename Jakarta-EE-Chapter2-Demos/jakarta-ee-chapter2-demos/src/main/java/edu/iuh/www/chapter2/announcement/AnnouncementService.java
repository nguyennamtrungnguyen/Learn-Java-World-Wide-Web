package edu.iuh.www.chapter2.announcement;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class AnnouncementService {
    private final ConcurrentHashMap<Long, Announcement> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @Inject
    private Event<AnnouncementCreated> announcementCreated;

    @PostConstruct
    void seedData() {
        long id = sequence.incrementAndGet();
        store.put(id, new Announcement(id, "Chào mừng", "Demo Jakarta EE 11 đã sẵn sàng.", Instant.now()));
    }

    public List<Announcement> findAll() {
        return store.values().stream()
                .sorted((left, right) -> Long.compare(right.id(), left.id()))
                .toList();
    }

    public Announcement findById(long id) {
        Announcement announcement = store.get(id);
        if (announcement == null) {
            throw new AnnouncementNotFoundException(id);
        }
        return announcement;
    }

    public Announcement create(CreateAnnouncementRequest request) {
        long id = sequence.incrementAndGet();
        Announcement announcement = new Announcement(
                id,
                request.title().strip(),
                request.content().strip(),
                Instant.now());
        store.put(id, announcement);
        announcementCreated.fire(new AnnouncementCreated(announcement));
        return announcement;
    }
}

