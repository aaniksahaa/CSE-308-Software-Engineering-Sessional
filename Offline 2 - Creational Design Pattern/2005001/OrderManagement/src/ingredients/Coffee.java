package ingredients;

public class Coffee {

    // storing manufacturer because
    // it is unusual for a Shake company to manufacture
    // coffee on its own
    // so using other manufacturer's product makes more sense

    private String manufacturer;
    public Coffee(){
        // keeping dummy default values
        this.manufacturer = "Nescafe";
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
