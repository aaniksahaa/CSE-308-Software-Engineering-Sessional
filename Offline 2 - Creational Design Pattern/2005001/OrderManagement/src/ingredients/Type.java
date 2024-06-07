package ingredients;

public enum Type {

    CHOCOLATE,
    STRAWBERRY,
    VANILLA;

    public String getDisplayName() {
        String name = name();
        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }

}
