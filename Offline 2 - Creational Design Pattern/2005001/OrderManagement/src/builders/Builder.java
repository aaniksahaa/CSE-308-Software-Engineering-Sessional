package builders;

import entities.ShakeVariant;
import ingredients.*;

public interface Builder {
    void setShakeVariant(ShakeVariant shakeVariant);
    void setMilk(Milk milk);
    void setSweeteningAgent(SweeteningAgent sweeteningAgent);
    void setSyrup(Syrup syrup);
    void setIceCream(IceCream iceCream);
    void setFlavor(Flavor flavor);
    void setCoffee(Coffee coffee);
    void setJello(Jello jello);
    void addTopping(Topping topping);
    void makeLactoseFree();
}
