package ingredients;

public enum Topping {
    CANDY(50), COOKIES(40);
    private int priceOverhead;
    private Topping(int priceOverhead){
        this.priceOverhead = priceOverhead;
    }

    public int getPriceOverhead() {
        return priceOverhead;
    }

    public String getDisplayName() {
        String name = name();
        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
