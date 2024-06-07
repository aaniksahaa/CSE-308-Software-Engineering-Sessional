package fileHierarchy;

public class Drive extends Container{
    public Drive(String name, String directory){
        super(name, directory);
        this.setType("Drive");
    }
    public void addFile(String name, double size){
        this.add(new File(name, size, this.getDirectory() + "\\" + name));
    }
    public void addFolder(String name){
        this.add(new Folder(name, this.getDirectory() + "\\" + name));
    }
}
