package publisher;

import server.NotifierThreadServer;
import server.Server;
import util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockAdministrator {
    private String name;
    private Server server;

    // credentials

    private HashMap<String, String> credentialsMap = new HashMap<>();

    // storing a hashmap of observers' unique usernames instead of list
    // so that It can be used by a single Administrator to lookup observers for a stock as per the specification

    // Since the receiving side reconstructs a transmitted object in case of Java Networking,
    // storing username Strings and corresponding NetworkUtils instead of Observer objects themselves
    private HashMap<String, List<String>> observerMap = new HashMap<>();
    private HashMap<String, List<String>> subscribedStocksMap = new HashMap<>();
    private HashMap<String, NetworkUtil> networkMap = new HashMap<>();
    private HashMap<String, List<StockUpdateDTO>> pendingNotificationMap = new HashMap<>();

    // state variables
    private HashMap<String, Integer> countMap = new HashMap<>();
    private HashMap<String, Double> priceMap = new HashMap<>();

    public StockAdministrator() throws Exception{
        initializeData();
    }

    public List<StockDTO> getAllStocks() {
        List<StockDTO> stockList = new ArrayList<>();
        for (String stockName : countMap.keySet()) {
            int count = countMap.get(stockName);
            if (priceMap.containsKey(stockName)) {
                double price = priceMap.get(stockName);
                StockDTO stock = new StockDTO(stockName, count, price);
                stockList.add(stock);
            }
            else {
                // errorneous, should not happen
            }
        }
        return stockList;
    }

    public void initializeData() throws Exception{
        List<StockDTO> stocks = StockParser.parseStockFile();
        for(StockDTO s: stocks){
            countMap.put(s.getName(), s.getCount());
            priceMap.put(s.getName(), s.getPrice());
        }
        credentialsMap = CredentialParser.parseCredentialFile();
    }

    // Getter and Setters for states
    public int getCount(String stockName){
        int count = -1;
        if(countMap.containsKey(stockName)){
            count = countMap.get(stockName);
        }
        return count;
    }
    // many threads may simultaneously write into the shared HashMap
    //  so it should be synchronized
    public synchronized void setCount(String stockName, int count) throws IOException{
        countMap.put(stockName, count);
        //notify(stockName);
    }
    public void increaseCount(String stockName, int increment) throws IOException{
        System.out.println("Changing count of " + stockName);
        setCount(stockName, getCount(stockName)+increment);
        notify(stockName);
    }
    public double getPrice(String stockName){
        double price = -1;
        if(priceMap.containsKey(stockName)){
            price = priceMap.get(stockName);
        }
        return price;
    }
    // many threads may simultaneously write into the shared HashMap
    //  so it should be synchronized
    public synchronized void setPrice(String stockName, double price) throws IOException{
        priceMap.put(stockName, price);
        //notify(stockName);
    }
    public void increasePrice(String stockName, double increment) throws IOException{
        System.out.println("Changing price of " + stockName);
        setPrice(stockName, getPrice(stockName)+increment);
        notify(stockName);
    }
    // Getter for subscribed Stocks
    public List<StockDTO> getSubscribedStocks(String observer){
        List<StockDTO> stockList = new ArrayList<>();
        if(subscribedStocksMap.containsKey(observer)){
            List<String> subscribedStocks = subscribedStocksMap.get(observer);
            for(String stockName: subscribedStocks){
                stockList.add(new StockDTO(stockName, countMap.get(stockName), priceMap.get(stockName)));
            }
        }
        return stockList;
    }

    public List<StockUpdateDTO> getPendingNotifications(String observer){
        List<StockUpdateDTO> pendingList = new ArrayList<>();
        if(pendingNotificationMap.containsKey(observer)){
            List<StockUpdateDTO> pending = pendingNotificationMap.get(observer);
            for(StockUpdateDTO update: pending){
                pendingList.add(update);
            }
        }
        return pendingList;
    }

    public void subscribe(String observer, String stockName){
        if (observerMap.containsKey(stockName)) {
            List<String> observers = observerMap.get(stockName);
            observers.add(observer);
        } else {
            List<String> observers = new ArrayList<>();
            observers.add(observer);
            observerMap.put(stockName, observers);
        }
        if (subscribedStocksMap.containsKey(observer)) {
            List<String> subscribedStocks = subscribedStocksMap.get(observer);
            subscribedStocks.add(stockName);
        } else {
            List<String> subscribedStocks = new ArrayList<>();
            subscribedStocks.add(stockName);
            subscribedStocksMap.put(observer, subscribedStocks);
        }
    }
    public void unsubscribe(String observer, String stockName){
        if (observerMap.containsKey(stockName)) {
            List<String> observers = observerMap.get(stockName);
            observers.remove(observer);
        } else {
            // errorneous request, ignore it
        }
        if (subscribedStocksMap.containsKey(observer)) {
            List<String> subscribedStocks = subscribedStocksMap.get(observer);
            subscribedStocks.remove(stockName);
        } else {
            // errorneous request, ignore it
        }
    }
    public void notify(String stockName) throws IOException {
        StockUpdateDTO update = new StockUpdateDTO(stockName, countMap.get(stockName), priceMap.get(stockName));
        System.out.println("Notifying Observers for " + stockName + "...");

        if(observerMap.containsKey(stockName)){
            HashMap<NetworkUtil, StockUpdateDTO> notificationMap = new HashMap<>();
            List<String> observers = observerMap.get(stockName);
            for(String observer: observers){
                System.out.print(observer);
                if(networkMap.containsKey(observer)){  // so logged in
                    System.out.println(" is currently logged in.");
                    //networkMap.get(observer).write(update);  // write the update to network
                    notificationMap.put(networkMap.get(observer), update);
                }
                else {
                    System.out.println(" is not currently logged in, storing the notification for future");
                    if(pendingNotificationMap.containsKey(observer)){
                        pendingNotificationMap.get(observer).add(update);
                    } else {
                        List<StockUpdateDTO> pendingList = new ArrayList<>();
                        pendingList.add(update);
                        pendingNotificationMap.put(observer, pendingList);
                    }
                }
            }
            // opening a new thread
            System.out.println("Notifier Thread opened to notify currently logged in users...");
            new NotifierThreadServer(notificationMap);
        }
    }
    public void handleLogin(String observer, NetworkUtil networkUtil){
        networkMap.put(observer, networkUtil);
    }
    public void handleLogout(String observer){
        networkMap.remove(observer);
    }
    public void run(){
        System.out.println("Welcome Admin\nUsage: I = Increase Price, D = Decrease Price, C = Change Count\n");
        server = new Server(this);
    }
    public Boolean authenticate(String username, String password){
        if(credentialsMap.containsKey(username) && credentialsMap.get(username).equals(password)){
            return true;
        }
        else {
            return false;
        }
    }
}
