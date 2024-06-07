package server;

import publisher.StockAdministrator;
import util.*;

import java.util.List;
import java.util.Scanner;

public class UIThreadServer implements Runnable{

    private Thread thread;
    private Server server;
    private String wrongNumberOfArgumentMessage = "Sorry! Wrong number of arguments.";

    // here a specific networkUtil is passed in constructor
    // because one Server Thread serves a specific client
    public UIThreadServer(Server server) {
        this.server = server;
        this.thread = new Thread(this);
        thread.start();
    }

    public void run() {
        StockAdministrator admin = server.getAdmin();
        try {
            Scanner input = new Scanner(System.in);
            while (true) {
                //System.out.println("Please input a command: ");
                String s = input.nextLine();
                Instruction ins = InstructionParser.parseInstruction(s);
                String command = ins.getCommand();
                List<String> arguments = ins.getArguments();

                if(command.equals("I")){
                    if(arguments.size() != 2){
                        System.out.println(wrongNumberOfArgumentMessage);
                    } else if ( ! admin.checkStockExists(arguments.get(0)) ) {
                        System.out.println("Sorry! No such stock exists.");
                    } else {
                        admin.increasePrice(arguments.get(0), Double.parseDouble(arguments.get(1)));
                        System.out.println("\nPrice of " + arguments.get(0) + " increased successfully.\n");
                    }
                }
                else if(command.equals("D")){
                    if(arguments.size() != 2){
                        System.out.println(wrongNumberOfArgumentMessage);
                    }
                    else if ( ! admin.checkStockExists(arguments.get(0)) ) {
                        System.out.println("Sorry! No such stock exists.");
                    }
                    else {
                        // decrementing price
                        admin.increasePrice(arguments.get(0), (-1.0)*Double.parseDouble(arguments.get(1)));
                        System.out.println("\nPrice of " + arguments.get(0) + " decreased successfully.\n");
                    }
                }
                else if(command.equals("C")){
                    if(arguments.size() != 2){
                        System.out.println(wrongNumberOfArgumentMessage);
                    }
                    else if ( ! admin.checkStockExists(arguments.get(0)) ) {
                        System.out.println("Sorry! No such stock exists.");
                    }
                    else {
                        admin.increaseCount(arguments.get(0), Integer.parseInt(arguments.get(1)));
                        System.out.println("\nCount of " + arguments.get(0) + " increased successfully.\n");
                    }
                }
                else {
                    System.out.println("Sorry! Unknown Command.");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Exiting Server UI Thread...");
        }
    }
}




