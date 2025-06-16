package org.example;

import io.javalin.Javalin;
import org.example.config.Routes;
import org.example.controller.HelloController;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.showJavalinBanner = false;
        });

        Routes.configure(app);
        app.start(7001); // porta 7000 tava usando operações do meu sistemas que não consegui matar
    }
}