package org.castlefight.castlefight;

public class GameEvent {
    Long id;
    String message;

    public GameEvent() {
    }

    public GameEvent(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "GameEvent{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
