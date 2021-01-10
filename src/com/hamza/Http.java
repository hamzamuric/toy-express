package com.hamza;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Http {
    private final ServerSocket server;

    public Http(int port) throws IOException {
        this.server = new ServerSocket(port);
    }

    public RequestResponse nextConnection() throws IOException {
        Socket socket = server.accept();
        Scanner scanner = new Scanner(socket.getInputStream());

        String type = scanner.next();
        String path = scanner.next();
        if (path.length() > 1 && path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        scanner.nextLine();
        Map<String, String> headers = new HashMap<>();

        while (true) {
            String headerLine = scanner.nextLine();
            if (headerLine.equals("")) break;

            String[] splitted = headerLine.split(":");
            String key = splitted[0].trim();
            String value = splitted[1].trim();

            headers.put(key, value);
        }

        Request req = new Request(type, path, headers);
        Response res = new Response(socket);
        return new RequestResponse(req, res);
    }
}
