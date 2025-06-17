package org.example.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


/**
* Classe utilitária para manipulação de datas e horários.
* evitar duplicação de código
*/
public class DateUtils {

    private static final ZoneId SAO_PAULO_ZONE = ZoneId.of("America/Sao_Paulo");

    public static String obterTimestampAtual() {
        ZonedDateTime now = ZonedDateTime.now(SAO_PAULO_ZONE);
        return now.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public static ZoneId obterZonaSaoPaulo() {
        return SAO_PAULO_ZONE;
    }
}
