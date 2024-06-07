package ingredients;

public class Milk {
    private MilkType milkType;
    public Milk(MilkType milkType){
        this.milkType = milkType;
    }
    public void setMilkType(MilkType milkType) {
        this.milkType = milkType;
    }
    public MilkType getMilkType() {
        return milkType;
    }
}
