package tylan;


import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
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
        out.println("Welcome to the Mini Calculator App! Let's do some quick math.");
        in = new BufferedReader(new InputStreamReader(communicationSocket.getInputStream()));


        String clientResponse;
        do {
            out.println("choose a valid operator, options : [+, -, *, /]");
            clientResponse = in.readLine();
        }while (verifyOperator(clientResponse).equals("Invalid Operator"));





        communicationSocket.close();
        listeningSocket.close();

        /*
        killing port if already in use error occurs:
        lsof -i :5000
        kill -9 <pid>
         */
    }

    public static int verifyNumber(String numString, Socket socket) throws IOException {
        int number;
        while (true) {

            PrintWriter response = new PrintWriter(socket.getOutputStream(), true);
            try {
                number = Integer.parseInt(numString);
                break;
            } catch (NumberFormatException e) {
                response.println("Please enter a valid number!");
            }
        }

        return number;
    }

    public static String verifyOperator(String operator) {
        String[] operators = {"+", "-", "/", "*"};
        boolean found = false;


        for (String theOperator : operators) {
            if (operator == theOperator) {
                found = true;
                break;
            }
        }
        if (found){
            return operator;
        }
        else {
            return "Invalid operator";
        }
    }
}