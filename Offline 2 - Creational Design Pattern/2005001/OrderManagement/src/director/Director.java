package director;

import builders.Builder;
import entities.ShakeVariant;
import ingredients.*;

public class Director {
    public void constructChocolateShake(Builder builder){
        builder.setShakeVariant(ShakeVariant.CHOCOLATE);
        builder.setMilk(new Milk(MilkType.REGULAR));
        builder.setSweeteningAgent(SweeteningAgent.SUGER);
        builder.setSyrup(new Syrup(Type.CHOCOLATE));
        builder.setIceCream(new IceCream(Type.CHOCOLATE));
    }
    public void constructCoffeeShake(Builder builder){
        builder.setShakeVariant(ShakeVariant.COFFEE);
        builder.setMilk(new Milk(MilkType.REGULAR));
        builder.setSweeteningAgent(SweeteningAgent.SUGER);
        builder.setCoffee(new Coffee());
        builder.setJello(new Jello(false));
    }
    public void constructStrawberryShake(Builder builder){
        builder.setShakeVariant(ShakeVariant.STRAWBERRY);
        builder.setMilk(new Milk(MilkType.REGULAR));
        builder.setSweeteningAgent(SweeteningAgent.SUGER);
        builder.setSyrup(new Syrup(Type.STRAWBERRY));
        builder.setIceCream(new IceCream(Type.STRAWBERRY));
    }
    public void constructVanillaShake(Builder builder){
        builder.setShakeVariant(ShakeVariant.VANILLA);
        builder.setMilk(new Milk(MilkType.REGULAR));
        builder.setSweeteningAgent(SweeteningAgent.SUGER);
        builder.setFlavor(new Flavor(Type.VANILLA));
        builder.setJello(new Jello(false));
    }
    public void constructZeroShake(Builder builder){
        builder.setShakeVariant(ShakeVariant.ZERO);
        builder.setMilk(new Milk(MilkType.REGULAR));
        builder.setSweeteningAgent(SweeteningAgent.SWEETENER);
        builder.setFlavor(new Flavor(Type.VANILLA));
        builder.setJello(new Jello(true));
    }
}
