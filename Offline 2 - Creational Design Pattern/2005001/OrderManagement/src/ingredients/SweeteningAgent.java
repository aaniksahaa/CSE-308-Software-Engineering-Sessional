package ingredients;

public enum SweeteningAgent {
    SUGER, SWEETENER;
    public String getDisplayName() {
        String name = name();
        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }
}