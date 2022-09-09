package edu.escuelaing.arep.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class Response {
    private static final String GET_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static final String GET_KEY = "&appid=b441b3273b993da746c237520044ce76";
    public static void response(Socket clientSocket, String input){
        String url;
        if(input.contains("GET")){
            url = input.split(" ")[1].substring(1);
            try {
                if (url.contains("clima")) {
                    consultarClima(clientSocket,Clima.getInstance());
                } else if (url.contains("consulta?lugar=")) {
                    consultarCiudad(clientSocket, url);
                } else {
                    System.out.println("Opcion Invalida. Se le va a enviar a clima");
                    consultarClima(clientSocket,Clima.getInstance());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void consultarClima(Socket clientSocket, String url) throws IOException{
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println(url);
        out.close(); 
    }

    private static void consultarCiudad(Socket clientSocket, String url) throws IOException{
        String ciudad = url.split("=")[1];
        String api = GET_URL + ciudad + GET_KEY ;
        URL nUrl = new URL(api);
        HttpURLConnection httpUrl = (HttpURLConnection) nUrl.openConnection();
        httpUrl.setRequestMethod("GET");
        httpUrl.getResponseCode();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.print("HTTP/1.1 200 OK\r\n Content-Type: text/json \r\n\r\n");
        BufferedReader br = new BufferedReader(new InputStreamReader(httpUrl.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        out.print(sb);
        out.close();
    }
}
