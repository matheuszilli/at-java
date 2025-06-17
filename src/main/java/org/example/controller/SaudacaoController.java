package org.example.controller;

import io.javalin.http.Context;

import java.util.Map;

public class SaudacaoController {

    public void saudar(Context ctx) {
        try{

            String nome = ctx.pathParam("nome");

            if (nome.isEmpty()) {
                ctx.status(400);
                ctx.json(Map.of("erro", "Nome não pode ser vazio"));
                return;
            }

            nome = nome.trim();

            String mensagem = "Olá, " + nome + "!";

            Map<String, Object> resposta = Map.of("Mensagem", mensagem);
            ctx.json(resposta);

        } catch (Exception e) {

            ctx.status(500);
            ctx.json(Map.of("erro", "Erro interno do servidor ao processar a requisição"));

        }
    }
}
