package client;

import observers.StockTrader;
import util.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UIThreadClient implements Runnable{

    private Thread thread;
    private Client client;
    private String wrongNumberOfArgumentMessage = "Sorry! Wrong number of arguments.\n";
    private String notLoggedInMessage = "Sorry! You need to login first.\n";
    private String loggedInMessage = "Sorry! You need to logout first.\n";
    public UIThreadClient(Client client) {
        this.client = client;
        this.thread = new Thread(this);
        thread.start();
    }

    public void run() {
        NetworkUtil networkUtil = client.getNetworkUtil();
        StockTrader trader = client.getTrader();
        try {
            // L = Login, E = Exit
            Scanner input = new Scanner(System.in);
            while (true) {
                String s = input.nextLine();
                Instruction ins = InstructionParser.parseInstruction(s);
                String command = ins.getCommand();
                List<String>arguments = ins.getArguments();

                if(command.equals("L")){
                    if(arguments.size() != 2){
                        System.out.println(wrongNumberOfArgumentMessage);
                    }
                    else if(client.getLoggedIn()){
                        System.out.println(loggedInMessage);
                    }
                    else {
                        networkUtil.write(new LoginDTO(arguments.get(0), arguments.get(1)));
                        System.out.println("Login request sent to the server.");
                    }
                }
                else if(command.equals("S")){
                    if(arguments.size() != 1){
                        System.out.println(wrongNumberOfArgumentMessage);
                    }
                    else if( ! client.getLoggedIn()){
                        System.out.println(notLoggedInMessage);
                    }
                    else {
                        networkUtil.write(new RequestDTO("S", arguments.get(0)));
                        System.out.println("Subscription request sent");
                    }
                }
                else if(command.equals("U")){
                    if(arguments.size() != 1){
                        System.out.println(wrongNumberOfArgumentMessage);
                    }
                    else if( ! client.getLoggedIn()){
                        System.out.println(notLoggedInMessage);
                    }
                    else {
                        networkUtil.write(new RequestDTO("U", arguments.get(0)));
                        System.out.println("Unsubscription request sent");
                    }
                } else if (command.equals("V")) {
                    if(arguments.size() != 0){
                        System.out.println(wrongNumberOfArgumentMessage);
                    }
                    else if( ! client.getLoggedIn()){
                        System.out.println(notLoggedInMessage);
                    }
                    else {
                        List<StockDTO> localStocks = trader.getLocalStocks();
                        if(localStocks.isEmpty()){
                            System.out.println("You have subscribed to no stock. Use command 'S <stock_name>' to subscribe\n");
                        }
                        else {
                            System.out.println("\nYour subscribed stocks:\n");
                            for (StockDTO stock : localStocks) {
                                System.out.println(stock);
                            }
                            System.out.println("");
                        }
                    }
                } else if (command.equals("E")) {
                    if(arguments.size() != 0){
                        System.out.println(wrongNumberOfArgumentMessage);
                    }
                    else if( ! client.getLoggedIn()){
                        System.out.println(notLoggedInMessage);
                    }
                    else {
                        System.out.println("Logged Out.\n");
                        networkUtil.write(new LogoutDTO(trader.getUsername()));
                        trader.clearLocalState();
                        client.setLoggedIn(false);
                    }
                }
                else {
                    System.out.println("Sorry! Unknown Command.\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



