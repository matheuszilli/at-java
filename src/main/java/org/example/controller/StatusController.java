package org.example.controller;

import io.javalin.http.Context;
import org.example.utils.DateUtils;

import java.util.HashMap;
import java.util.Map;

public class StatusController {

    /**
     * Endpoint GET /status
     * Retorna o status da aplicação com um timestamp
     */
    public void getStatus(Context ctx) {
        Map<String, Object> response = new HashMap<>();

        // Status ok
        response.put("status", "OK");

        // Usa a classe utilitária para obter timestamp (mesma lógica da Tarefa)
        String timestamp = DateUtils.obterTimestampAtual();
        response.put("timestamp", timestamp);

        ctx.json(response);
    }
}