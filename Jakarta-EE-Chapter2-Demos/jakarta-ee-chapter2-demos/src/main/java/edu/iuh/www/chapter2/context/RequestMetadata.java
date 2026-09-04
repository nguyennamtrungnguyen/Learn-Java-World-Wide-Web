package edu.iuh.www.chapter2.context;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class RequestMetadata {
    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}

