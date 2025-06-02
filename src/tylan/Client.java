package tylan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 8080);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        /*
        so doing the following won't actually work :
        System.out.println(in);
        It'll only print a hash key to the console
         */

        while (true){
            String serverResponse;
            while ((serverResponse = in.readLine()) != null &&
                    !serverResponse.startsWith("choose") &&
                    !serverResponse.startsWith("Please enter")){
                System.out.println("SERVER SAYs: " + serverResponse);
            }

            if (serverResponse != null) {
                System.out.println("SERVER RESPONSE: " + serverResponse);
            }

            String response = keyboard.readLine();
            out.println(response);
            if(response.equals("quit")){
                break;
            }

        }



        //and now we can print it appropriately

        in.close();
        out.close();
        keyboard.close();


    }

}