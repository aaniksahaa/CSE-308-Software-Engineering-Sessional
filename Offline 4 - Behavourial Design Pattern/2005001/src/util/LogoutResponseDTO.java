package util;

import java.io.Serializable;

public class LogoutResponseDTO implements Serializable {
    private Boolean status;

    public LogoutResponseDTO(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
