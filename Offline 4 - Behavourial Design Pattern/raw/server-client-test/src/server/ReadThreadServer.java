package server;

import util.MyDTO;
import util.NetworkUtil;

import java.io.IOException;
import java.net.Socket;

public class ReadThreadServer implements Runnable {
    NetworkUtil networkUtil;
    Server server;
    Socket clientSocket;
    Thread t;
    private boolean threadOn = true;

    ReadThreadServer(Socket clientSocket, Server server, NetworkUtil networkUtil){
        this.clientSocket = clientSocket;
        this.server = server;
        this.networkUtil = networkUtil;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        while (threadOn) {
            Object next = null;
            while (threadOn) {
                try {
                    next = networkUtil.read();
                    break;
                } catch (IOException | ClassNotFoundException ignored) {
                }
            }
            if (next instanceof String) {
                System.out.println("Received String  " + next);
            }
            if (next instanceof MyDTO) {
                MyDTO my = (MyDTO) next;
                System.out.println(my.getName() + "received");
                my.setName("Changed by server");
                try {
                    networkUtil.write(my);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
