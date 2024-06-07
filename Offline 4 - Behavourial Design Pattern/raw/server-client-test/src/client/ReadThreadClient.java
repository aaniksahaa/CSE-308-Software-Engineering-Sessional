package client;

import util.*;

import java.io.IOException;

public class ReadThreadClient implements Runnable {
    private final Thread thr;
    private final Client client;

    public ReadThreadClient(Client client) {
        this.client = client;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = client.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof MyDTO) {
                        MyDTO my = (MyDTO) o;
                        System.out.println(my.getName());
                        System.out.println("Received " + my.getName() + " from client");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                client.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


