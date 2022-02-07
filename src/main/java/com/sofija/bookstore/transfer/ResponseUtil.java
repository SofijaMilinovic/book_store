package com.sofija.bookstore.transfer;

public final class ResponseUtil {

    private ResponseUtil() {
    }

    public static Response createResponse(Object data, int statusCode, String message) {
        Response response = createResponse();
        response.setData(data);
        response.setStatusCode(statusCode);
        response.setMessage(message);
        return response;
    }

    public static Response createResponse(int statusCode, String message) {
        return createResponse(null, statusCode, message);
    }

    private static Response createResponse() {
        return new Response();
    }
}
