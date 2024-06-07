package client;

import util.NetworkUtil;

import java.io.IOException;
import java.util.Scanner;

public class Client {
    private NetworkUtil networkUtil;
    public Client(){

    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 44444;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new ReadThreadClient(this);
    }

    public void run() throws IOException{
        connectToServer();
    }


}
