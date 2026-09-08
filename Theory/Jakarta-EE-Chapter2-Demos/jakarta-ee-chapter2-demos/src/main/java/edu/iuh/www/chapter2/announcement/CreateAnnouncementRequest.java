package edu.iuh.www.chapter2.announcement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAnnouncementRequest(
        @NotBlank(message = "title không được để trống")
        @Size(max = 80, message = "title tối đa 80 ký tự")
        String title,

        @NotBlank(message = "content không được để trống")
        @Size(max = 500, message = "content tối đa 500 ký tự")
        String content) {
}

