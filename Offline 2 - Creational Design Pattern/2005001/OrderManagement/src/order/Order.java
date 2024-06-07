package order;

import entities.Shake;
import entities.ShakeOrder;
import user.User;

import java.util.ArrayList;

public class Order {
    private ArrayList<ShakeOrder> shakeOrderList = new ArrayList();
    Order(){

    }
    public Order(ArrayList<ShakeOrder> shakeOrderList) {
        this.shakeOrderList = shakeOrderList;
    }
    public void addShakeOrder(ShakeOrder shakeOrder){
        this.shakeOrderList.add(shakeOrder);
    }
    public int getTotalPrice(){
        int totalPrice = 0;
        for(ShakeOrder shakeOrder: shakeOrderList){
            totalPrice += shakeOrder.getTotalPrice();
        }
        return totalPrice;
    }
    public void print(){
        System.out.println("\n\nOrder Details");
        System.out.println("--------------\n");
        System.out.println("Total shakes: " + shakeOrderList.size() + "\n\n");
        int count = 0;
        for(ShakeOrder shakeOrder: shakeOrderList){
            System.out.println("Shake " + ++count );
            System.out.println("--------\n");
            System.out.println(shakeOrder.getDetails());
        }
        System.out.println("\nTotal Checkout Price: "+getTotalPrice()+"\n\n");
    }

    public ArrayList<ShakeOrder> getShakeOrderList() {
        return shakeOrderList;
    }
}
