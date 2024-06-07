import spaceship.Crewmate;
import spaceship.ImposterDecorator;
import spaceship.Passenger;
import spaceship.SpaceShip;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static SpaceShip spaceShip;
    private static Passenger currentLoggedinPassenger;
    private static String wrongNumberOfArgumentMessage = "Sorry! Wrong number of arguments";
    public static void addCrewmate(String name){
        Passenger crewmate = new Crewmate(name);
        spaceShip.addPassenger(name, crewmate);
    }
    public static void addImposter(String name){
        Passenger imposter = new ImposterDecorator(new Crewmate(name));
        spaceShip.addPassenger(name, imposter);
    }

    public static void initialize(int initialCrewmates, int initialImposters){
        for(int i=1; i<= initialCrewmates; i++){
            addCrewmate("crew" + i);
        }
        for(int i=1; i<= initialImposters; i++){
            addImposter("imp" + i);
        }
        currentLoggedinPassenger = null;
    }

    public static void main(String[] args) throws Exception {
        spaceShip = new SpaceShip("Falcon 22B");
        initialize(5,5);
        run();
    }
    public static void run() throws Exception{

        boolean fileInput = true;
        Scanner in;
        java.io.File inputFile;
        String filepath = "src/input.txt";
        inputFile = new java.io.File(filepath);
        if(fileInput){
            in = new Scanner(inputFile);
        }else {
            in = new Scanner(System.in);
        }
        while(in.hasNextLine()){
            String inputLine = in.nextLine();
            if(fileInput){
                System.out.println(inputLine);
            }
            // Splitting the input line into words
            String[] words = inputLine.split("\\s+");

            ArrayList<String> arguments = new ArrayList<>();

            String command = words[0];

            for (int i = 1; i < words.length; i++) {
                arguments.add(words[i]);
            }

            if(command.equals("exit")){
                break;
            }

            process(command, arguments);

        }
    }

    public static void process(String command, ArrayList<String>arguments){
        if(command.equals("login")) {
            if(arguments.size() != 1){
                System.out.println(wrongNumberOfArgumentMessage);
                return;
            }
            if(currentLoggedinPassenger != null){
                System.out.println("Sorry! Please logout first.");
                return;
            }
            String name = arguments.get(0);
            Passenger p = spaceShip.authenticate(name);
            if(p != null){
                p.login();
                currentLoggedinPassenger = p;
            }
            else {
                System.out.println("Sorry! No such passenger is onboard.");
            }
        } else if (command.equals("repair")) {
            if(arguments.size() != 0){
                System.out.println(wrongNumberOfArgumentMessage);
                return;
            }
            if(currentLoggedinPassenger == null){
                System.out.println("Sorry! Please login first.");
            }
            else {
                currentLoggedinPassenger.repair();
            }
        } else if (command.equals("work")) {
            if(arguments.size() != 0){
                System.out.println(wrongNumberOfArgumentMessage);
                return;
            }
            if(currentLoggedinPassenger == null){
                System.out.println("Sorry! Please login first.");
            }
            else {
                currentLoggedinPassenger.work();
            }
        } else if(command.equals("logout")) {
            if(arguments.size() != 0){
                System.out.println(wrongNumberOfArgumentMessage);
                return;
            }
            if(currentLoggedinPassenger == null){
                System.out.println("Sorry! No passenger is currently logged in.");
                return;
            }
            currentLoggedinPassenger.logout();
            currentLoggedinPassenger = null;
        } else {
            System.out.println("Sorry! Unknown command");
        }
    }
}