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

        /*
        so doing the following won't actually work :
        System.out.println(in);
        It'll only print a hash key to the console
         */

        String serverResponse = in.readLine();//this is how we go about formatting the "in" better
        out.println("Hello server");

        System.out.println(serverResponse);//and now we can print it appropriately

        in.close();


    }
}