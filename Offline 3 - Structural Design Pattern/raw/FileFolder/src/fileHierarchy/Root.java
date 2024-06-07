package fileHierarchy;

import java.util.ArrayList;

public class Root extends Container{
    public Root(){
        super("root","\\");
    }
    public void addDrive(String name){
        this.add(new Drive(name, name));
    }
}
