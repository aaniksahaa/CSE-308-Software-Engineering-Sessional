package util;

import java.io.Serializable;

public class NotificationDTO implements Serializable {
    String message;

    public NotificationDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
