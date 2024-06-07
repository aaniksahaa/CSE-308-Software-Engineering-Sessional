package user;

import order.Order;

import java.util.ArrayList;

public class User {
    private String username;
    private ArrayList<Order> orders = new ArrayList<>();
    // can add password, email etc later

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    // package-private
    void setUsername(String username) {
        this.username = username;
    }

    public void addOrder(Order order){
        this.orders.add(order);
        //System.out.println("\n\n\nHere, adding order " + orders.size()+"\n\n\n");
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}
