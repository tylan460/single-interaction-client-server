package tylan;


import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    static BufferedReader in;
    static PrintWriter out;
    static int port = 8080;
    static ServerSocket listeningSocket;
    static Socket communicationSocket;

    public static void main(String[] args) throws IOException {
        System.out.println("Server is waiting for connection...");
        listeningSocket = new ServerSocket(port);
        communicationSocket = listeningSocket.accept();
        System.out.println("Client connected");
        out = new PrintWriter(communicationSocket.getOutputStream(), true);
        out.println("Hello client");
        in = new BufferedReader(new InputStreamReader(communicationSocket.getInputStream()));
        String clientResponse = in.readLine();
        System.out.println(clientResponse);
        communicationSocket.close();
        listeningSocket.close();

        /*
        killing port if already in use error occurs:
        lsof -i :5000
        kill -9 <pid>
         */
    }
}