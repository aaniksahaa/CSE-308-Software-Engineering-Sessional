package fileHierarchy;

import util.DateTime;

public class File extends Component{
    private double size; // in kB

    public File(String name, double size, String directory) {
        super(name, directory);
        this.size = size;
        this.setType("File");
    }

    @Override
    public double calculateSize() {
        return size;
    }

    @Override
    public int getComponentCount() {
        return 0;  // because there are no components inside a file
    }
}
