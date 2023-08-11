package singleClient;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String inputLine;
        while ((inputLine = consoleReader.readLine()) != null) {
            out.println(inputLine);
            System.out.println("Server: " + in.readLine());
        }

        in.close();
        out.close();
        socket.close();
    }
}
