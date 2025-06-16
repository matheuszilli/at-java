package org.example.controller;


import io.javalin.http.Context;

public class HelloController {

    /**
     * Endpoint GET /hello
     * Retonar a mensagem "Hello, Javalin!"
     */
    public void hello(Context ctx) {
        ctx.result("Hello, Javalin!");
    }
}
