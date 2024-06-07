package util;

import java.io.Serializable;

public class StockUpdateDTO implements Serializable {
    private String stockName;
    private int updatedCount;
    private double updatedPrice;
    private DateTime dateTime;

    public StockUpdateDTO(String stockName, int updatedCount, double updatedPrice) {
        this.dateTime = new DateTime();
        this.stockName = stockName;
        this.updatedCount = updatedCount;
        this.updatedPrice = updatedPrice;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public int getUpdatedCount() {
        return updatedCount;
    }

    public void setUpdatedCount(int updatedCount) {
        this.updatedCount = updatedCount;
    }

    public double getUpdatedPrice() {
        return updatedPrice;
    }

    public void setUpdatedPrice(double updatedPrice) {
        this.updatedPrice = updatedPrice;
    }

    public String toString(){
        return dateTime + ": Stock " + stockName + " is updated. Updated Count: " + updatedCount + ", Updated Price: " + updatedPrice;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }
}
