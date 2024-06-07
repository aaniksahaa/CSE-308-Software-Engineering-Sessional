package entities;

import ingredients.*;

import java.util.ArrayList;

public class Shake {

    private final ShakeVariant shakeVariant;
    private final Milk milk;
    private final SweeteningAgent sweeteningAgent;
    private final Syrup syrup;
    private final IceCream iceCream;
    private final Flavor flavor;
    private final Coffee coffee;
    private Jello jello;
    private ArrayList<Topping> toppings = new ArrayList<>();

    public Shake(ShakeVariant shakeVariant, Milk milk, SweeteningAgent sweeteningAgent, Syrup syrup, IceCream iceCream, Flavor flavor, Coffee coffee, Jello jello, ArrayList<Topping> toppings) {
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

    public ShakeVariant getShakeVariant() {
        return shakeVariant;
    }

    public Milk getMilk() {
        return milk;
    }

    public SweeteningAgent getSweeteningAgent() {
        return sweeteningAgent;
    }

    public Syrup getSyrup() {
        return syrup;
    }

    public IceCream getIceCream() {
        return iceCream;
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public Jello getJello() {
        return jello;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }
}
