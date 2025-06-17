package org.example.config;

import io.javalin.Javalin;
import org.example.controller.*;

/**
 * Classe responsável por configurar todas as rotas da aplicação
 * Centraliza o registro de endpoints para facilitar manutenção
 */
public class Routes {

    /**
     * Configura todas as rotas da aplicação
     */
    public static void configure(Javalin app) {

        // ========== INSTANCIAÇÃO DOS CONTROLLERS ==========
        HelloController helloController = new HelloController();
        StatusController statusController = new StatusController();
        EchoController echoController = new EchoController();
        SaudacaoController saudacaoController = new SaudacaoController();
        TarefaController tarefaController = new TarefaController();

        // Etapa 01 - Exercício 01:
        app.get("/hello", helloController::hello);

        // Etapa 01 - Exercício 02:
        app.get("/status", statusController::getStatus);

        // Etapa 01 - Exercício 03:
        app.post("/echo", echoController::echo);

        // Etapa 01 - Exercício 04:
        app.get("/saudacao/{nome}", saudacaoController::saudar);

        // Etapa 01 - Exercício 05:
        app.post("/tarefas", tarefaController::criarTarefa);

        // Etapa 01 - Exercício 06:
        app.get("/tarefas", tarefaController::listar);         // Lista todas
        app.get("/tarefas/{id}", tarefaController::buscar);    // Busca por ID
    }
}