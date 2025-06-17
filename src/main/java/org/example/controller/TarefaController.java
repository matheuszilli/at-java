package org.example.controller;

import io.javalin.http.Context;
import org.example.model.Tarefa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TarefaController {


    /**
     * Lista de tarefas (simula um banco de dados)
     * Aqui você pode implementar a lógica para persistir as tarefas
     */
    private static final List<Tarefa> tarefas = new ArrayList<>();

    /**
     * Endpoint POST /tarefas
     * Cria uma nova tarefa e armazena em memória
     *
     * Json mínimo esperado:
     *
     * {
     *     "titulo": "Título da tarefa",
     *     "descricao": "Descrição da tarefa"
     * }
     *
     * Json maximo esperado:
     */
    public void criarTarefa(Context ctx){

        try{
            Map<String, Object> dadosRecebidos = ctx.bodyAsClass(Map.class);


            if (!dadosRecebidos.containsKey("titulo") ||
                    dadosRecebidos.get("titulo") == null ||
                    dadosRecebidos.get("titulo").toString().trim().isEmpty()) {

                ctx.status(400); // Bad Request
                ctx.json(Map.of("erro", "Campo 'titulo' é obrigatório"));
                return;
            }

            String titulo = dadosRecebidos.get("titulo").toString().trim();
            String descricao = dadosRecebidos.get("descricao") != null ?
                    dadosRecebidos.get("descricao").toString().trim() : null;

            Tarefa novaTarefa = new Tarefa(titulo, descricao);

            tarefas.add(novaTarefa);

            // Log de debug
            System.out.println("Tarefa criada: " + novaTarefa);

            ctx.status(201); // Created
            ctx.json(novaTarefa);

        } catch (Exception e){

            ctx.status(400);
            ctx.json(Map.of("erro", "JSON Invalido" + e.getMessage()));
        }
    }


    /**
     * Endpoint GET /tarefas
     * Lista todas as tarefas criadas
     */
    public void listar(Context ctx) {
        try {
            List<Tarefa> todasTarefas = obterTodasTarefas();

            // Log para debug
            System.out.println("📋 Listando " + todasTarefas.size() + " tarefa(s)");

            ctx.json(todasTarefas);

        } catch (Exception e) {
            ctx.status(500);
            ctx.json(Map.of("erro", "Erro interno do servidor"));
        }
    }

    /**
     * Endpoint GET /tarefas/{id}
     * Busca uma tarefa específica pelo ID
     */
    public void buscar(Context ctx) {
        try {
            String id = ctx.pathParam("id");

            if (id == null || id.trim().isEmpty()) {
                ctx.status(400);
                ctx.json(Map.of("erro", "ID não pode estar vazio"));
                return;
            }

            Tarefa tarefa = buscarPorId(id);

            if (tarefa == null) {
                ctx.status(404); // Not Found
                ctx.json(Map.of("erro", "Tarefa não encontrada"));
                return;
            }

            // Log para debug
            System.out.println("🔍 Tarefa encontrada: " + tarefa);

            ctx.json(tarefa);

        } catch (Exception e) {
            ctx.status(500);
            ctx.json(Map.of("erro", "Erro interno do servidor"));
        }
    }

    public static List<Tarefa> obterTodasTarefas() {
        return new ArrayList<>(tarefas); // Retorna uma cópia para segurança
    }

    public static Tarefa buscarPorId(String id) {
        return tarefas.stream()
                .filter(tarefa -> tarefa.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
