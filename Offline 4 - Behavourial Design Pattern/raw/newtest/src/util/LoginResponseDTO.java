package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginResponseDTO implements Serializable {
    private String username;
    private Boolean status;
    List<StockDTO>allStocks = new ArrayList<>();
    List<StockDTO>subscribedStocks = new ArrayList<>();
    List<StockUpdateDTO> pendingUpdates = new ArrayList<>();

    public LoginResponseDTO(String username, Boolean status, List<StockDTO>allStocks, List<StockDTO> subscribedStocks, List<StockUpdateDTO> pendingUpdates) {
        this.username = username;
        this.status = status;
        this.allStocks = allStocks;
        this.subscribedStocks = subscribedStocks;
        this.pendingUpdates = pendingUpdates;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<StockDTO> getSubscribedStocks() {
        return subscribedStocks;
    }

    public void setSubscribedStocks(List<StockDTO> subscribedStocks) {
        this.subscribedStocks = subscribedStocks;
    }

    public List<StockUpdateDTO> getPendingUpdates() {
        return pendingUpdates;
    }

    public void setPendingUpdates(List<StockUpdateDTO> pendingUpdates) {
        this.pendingUpdates = pendingUpdates;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<StockDTO> getAllStocks() {
        return allStocks;
    }

    public void setAllStocks(List<StockDTO> allStocks) {
        this.allStocks = allStocks;
    }
}
