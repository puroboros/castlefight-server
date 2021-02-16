package org.castlefight.castlefight.model;

public class UserResponse {
    private String method;
    private Object content;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public UserResponse(String method, Object content) {
        this.method = method;
        this.content = content;
    }
}
