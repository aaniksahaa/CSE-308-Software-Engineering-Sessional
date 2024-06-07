package server;

import client.Client;
import observers.StockTrader;
import publisher.StockAdministrator;
import util.*;

import java.io.IOException;
import java.util.List;

public class ReadThreadServer implements Runnable{
    private Thread thread;
    private Server server;

    // here a specific networkUtil is passed in constructor
    // because one Server Thread serves a specific client
    private NetworkUtil networkUtil;

    public ReadThreadServer(Server server, NetworkUtil networkUtil) {
        this.server = server;
        this.networkUtil = networkUtil;
        this.thread = new Thread(this);
        thread.start();
    }

    public void run() {
        StockAdministrator admin = server.getAdmin();
        String currentLoggedIn = "";
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o instanceof LoginDTO) {
                    LoginDTO obj = (LoginDTO) o;
                    String observer = obj.getUsername();
                    System.out.println("Login Request Received from "+observer);

                    if(admin.authenticate(obj.getUsername(), obj.getPassword())){
                        System.out.println("Credentials correct. Login approved.");
                        admin.handleLogin(observer, networkUtil);
                        List<StockDTO> subscribedStocks = admin.getSubscribedStocks(observer);
                        List<StockUpdateDTO> pendingNotifications = admin.getPendingNotifications(observer);
                        networkUtil.write(new LoginResponseDTO(observer,true, admin.getAllStocks(), subscribedStocks, pendingNotifications));
                        currentLoggedIn = observer;
                    }
                    else {
                        System.out.println("Credentials Invalid. Login denied.");
                        networkUtil.write(new LoginResponseDTO(observer,false,null,null,null));
                    }
                }
                else if(o instanceof RequestDTO){
                    if(currentLoggedIn.equals("")){
                        System.out.println("No one is currently logged in. Invalid request. Request ignored.");
                        continue;
                    }
                    RequestDTO obj = (RequestDTO) o;
                    String type = obj.getType();
                    String stockName = obj.getStockName();
                    StockDTO requestedStock = new StockDTO(stockName, admin.getCount(stockName), admin.getPrice(stockName));
                    if( ! admin.checkStockExists(stockName) ){
                        String message = "Erroneous request. No such stock exists.";
                        System.out.println(message);
                        networkUtil.write(new RequestResponseDTO("X", message, requestedStock, false));
                    }
                    else if(type.equals("S")){
                        admin.subscribe(currentLoggedIn, stockName);
                        System.out.println(currentLoggedIn + " subscribed to " + stockName);
                        String message = "Subscription to " + stockName + " successful. You will be notified updates at real time.";
                        networkUtil.write(new RequestResponseDTO("S", message, requestedStock, true));
                    }
                    else if(type.equals("U")){
                        admin.unsubscribe(currentLoggedIn, stockName);
                        System.out.println(currentLoggedIn + " unsubscribed from " + stockName);
                        String message = "Unsubscription from " + stockName + " successful. You have opted out from real-time updates.";
                        networkUtil.write(new RequestResponseDTO("U", message, requestedStock, true));
                    }
                } else if (o instanceof LogoutDTO) {
                    LogoutDTO obj = (LogoutDTO) o;
                    String observer = obj.getUsername();
                    admin.handleLogout(observer);
                    System.out.println(observer + " logged out.");
                    currentLoggedIn="";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




