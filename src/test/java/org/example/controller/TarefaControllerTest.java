package org.example.controller;

import io.javalin.Javalin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.config.Routes;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para TarefaController
 * Testa criação, listagem e busca de tarefas
 */
public class TarefaControllerTest {

    private Javalin app;
    private HttpClient client;
    private String baseUrl;

    /**
     * Configuração que roda ANTES de cada teste
     * Sobe um servidor de teste em porta aleatória
     */
    @BeforeEach
    public void setUp() {
        // Cria o app Javalin
        app = Javalin.create(config -> {
            config.showJavalinBanner = false;
        });

        // Configura as rotas
        Routes.configure(app);

        // Inicia o servidor em porta aleatória
        app.start(0);

        // Constrói a URL base
        baseUrl = "http://localhost:" + app.port();

        // Cria cliente HTTP
        client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        System.out.println(" ***** Servidor de teste iniciado em: " + baseUrl);
    }

    /**
     * Limpeza que roda APÓS cada teste
     * Para o servidor de teste
     */
    @AfterEach
    public void tearDown() {
        if (app != null) {
            app.stop();
            System.out.println("🛑 Servidor de teste parado");
        }
    }

    /**
     * Teste do endpoint POST /tarefas
     * Simula criação de nova tarefa e verifica se retorna status 201
     */
    @Test
    public void deveCriarTarefaComSucesso() throws Exception {
        // Arrange (Preparação)
        String jsonTarefa = """
                {
                    "titulo": "Estudar Java",
                    "descricao": "Fazer exercícios de Javalin"
                }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/tarefas"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonTarefa))
                .build();

        // Act (Ação)
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        // Assert (Verificação)
        assertEquals(201, response.statusCode(),
                "Status deveria ser 201 (Created) ao criar tarefa");

        // Verifica se a resposta não está vazia
        assertNotNull(response.body(),
                "Resposta não deveria estar vazia");

        assertFalse(response.body().isEmpty(),
                "Resposta não deveria estar vazia");

        // Verifica se a resposta contém os dados esperados
        String responseBody = response.body();
        assertTrue(responseBody.contains("\"titulo\":\"Estudar Java\""),
                "Resposta deveria conter o título enviado");

        assertTrue(responseBody.contains("\"descricao\":\"Fazer exercícios de Javalin\""),
                "Resposta deveria conter a descrição enviada");

        assertTrue(responseBody.contains("\"concluida\":false"),
                "Tarefa deveria ser criada como não concluída");

        assertTrue(responseBody.contains("\"id\":"),
                "Resposta deveria conter um ID gerado");

        assertTrue(responseBody.contains("\"dataCriacao\":"),
                "Resposta deveria conter data de criação");

        // Log para debug
        System.out.println("✅ Teste POST /tarefas: " + response.statusCode());
        System.out.println("Tarefa criada: " + responseBody);
    }

    /**
     * Teste do endpoint POST /tarefas apenas com título (descrição opcional)
     * Verifica se criação funciona com dados mínimos
     */
    @Test
    public void deveCriarTarefaApenasComTitulo() throws Exception {
        // Arrange - JSON só com título
        String jsonTarefa = """
                {
                    "titulo": "Tarefa simples"
                }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/tarefas"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonTarefa))
                .build();

        // Act
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        // Assert
        assertEquals(201, response.statusCode(),
                "Status deveria ser 201 mesmo sem descrição");

        String responseBody = response.body();
        assertTrue(responseBody.contains("\"titulo\":\"Tarefa simples\""),
                "Resposta deveria conter o título");

        assertTrue(responseBody.contains("\"descricao\":null"),
                "Descrição deveria ser null quando não informada");

        System.out.println("✅ Teste POST /tarefas (só título): " + response.statusCode());
        System.out.println("Tarefa criada: " + responseBody);
    }

    /**
     * Teste de erro: POST /tarefas sem título obrigatório
     * Verifica se retorna erro 400 quando título não é informado
     */
    @Test
    public void deveRetornarErroSemTitulo() throws Exception {
        // Arrange - JSON sem título
        String jsonInvalido = """
                {
                    "descricao": "Só descrição, sem título"
                }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/tarefas"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonInvalido))
                .build();

        // Act
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        // Assert
        assertEquals(400, response.statusCode(),
                "Status deveria ser 400 (Bad Request) sem título");

        String responseBody = response.body();
        assertTrue(responseBody.contains("erro"),
                "Resposta deveria conter campo 'erro'");

        assertTrue(responseBody.contains("titulo") && responseBody.contains("obrigatório"),
                "Erro deveria mencionar que título é obrigatório");

        System.out.println("✅ Teste POST /tarefas (erro): " + response.statusCode());
        System.out.println("❌ Erro esperado: " + responseBody);
    }

    /**
     * Teste de erro: POST /tarefas com JSON malformado
     * Verifica tratamento de JSON inválido
     */
    @Test
    public void deveRetornarErroJsonInvalido() throws Exception {
        // Arrange - JSON malformado
        String jsonMalformado = "{ titulo sem aspas }";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/tarefas"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonMalformado))
                .build();

        // Act
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        // Assert
        assertEquals(400, response.statusCode(),
                "Status deveria ser 400 para JSON malformado");

        String responseBody = response.body();
        assertTrue(responseBody.contains("erro") && responseBody.contains("JSON"),
                "Erro deveria mencionar problema com JSON");

        System.out.println("✅ Teste POST /tarefas (JSON inválido): " + response.statusCode());
        System.out.println("❌ Erro esperado: " + responseBody);
    }

    /**
     * Teste do endpoint GET /tarefas/{id}
     * Cria uma tarefa e depois busca ela pelo ID para verificar se foi recuperada corretamente
     */
    @Test
    public void deveBuscarTarefaPorId() throws Exception {
        // ========== ETAPA 1: Criar uma tarefa (POST) ==========
        String jsonTarefa = """
        {
            "titulo": "Tarefa para buscar",
            "descricao": "Teste de busca por ID"
        }
        """;

        HttpRequest createRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/tarefas"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonTarefa))
                .build();

        HttpResponse<String> createResponse = client.send(createRequest,
                HttpResponse.BodyHandlers.ofString());

        // Verifica se a criação foi bem-sucedida
        assertEquals(201, createResponse.statusCode(),
                "Criação da tarefa deveria retornar 201");

        // Extrai o ID da tarefa criada
        String createResponseBody = createResponse.body();
        String tarefaId = extrairIdDaResposta(createResponseBody);

        assertNotNull(tarefaId, "ID da tarefa não deveria ser null");
        assertFalse(tarefaId.isEmpty(), "ID da tarefa não deveria estar vazio");

        System.out.println("Tarefa criada com ID: " + tarefaId);

        // ========== ETAPA 2: Buscar a tarefa pelo ID (GET) ==========
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/tarefas/" + tarefaId))
                .GET()
                .build();

        HttpResponse<String> getResponse = client.send(getRequest,
                HttpResponse.BodyHandlers.ofString());

        // ========== ETAPA 3: Verificar se encontrou corretamente ==========
        assertEquals(200, getResponse.statusCode(),
                "Busca por ID válido deveria retornar 200");

        String getResponseBody = getResponse.body();

        // Verifica se os dados estão corretos
        assertTrue(getResponseBody.contains("\"titulo\":\"Tarefa para buscar\""),
                "Resposta deveria conter o título correto");

        assertTrue(getResponseBody.contains("\"descricao\":\"Teste de busca por ID\""),
                "Resposta deveria conter a descrição correta");

        assertTrue(getResponseBody.contains("\"id\":\"" + tarefaId + "\""),
                "Resposta deveria conter o ID correto");

        assertTrue(getResponseBody.contains("\"concluida\":false"),
                "Tarefa deveria estar como não concluída");

        assertTrue(getResponseBody.contains("\"dataCriacao\":"),
                "Resposta deveria conter data de criação");

        System.out.println("✅ Teste GET /tarefas/{id}: " + getResponse.statusCode());
        System.out.println("***** Tarefa encontrada: " + getResponseBody);
    }

    /**
     * Teste do endpoint GET /tarefas/{id} com ID inexistente
     * Verifica se retorna 404 quando ID não existe
     */
    @Test
    public void deveRetornar404ParaIdInexistente() throws Exception {
        // ID que sabemos que não existe
        String idInexistente = "00000000-0000-0000-0000-000000000000";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/tarefas/" + idInexistente))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        // Verifica se retorna 404
        assertEquals(404, response.statusCode(),
                "ID inexistente deveria retornar 404");

        String responseBody = response.body();
        assertTrue(responseBody.contains("erro"),
                "Resposta deveria conter campo 'erro'");

        assertTrue(responseBody.contains("não encontrada") || responseBody.contains("not found"),
                "Erro deveria indicar que tarefa não foi encontrada");

        System.out.println("✅ Teste GET /tarefas/{id} (404): " + response.statusCode());
        System.out.println("❌ Erro esperado: " + responseBody);
    }

    /**
     * Método auxiliar para extrair o ID da resposta JSON
     * Busca pelo padrão "id":"uuid-aqui" na resposta
     */
    private String extrairIdDaResposta(String jsonResponse) {
        // Procura pelo padrão: "id":"algum-uuid"
        // Regex para encontrar UUID após "id":"
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
                "\"id\"\\s*:\\s*\"([a-f0-9-]{36})\""
        );

        java.util.regex.Matcher matcher = pattern.matcher(jsonResponse);

        if (matcher.find()) {
            return matcher.group(1); // Retorna o UUID capturado
        }

        // Se não encontrou com regex, tenta abordagem manual
        int idStart = jsonResponse.indexOf("\"id\":\"") + 6;
        if (idStart > 5) { // Se encontrou "id":"
            int idEnd = jsonResponse.indexOf("\"", idStart);
            if (idEnd > idStart) {
                return jsonResponse.substring(idStart, idEnd);
            }
        }

        throw new RuntimeException("Não foi possível extrair ID da resposta: " + jsonResponse);
    }

    /**
     * Teste do endpoint GET /tarefas (listagem)
     * Cria algumas tarefas e verifica se a listagem retorna array não vazio
     */
    @Test
    public void deveListarTodasAsTarefas() throws Exception {
        // ETAPA 1: Criar terafas
        String tarefa1 = """
        {
            "titulo": "Primeira tarefa",
            "descricao": "Descrição da primeira"
        }
        """;

        String tarefa2 = """
        {
            "titulo": "Segunda tarefa"
        }
        """;

        // Cria tarefa 1
        HttpRequest createRequest1 = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/tarefas"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(tarefa1))
                .build();

        HttpResponse<String> createResponse1 = client.send(createRequest1,
                HttpResponse.BodyHandlers.ofString());
        assertEquals(201, createResponse1.statusCode(), "Primeira tarefa deveria ser criada");

        // Cria tarefa 2
        HttpRequest createRequest2 = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/tarefas"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(tarefa2))
                .build();

        HttpResponse<String> createResponse2 = client.send(createRequest2,
                HttpResponse.BodyHandlers.ofString());
        assertEquals(201, createResponse2.statusCode(), "Segunda tarefa deveria ser criada");

        System.out.println("Duas tarefas criadas para teste de listagem");

        // ETAPA 2: Listar todas as tarefas
        HttpRequest listRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/tarefas"))
                .GET()
                .build();

        HttpResponse<String> listResponse = client.send(listRequest,
                HttpResponse.BodyHandlers.ofString());

        // ========== ETAPA 3: Verificar a listagem ==========
        assertEquals(200, listResponse.statusCode(),
                "Listagem deveria retornar status 200");

        String responseBody = listResponse.body();

        // Verifica se é um array JSON
        assertTrue(responseBody.startsWith("[") && responseBody.endsWith("]"),
                "Resposta deveria ser um array JSON");

        // Verifica se não está vazio
        assertFalse(responseBody.equals("[]"),
                "Array não deveria estar vazio após criar tarefas");

        // Verifica se contém as tarefas criadas
        assertTrue(responseBody.contains("\"titulo\":\"Primeira tarefa\""),
                "Lista deveria conter a primeira tarefa");

        assertTrue(responseBody.contains("\"titulo\":\"Segunda tarefa\""),
                "Lista deveria conter a segunda tarefa");

        // Conta quantas tarefas foram retornadas (simples: conta "id":")
        int quantidadeTarefas = responseBody.split("\"id\":").length - 1;

        assertTrue(quantidadeTarefas >= 2,
                "Deveria retornar pelo menos 2 tarefas, mas retornou: " + quantidadeTarefas);

        System.out.println("✅ Teste GET /tarefas: " + listResponse.statusCode());
        System.out.println("Tarefas listadas (" + quantidadeTarefas + "): " + responseBody);
    }
}