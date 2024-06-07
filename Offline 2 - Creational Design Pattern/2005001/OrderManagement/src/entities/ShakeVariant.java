package entities;

public enum ShakeVariant {

    CHOCOLATE(230),
    COFFEE(250),
    STRAWBERRY(200),
    VANILLA(190),
    ZERO(240);

    private final int basePrice;
    private ShakeVariant(int basePrice){
        this.basePrice = basePrice;
    }

    public String getDisplayName() {
        String name = name();
        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public int getBasePrice(){
        return basePrice;
    }



}
