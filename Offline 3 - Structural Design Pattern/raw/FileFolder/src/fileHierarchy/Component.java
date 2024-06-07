package fileHierarchy;

import util.DateTime;

public abstract class Component {
    private String name;
    private String type;
    private DateTime creationTime;
    private String directory;

    public Component(String name, String directory) {
        this.name = name;
        this.creationTime = new DateTime();
        this.directory = directory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(DateTime creationTime) {
        this.creationTime = creationTime;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getType() {
        return type;
    }

    protected void setType(String type) {
        this.type = type;
    }

    abstract public double calculateSize();

    abstract public int getComponentCount();

    public String getDetails(){
        String details = "";
        details += "Name: " + this.name + "\n";
        details += "Type: " + this.type + "\n";
        // may change to MB, GB etc...
        details += "Size: " + this.calculateSize() + "kB" + "\n";
        details += "Directory: \"" + this.directory + "\"" + "\n";
        details += "Component Count: " + this.getComponentCount() + "\n";
        details += "Creation time: " + this.creationTime.getString(1) + "\n";
        return details;
    }

    public String getShortDetails(){
        return this.name + "\t\t" + this.calculateSize() + "kB" + "\t\t" + this.creationTime.getString(2);
    }
}
