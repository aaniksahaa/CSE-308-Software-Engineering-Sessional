import commandShell.Shell;

public class Main {
    public static void main(String[] args) throws Exception{

        boolean fileInput = true;
        String filepath = "src/input.txt";

        Shell shell = new Shell();
        shell.run(fileInput, filepath);
    }
}