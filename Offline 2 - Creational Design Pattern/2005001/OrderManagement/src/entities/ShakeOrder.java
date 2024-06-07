package entities;

import ingredients.*;

import java.util.ArrayList;

public class ShakeOrder {

    private ShakeVariant shakeVariant;
    private Milk milk;
    private SweeteningAgent sweeteningAgent;
    private Syrup syrup;
    private IceCream iceCream;
    private Flavor flavor;
    private Coffee coffee;
    private Jello jello;
    private ArrayList<Topping> toppings = new ArrayList<>();

    public ShakeOrder(ShakeVariant shakeVariant, Milk milk, SweeteningAgent sweeteningAgent, Syrup syrup, IceCream iceCream, Flavor flavor, Coffee coffee, Jello jello, ArrayList<Topping> toppings) {
        this.shakeVariant = shakeVariant;
        this.milk = milk;
        this.sweeteningAgent = sweeteningAgent;
        this.syrup = syrup;
        this.iceCream = iceCream;
        this.flavor = flavor;
        this.coffee = coffee;
        this.jello = jello;
        this.toppings = new ArrayList<>(toppings);
    }

    public int getTotalPrice(){
        int price = shakeVariant.getBasePrice();
        price += milk.getMilkType().getPriceOverhead();
        for(Topping topping: toppings){
            price += topping.getPriceOverhead();
        }
        return price;
    }

    public String getDetails(){
        String details = "";
        details += "Name: " + shakeVariant.getDisplayName() + " Shake\n";
        details += "\nBase Ingredients: \n\n";
        int count = 0;
        if(milk != null){
            details += ++count + ". " + milk.getMilkType().getDisplayName() + " Milk\n";
        }
        if(sweeteningAgent != null){
            details += ++count + ". " + sweeteningAgent.getDisplayName() + "\n";
        }
        if(syrup != null){
            details += ++count + ". " + syrup.getType().getDisplayName() + " Syrup\n";
        }
        if(iceCream != null){
            details += ++count + ". " + iceCream.getType().getDisplayName() + " IceCream\n";
        }
        if(flavor != null){
            details += ++count + ". " + flavor.getType().getDisplayName() + " Flavoring\n";
        }
        if(coffee != null){
            details += ++count + ". " + "Coffee\n";
        }
        if(jello != null){
            details += ++count + ". " + (jello.getSugarFree() ? "Suger-free " : "") + "Jello\n";
        }

        details += "\nAdditional Ingredients: \n\n";
        if(toppings.size() == 0){
            details += "No additional ingredient found\n";
        }
        else{
            count = 0;
            for(Topping topping: toppings){
                details += ++count + ". " + topping.getDisplayName() + "\n";
            }
        }
        details += "\nShake Price: " + getTotalPrice() + "\n\n";
        int milkPriceOverhead = milk.getMilkType().getPriceOverhead();
        if(milkPriceOverhead > 0){
            details += "Price increased by " + milkPriceOverhead + " for making milk lactose-free\n";
        }
        for(Topping topping: toppings){
            details += "Price increased by " + topping.getPriceOverhead() + " for adding " + topping.getDisplayName() + " on top\n";
        }

        return details;
    }
    void print(){
        System.out.println(getDetails());
    }
}
