package fileHierarchy;

import util.DateTime;

import java.util.ArrayList;

abstract public class Container extends Component{
    private ArrayList<Component>components;

    public Container(String name, String directory) {
        super(name, directory);
        components = new ArrayList<>();
    }

    @Override
    public double calculateSize() {
        double totalSize = 0.0;
        for(Component c: components){
            totalSize += c.calculateSize();
        }
        return totalSize;
    }

    @Override
    public int getComponentCount() {
        return components.size();
    }
    // protected so that, unwanted hierarchy(eg. drive inside folder) does not get created
    protected void add(Component c){
        components.add(c);
    }
    public String getList(){
        String list = "";
        for(Component c: components){
            list += c.getShortDetails() + "\n";
        }
        return list;
    }
    public Component findComponent(String name){
        for(Component c: components){
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }
    public void deleteComponent(Component c){
        components.remove(c);
    }
    public void deleteComponentRecursive(Component c){
        if(c instanceof Container){
            ((Container) c).deleteAll();
        }
        components.remove(c);
    }
    public void deleteAll(){
        for(Component c: components){
            if(c instanceof Container){
                ((Container) c).deleteAll();
            }
        }
        components.clear();
    }
}
