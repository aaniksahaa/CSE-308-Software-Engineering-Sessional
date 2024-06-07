package util;

import java.io.Serializable;

public class RequestResponseDTO implements Serializable {
    private String type;
    private String message;
    private StockDTO stock;
    private Boolean status;

    public RequestResponseDTO(String type, String message, StockDTO stock, Boolean status) {
        this.type = type;
        this.message = message;
        this.stock = stock;
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StockDTO getStock() {
        return stock;
    }

    public void setStock(StockDTO stock) {
        this.stock = stock;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
