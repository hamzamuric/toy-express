package com.hamza;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@FunctionalInterface
interface VoidCallback {
    void call();
}

@FunctionalInterface
interface RequestHandler {
    void call(Request req, Response res) throws IOException;
}

public class App {
    private final Map<String, RequestHandler> getHandlers = new LinkedHashMap<>();
    private final Map<String, RequestHandler> postHandlers = new LinkedHashMap<>();
    private RequestHandler notFoundHandler = (req, res) -> {
        System.out.println("404");
    };

    public void get(String path, RequestHandler handler) {
        getHandlers.put(path, handler);
    }

    public void post(String path, RequestHandler handler) {
        postHandlers.put(path, handler);
    }

    public void error404(RequestHandler handler) {
        notFoundHandler = handler;
    }

    public void listen(int port, VoidCallback callback) {
        try {
            Http http = new Http(port);
            callback.call();

            while (true) {
                RequestResponse reqRes = http.nextConnection();
                Request req = reqRes.getRequest();
                Response res = reqRes.getResponse();

                switch (req.getType()) {
                    case "GET":
                        RequestHandler getHandler = getHandlers.get(req.getPath());
                        if (getHandler == null) {
                            notFoundHandler.call(req, res);
                        } else {
                            getHandler.call(req, res);
                        }
                        break;
                    case "POST":
                        RequestHandler postHandler = postHandlers.get(req.getPath());
                        if (postHandler == null) {
                            notFoundHandler.call(req, res);
                        } else {
                            postHandler.call(req, res);
                        }
                        break;
                    default:
                        System.err.println("Unsupported http method " + req.getType());
                }
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
}
