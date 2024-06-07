package client;

import observers.StockTrader;
import util.*;

import java.io.IOException;
import java.util.List;

public class ReadThreadClient implements Runnable{
    private Thread thread;
    private Client client;

    public ReadThreadClient(Client client) {
        this.client = client;
        this.thread = new Thread(this);
        thread.start();
    }

    public void run() {
        NetworkUtil networkUtil = client.getNetworkUtil();
        StockTrader trader = client.getTrader();
        try {
            while (true) {
                Object o = networkUtil.read();
                if(o instanceof LoginResponseDTO){
                    LoginResponseDTO obj = (LoginResponseDTO) o;
                    if(obj.getStatus()){
                        System.out.println("Login Successful\n\nWelcome, "+obj.getUsername() + "\n");
                        List<StockDTO> allStocks = obj.getAllStocks();
                        System.out.println("List of Available Stocks: \n");
                        for(StockDTO stock: allStocks){
                            System.out.println(stock);
                        }
                        System.out.println("");
                        trader.update(obj.getPendingUpdates());
                        client.setLoggedIn(true);
                        trader.setUsername(obj.getUsername());
                        trader.buildLocalState(obj.getSubscribedStocks());
                    }
                    else {
                        System.out.println("Sorry! Login Failed.\n");
                    }
                }
                else if(o instanceof RequestResponseDTO){
                    RequestResponseDTO obj = (RequestResponseDTO) o;
                    trader.update(obj);
                }
                else if (o instanceof NotificationDTO) {
                    NotificationDTO obj = (NotificationDTO) o;
                    trader.update(obj.getMessage());
                }
                else if(o instanceof StockUpdateDTO){
                    StockUpdateDTO obj = (StockUpdateDTO) o;
                    trader.update(obj);
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



