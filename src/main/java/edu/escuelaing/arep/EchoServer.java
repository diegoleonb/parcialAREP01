package edu.escuelaing.arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import edu.escuelaing.arep.API.*;

public class EchoServer {
    
    private static EchoServer instance = new EchoServer();

    public static EchoServer getInstance() {
        return instance;
    }

    public void start() throws IOException {
      ServerSocket serverSocket = null;
      try {
          serverSocket = new ServerSocket(getPort());
      } catch (IOException e) {
          System.err.println("Could not listen on port: 35000.");
          System.exit(1);
      }
        String exit = "";
        while (exit != "exit") {
         Socket clientSocket=null;
            try {
               System.out.println("Listo para recibir ...");
               clientSocket = serverSocket.accept();
           } catch (IOException e) {
               System.err.println("Accept failed.");
               System.exit(1);
           }
           try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            if (true) {
               String inputLine = "";
               while ((inputLine = in.readLine()) != null) {
                   System.out.println("Recibí: " + inputLine);
                   Response.response(clientSocket, inputLine);
                   if (!in.ready()) {
                       break;
                   }
               }
            }
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        }
        serverSocket.close();

    }

    private int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }
}