package edu.iuh.www.chapter2.error;

import java.util.List;

public record ApiProblem(
        int status,
        String title,
        String detail,
        String instance,
        String code,
        String requestId,
        List<String> violations) {
}

