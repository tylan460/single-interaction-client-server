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
            clientResponse = verifyOperator(in.readLine());
            System.out.println("Client response: " + clientResponse);
            if (clientResponse.equals("Invalid operator")){
                out.println("Invalid operator detected.");
            }
        }while (verifyOperator(clientResponse).equals("Invalid operator"));

        String operator = clientResponse;

        out.println("Please enter your first number");
        int firstNum = verifyNumber(communicationSocket);

        out.println("Please enter your second number");
        int secondNum = verifyNumber(communicationSocket);

        int final_Answer = performCalculation(operator, firstNum, secondNum);

        out.println(firstNum + " " + operator + " " + secondNum + " " + "= " + final_Answer);









        communicationSocket.close();
        listeningSocket.close();

        /*
        killing port if already in use error occurs:
        lsof -i :5000
        kill -9 <pid>
         */
    }

    public static int verifyNumber(Socket socket) throws IOException {
        int number;

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter response = new PrintWriter(socket.getOutputStream(), true);
        while (true) {
            String numString = in.readLine();
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
            if (operator.equals(theOperator)) {
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

    public static int performCalculation(String operator, int num1, int num2){

        switch (operator){
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                return -1;


        }
    }
}