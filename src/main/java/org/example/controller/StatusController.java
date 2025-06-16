package org.example.controller;

import io.javalin.http.Context;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

        // Definir a zona de São Paulo
        ZoneId saoPauloZone = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime nowInSaoPaulo = ZonedDateTime.now(saoPauloZone);

        // Timestamp ISO 8601
        String timstamp = nowInSaoPaulo.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        response.put("timestamp", timstamp);

        ctx.json(response);
    }
}
