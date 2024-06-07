package server;

import util.NetworkUtil;
import util.StockUpdateDTO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NotifierThreadServer implements Runnable{
    private Thread thread;
    private HashMap<NetworkUtil, StockUpdateDTO> notificationMap;

    public NotifierThreadServer(HashMap<NetworkUtil, StockUpdateDTO> notificationMap) {
        this.notificationMap = notificationMap;
        this.thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            for (Map.Entry<NetworkUtil, StockUpdateDTO> entry : notificationMap.entrySet()) {
                NetworkUtil networkUtil = entry.getKey();
                StockUpdateDTO update = entry.getValue();

                //System.out.println("Update written");
                networkUtil.write(update);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("Notifications delivered. Exiting thread...");
        }
    }
}
