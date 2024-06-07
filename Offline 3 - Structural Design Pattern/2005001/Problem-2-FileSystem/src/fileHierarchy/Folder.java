package fileHierarchy;

public class Folder extends Container{
    public Folder(String name, String directory){
        super(name, directory);
        this.setType("Folder");
    }
    public void addFile(String name, double size){
        this.add(new File(name, size, this.getDirectory() + "\\" + name));
    }
    public void addFolder(String name){
        this.add(new Folder(name, this.getDirectory() + "\\" + name));
    }
}
