package util;

import java.io.Serializable;

public class RequestDTO implements Serializable {
    private String type;
    private String stockName;

    public RequestDTO(String type, String stockName) {
        this.type = type;
        this.stockName = stockName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
