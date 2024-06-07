package builders;

import entities.Shake;
import entities.ShakeOrder;
import entities.ShakeVariant;
import ingredients.*;

import java.util.ArrayList;

public class ShakeOrderBuilder implements Builder{

    private ShakeVariant shakeVariant;
    private Milk milk;
    private SweeteningAgent sweeteningAgent;
    private Syrup syrup;
    private IceCream iceCream;
    private Flavor flavor;
    private Coffee coffee;
    private Jello jello;
    private ArrayList<Topping> toppings = new ArrayList<>();
    @Override
    public void setShakeVariant(ShakeVariant shakeVariant) {
        this.shakeVariant = shakeVariant;
    }
    @Override
    public void setMilk(Milk milk) {
        this.milk = milk;
    }
    @Override
    public void setSweeteningAgent(SweeteningAgent sweeteningAgent) {
        this.sweeteningAgent = sweeteningAgent;
    }
    @Override
    public void setSyrup(Syrup syrup) {
        this.syrup = syrup;
    }
    @Override
    public void setIceCream(IceCream iceCream) {
        this.iceCream = iceCream;
    }
    @Override
    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }
    @Override
    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }
    @Override
    public void setJello(Jello jello) {
        this.jello = jello;
    }
    @Override
    public void addTopping(Topping topping){
        this.toppings.add(topping);
    }

    @Override
    public void makeLactoseFree(){
        milk.setMilkType(MilkType.ALMOND);
    }

    public ShakeOrder getResult(){
        return new ShakeOrder(shakeVariant, milk, sweeteningAgent, syrup, iceCream, flavor, coffee, jello, toppings);
    }

}

