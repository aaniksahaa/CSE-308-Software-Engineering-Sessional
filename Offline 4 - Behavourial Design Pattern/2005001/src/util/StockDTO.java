package util;

import java.io.Serializable;

public class StockDTO implements Serializable {
    private String name;
    private int count;
    private double price;

    public StockDTO(String name, int count, double price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    @Override
    public String toString() {
        //return "Stock [ name='" + name + "', count=" + count + ", price=" + price + " ]";
        return name + "\t" + count + "\t" + price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}