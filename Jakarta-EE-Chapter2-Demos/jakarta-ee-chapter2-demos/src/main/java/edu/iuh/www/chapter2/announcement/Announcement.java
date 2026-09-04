package edu.iuh.www.chapter2.announcement;

import java.time.Instant;

public record Announcement(long id, String title, String content, Instant createdAt) {
}

