package edu.escuelaing.arep.API;

import java.io.IOException;

public class Clima {
    public static String getInstance() throws IOException{
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n" +
                "<!DOCTYPE html>" +
                script()  +
                "<html>"  +
                "<head>"  +
                "<title>PARCIAL DIEGO</title>" +
                "<script> src='./js/script.js'</script>" +
                "</head>" +
                "<body >" +
                "<h1>API</h1> "+
                "<h2 >Ciudad</h2>" +
                "<input class='box' type='Text' id='nombre' placeholder='Nombre'>" +
                "<button  class='box' onclick='consultar()' class='btn'>Consultar</button>" +
                "<h3>Nombre</h3>" +
                "<p  id='city'></p>" +
                "<h3>Clima</h3>" +
                "<p id='tempe'></p>" +
                "</body>" +
               " </html>";

    }

    public static String script() {
        return "<script type=\"text/javascript\">let url = \"https://parcialdiego.herokuapp.com/consulta?lugar=\"\n" +
                "function consultar(){\n" +
                "fetch(url+document.getElementById(\"nombre\").value)\n" +
                "  .then(response => response.json())\n" +
                ".then(data => {\n" +
                "document.getElementById(\"city\").innerHTML = data.name\n" +
                "document.getElementById(\"tempe\").innerHTML = data.main.temp\n})\n}\n</script>\n";
    }
}
