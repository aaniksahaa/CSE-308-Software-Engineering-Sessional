package ingredients;

import javax.lang.model.element.TypeElement;

public class IceCream {
    private Type type;

    public IceCream(Type type){
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
