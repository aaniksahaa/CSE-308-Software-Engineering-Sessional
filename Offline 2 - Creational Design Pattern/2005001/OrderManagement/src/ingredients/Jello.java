package ingredients;

public class Jello {

    // storing manufacturer because
    // it is unusual for a Shake company to manufacture
    // jello on its own
    // so using other manufacturer's product makes more sense

    private Boolean sugarFree;
    private String manufacturer;
    public Jello(Boolean sugarFree){
        this.sugarFree = sugarFree;
        // keeping dummy default values
        this.manufacturer = "Knox";
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Boolean getSugarFree() {
        return sugarFree;
    }

    public void setSugarFree(Boolean sugarFree) {
        this.sugarFree = sugarFree;
    }
}
