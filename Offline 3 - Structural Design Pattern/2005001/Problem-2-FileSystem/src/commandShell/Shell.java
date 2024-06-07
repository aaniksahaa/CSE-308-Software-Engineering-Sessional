package commandShell;

import fileHierarchy.*;

import java.util.*;

public class Shell {
    private Container currentDirectory;

    // moving to parent directory - added for convenience
    private Stack<Container> previousDirectories;
    private static Root root;
    Map<String, Integer> expectedArgumentCount;
    public Shell(){
        root = new Root();
        currentDirectory = root;
        previousDirectories = new Stack<>();
        expectedArgumentCount = new HashMap<>(Map.of(
                "cd", 1,
                "ls",1,
                "list", 0,
                "mkdir", 1,
                "touch",2,
                "mkdrive",1
        ));
    }
    public void run(boolean fileInput, String filepath) throws Exception{

        Scanner in;
        java.io.File inputFile;
        inputFile = new java.io.File(filepath);
        if(fileInput){
            in = new Scanner(inputFile);
        }else {
            in = new Scanner(System.in);
        }
        System.out.println("\nWelcome to Command Shell !\n");
        while(true){
            System.out.print(currentDirectory.getDirectory() + "> ");
            String inputLine = "";
            if(in.hasNextLine()){
                inputLine = in.nextLine();
            }
            else{
                break;
            }
            if(fileInput){
                System.out.println(inputLine);
            }
            if (inputLine.isEmpty() || inputLine.equals("exit")) {
                break;
            }
            // Splitting the input line into words
            String[] words = inputLine.split("\\s+");
            ArrayList<String> arguments = new ArrayList<>();
            String command = words[0];
            for (int i = 1; i < words.length; i++) {
                arguments.add(words[i]);
            }
            process(command, arguments);

        }
    }
    private void process(String command, ArrayList<String>arguments){

        if(expectedArgumentCount.containsKey(command) && expectedArgumentCount.get(command) != arguments.size()){
            System.out.println("Error! " + command + " expects " + expectedArgumentCount.get(command) + " arguments, but " + arguments.size() + " were given.");
            return;
        }

        if(command.equals("cd")){
            String name = arguments.get(0);
            if(name.equals("~")){
                currentDirectory = root;
                return;
            } else if (name.equals("..")) {
                if(previousDirectories.empty()){
                    currentDirectory = root;
                }else {
                    currentDirectory = previousDirectories.peek();
                    previousDirectories.pop();
                }
                return;
            }
            name = removeLastBackslash(name);
            Component c = currentDirectory.findComponent(name);
            if(c == null){
                System.out.println("Error! No such folder or drive exists in the current directory.");
            } else if (c instanceof File) {
                System.out.println("Error! Cannot change directory to a file. Please provide the name of a valid folder or drive.");
            } else {
                // downcasting to Container
                previousDirectories.push(currentDirectory);
                currentDirectory = (Container) c;
            }
        } else if (command.equals("ls")) {
            String name = arguments.get(0);
            Component c = currentDirectory.findComponent(name);
            if(c == null){
                System.out.println("Error! No such file, folder or drive exists in the current directory.");
            } else {
                String details = c.getDetails();
                System.out.print(details);
            }
        } else if (command.equals("list")) {
            System.out.print(currentDirectory.getList());
        }
        else if (command.equals("mkdir")) {
            String name = arguments.get(0);
            if(currentDirectory.findComponent(name) != null){
                System.out.println("Sorry! A component with the same name already exists in the current directory.");
                return;
            }
            if(currentDirectory instanceof Drive){
                Drive d = (Drive) currentDirectory;
                d.addFolder(name);
            } else if (currentDirectory instanceof Folder) {
                Folder f = (Folder) currentDirectory;
                f.addFolder(name);
            } else {
                System.out.println("Error! Can only make directory inside a folder or drive.");
            }
        }
        else if (command.equals("touch")) {
            String name = arguments.get(0);
            double size = Double.parseDouble(arguments.get(1));
            if(currentDirectory instanceof Drive){
                Drive d = (Drive) currentDirectory;
                d.addFile(name,size);
            } else if (currentDirectory instanceof Folder) {
                Folder f = (Folder) currentDirectory;
                f.addFile(name,size);
            } else {
                System.out.println("Error! Can only touch file inside a folder or drive.");
            }
        }
        else if (command.equals("mkdrive")) {
            String name = arguments.get(0);
            name = name + ":";
            if(root.findComponent(name) != null){
                System.out.println("Sorry! A drive with the same name already exists in the root.");
                return;
            }
            root.addDrive(name);
        } else if (command.equals("delete")) {
            if(arguments.get(0).equals("-r")){
                String name = arguments.get(1);
                name = removeLastBackslash(name);
                Component c = currentDirectory.findComponent(name);
                if(c == null){
                    System.out.println("Error! No such folder or drive exists in the current directory.");
                }else if(c instanceof File) {
                    currentDirectory.deleteComponentRecursive(c);
                    System.out.println("Warning! File deleted. Recursive delete not required.");
                } else {
                    currentDirectory.deleteComponentRecursive(c);
                    System.out.println(c.getType() + " " + name + " recursively deleted.");
                }
            } else {
                String name = arguments.get(0);
                name = removeLastBackslash(name);
                Component c = currentDirectory.findComponent(name);
                if(c == null){
                    System.out.println("Error! No such folder or drive exists in the current directory.");
                } else if (c instanceof File) {
                    currentDirectory.deleteComponent(c);
                    System.out.println(c.getType() + " " + name + " successfully deleted.");
                } else {
                    if(c.getComponentCount() == 0){
                        currentDirectory.deleteComponent(c);
                        System.out.println(c.getType() + " " + name + " successfully deleted.");
                    } else {
                        System.out.println("Sorry! Cannot delete a non-empty folder or drive.");
                    }
                }
            }
        }
    }
    private String removeLastBackslash(String s){
        String ret = s;
        if(ret.endsWith("\\")){
            ret = ret.substring(0,ret.length()-1);
        }
        return ret;
    }
}
