package observers;


import client.Client;
import util.RequestResponseDTO;
import util.StockDTO;
import util.StockUpdateDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockTrader implements StockObserver {
    private String username;
    // local state variables // holds only own subscribed stocks
    private HashMap<String, Integer> localCountMap = new HashMap<>();
    private HashMap<String, Double> localPriceMap = new HashMap<>();
    public StockTrader(){
        // varibales will be initialized upon login
    }
    public void run(){
        System.out.println("Welcome\nUsage: L = Login, S = Subscribe, U = Unsubscribe, V = View, E = Logout\n");
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        Client client = new Client(this, serverAddress, serverPort);
    }
    public void clearLocalState(){
        localCountMap.clear();
        localPriceMap.clear();
    }
    public void buildLocalState(List<StockDTO> stocks){
        for(StockDTO s: stocks){
            localCountMap.put(s.getName(), s.getCount());
            localPriceMap.put(s.getName(), s.getPrice());
        }
    }
    public void addToLocalState(StockDTO s){
        localCountMap.put(s.getName(), s.getCount());
        localPriceMap.put(s.getName(), s.getPrice());
    }
    public void removeFromLocalState(StockDTO s){
        localCountMap.remove(s.getName());
        localPriceMap.remove(s.getName());
    }
    public List<StockDTO> getLocalStocks() {
        List<StockDTO> stockList = new ArrayList<>();

        for (Map.Entry<String, Integer> countEntry : localCountMap.entrySet()) {
            String stockName = countEntry.getKey();
            int count = countEntry.getValue();

            if (localPriceMap.containsKey(stockName)) {
                double price = localPriceMap.get(stockName);
                StockDTO stock = new StockDTO(stockName, count, price);
                stockList.add(stock);
            } else {
                // Erroneous case, should never happen
            }
        }

        return stockList;
    }
    public void update(String message){
        System.out.println(message);
    }
    public void update(RequestResponseDTO response){
        StockDTO stock = response.getStock();
        if(response.getStatus())
        {
            if(response.getType().equals("S")){  // subscribed
                addToLocalState(stock);
            } else {  // unsubscribed
                removeFromLocalState(stock);
            }
        }
        System.out.println(response.getMessage()+"\n");
    }
    public void update(List<StockUpdateDTO> pendingUpdates){
        // In this case, just show the updates, don't alter local state as they were already altered while server sends the updated state on login
        if(pendingUpdates.size() == 0){
            System.out.println("No Unread Notification\n");
            return;
        }
        System.out.println("Unread Notifications:\n");
        for(StockUpdateDTO update: pendingUpdates){
            System.out.println(update);
        }
        System.out.println("");
    }
    public void update(StockUpdateDTO update){
        // showing the notification
        System.out.println("Notification: " + update + "\n");

        // updating local state
        localCountMap.put(update.getStockName(), update.getUpdatedCount());
        localPriceMap.put(update.getStockName(), update.getUpdatedPrice());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
