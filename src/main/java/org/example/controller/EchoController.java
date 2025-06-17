package org.example.controller;

import io.javalin.http.Context;

import java.util.Map;

public class EchoController {

    public void echo(Context ctx) {
        try {
            Map<String, Object> dadosRecebidos = ctx.bodyAsClass(Map.class);

            if(!dadosRecebidos.containsKey("mensagem")) {
                ctx.status(400); // Bad Request
                ctx.json(Map.of("erro", "Campo 'mensagem' é obrigatório"));
                return;
            }

            ctx.json(dadosRecebidos);
        } catch (Exception e) {
            ctx.status(400); // Bad Request
            ctx.json(Map.of("erro", "Erro interno do servidor ao processar a requisição"));
        }
    }
}
