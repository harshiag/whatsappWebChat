package mutlipleClients;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        Thread inputThread = new Thread(() -> {
            try {
                String inputLine;
                while ((inputLine = consoleReader.readLine()) != null) {
                    out.println(inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        inputThread.start();

        String response;
        while ((response = in.readLine()) != null) {
            System.out.println("Server: " + response);
        }

        in.close();
        out.close();
        socket.close();
    }
}

