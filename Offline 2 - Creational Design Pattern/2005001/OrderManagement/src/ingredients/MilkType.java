package ingredients;

public enum MilkType {
    REGULAR(0), ALMOND(60);
    private int priceOverhead;
    private MilkType(int priceOverhead){
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
