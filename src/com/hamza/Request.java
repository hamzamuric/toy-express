package com.hamza;

import java.util.Map;

public class Request {
    private final String type;
    private final String path;
    private final Map<String, String> headers;

    public Request(String type, String path, Map<String, String> headers) {
        this.type = type;
        this.path = path;
        this.headers = headers;
    }

    public String getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===REQUEST===\n");
        sb.append("Type: ");
        sb.append(type);
        sb.append('\n');
        sb.append("Path: ");
        sb.append(path);
        sb.append('\n');
        sb.append("Headers:\n");
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append('\n');
        }
        sb.append('\n');
        return sb.toString();
    }
}
