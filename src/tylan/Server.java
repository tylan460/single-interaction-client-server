package tylan;


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
        System.out.println("The server is running");
        listeningSocket = new ServerSocket(port);
        communicationSocket = listeningSocket.accept();
        out = new PrintWriter(communicationSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(communicationSocket.getInputStream()));
    }
}