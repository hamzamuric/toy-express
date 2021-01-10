package com.hamza;

public class RequestResponse {
    private final Request req;
    private final Response res;

    public RequestResponse(Request req, Response res) {
        this.req = req;
        this.res = res;
    }

    public Request getRequest() {
        return req;
    }

    public Response getResponse() {
        return res;
    }
}
