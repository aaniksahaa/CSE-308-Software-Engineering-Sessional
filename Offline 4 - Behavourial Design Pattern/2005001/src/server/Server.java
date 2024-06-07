package server;

import publisher.StockAdministrator;
import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private StockAdministrator admin;
    private ServerSocket serverSocket;

    public StockAdministrator getAdmin() {
        return admin;
    }

    public Server(StockAdministrator admin) {
        this.admin = admin;
        try {
            serverSocket = new ServerSocket(33333);
            System.out.println("Server is running...");
            new UIThreadServer(this);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
                System.out.println("Server accepts a client...");
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(this, networkUtil);
    }
}
