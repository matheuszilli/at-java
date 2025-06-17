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
 * Testes unitários para HelloController
 * Usa HttpClient do Java (mais robusta que JavalinTest)
 */
public class HelloControllerTest {

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

        System.out.println("****** Servidor de teste iniciado em: " + baseUrl);
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
     * Teste do endpoint GET /hello
     * Valida status 200 e resposta "Hello, Javalin!"
     */
    @Test
    public void deveRetornarHelloJavalin() throws Exception {
        // Arrange (Preparação)
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/hello"))
                .GET()
                .build();

        // Act (Ação)
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        // Assert (Verificação)
        assertEquals(200, response.statusCode(),
                "Status deveria ser 200 (OK)");

        assertEquals("Hello, Javalin!", response.body(),
                "Resposta deveria ser 'Hello, Javalin!'");

        // Log para debug
        System.out.println("✅ Teste /hello: " + response.statusCode() +
                " - " + response.body());
    }

    /**
     * Teste de endpoint inexistente (deve retornar 404)
     */
    @Test
    public void deveRetornar404ParaEndpointInexistente() throws Exception {
        // Arrange
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/endpoint-que-nao-existe"))
                .GET()
                .build();

        // Act
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        // Assert
        assertEquals(404, response.statusCode(),
                "Endpoint inexistente deveria retornar 404");

        System.out.println("✅ Teste 404: " + response.statusCode());
    }
}