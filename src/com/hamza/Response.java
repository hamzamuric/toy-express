package com.hamza;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Response {
    private final Map<String, String> headers = new HashMap<>();
    private final Socket socket;
    private int status = 200;

    public Response(Socket socket) {
        this.socket = socket;
        headers.put("Server", "hamza-express");
    }

    public void send(String body) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.println("HTTP/1.1 " + status + " " + getStatusMessage());
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            writer.println(entry.getKey() + ": " + entry.getValue());
        }
        writer.println();
        writer.println(body);
        writer.println();
        writer.flush();
        socket.close();
    }

    private String getStatusMessage() {
        return "OK";
    }
}
