package app;

import java.net.*;

public class PortChecker {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 2005;
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), 1000); // Thời gian chờ là 1 giây
            socket.close();
            System.out.println("Port " + port + " is open.");
        } catch (Exception ex) {
            System.out.println("Port " + port + " is closed.");
        }
    }
}

