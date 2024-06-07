package client;

import observers.StockTrader;
import util.NetworkUtil;

public class Client {
    private StockTrader trader;
    private NetworkUtil networkUtil;
    private Boolean isLoggedIn;
    public Client(StockTrader trader, String serverAddress, int serverPort) {
        this.trader = trader;
        this.isLoggedIn = false; // not yet logged in
        try {
            networkUtil = new NetworkUtil(serverAddress, serverPort);
            new ReadThreadClient(this);
            new UIThreadClient(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StockTrader getTrader() {
        return trader;
    }

    public void setTrader(StockTrader trader) {
        this.trader = trader;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    public void setNetworkUtil(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}


