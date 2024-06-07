package util;

import java.io.Serializable;

public class LogoutDTO implements Serializable {
    private String username;

    public LogoutDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
