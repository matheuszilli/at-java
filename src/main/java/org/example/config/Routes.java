package org.example.config;

import io.javalin.Javalin;
import org.example.controller.EchoController;
import org.example.controller.HelloController;
import org.example.controller.StatusController;

/**
 * Classe responsável por configurar todas as rotas da aplicação
 * Centraliza o registro de endpoints para facilitar manutenção
 */
public class Routes {

    /**
     * Configura todas as rotas da aplicação
     */
    public static void configure(Javalin app) {
        /**
         * ETAPA 1
         * Exercicio 01
         */
        // Instancia o controlador HelloController
        HelloController helloController = new HelloController();
        // Registra a rota GET /hello que chama o hello do controlador
        app.get("/hello", helloController::hello);

        /**
         * ETAPA 2
         * Exercicio 02
         */
        // Instancia o controlador StatusController
        StatusController statusController = new StatusController();
        app.get("/status", statusController::getStatus);

        /**
         * ETAPA 3
         * Exercicio 03
         */
        // Instancia o controlador EchoController
        EchoController echoController = new EchoController();
        app.post("/echo", echoController::echo);

    }
}
