package edu.iuh.www.chapter2.announcement;

public class AnnouncementNotFoundException extends RuntimeException {
    public AnnouncementNotFoundException(long id) {
        super("Không tìm thấy thông báo có id=" + id);
    }
}

