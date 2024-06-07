package server;

import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    ServerSocket serverSocket;
    Server(){
        try{
            serverSocket = new ServerSocket(44444);
            System.out.println("Server is waiting...");
            while(true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server accepts a client...");
                var networkUtil = new NetworkUtil(clientSocket);
                new ReadThreadServer(clientSocket, this, networkUtil);
            }

        } catch(Exception e){
            System.out.println("Server starts" + e);

        }
    }
    public static void main(String[] args) throws IOException {
        Server s = new Server();
    }
}
