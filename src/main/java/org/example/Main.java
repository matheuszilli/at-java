package org.example;

import io.javalin.Javalin;
import org.example.config.Routes;
import org.example.controller.HelloController;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.showJavalinBanner = false; // Disable Javalin banner
        });

        Routes.configure(app);
        app.start(7000);
    }
}