package edu.ajan.model.workshop.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Classe de utilidades para datas.
 * 
 * @author Alan Lima
 */
public final class Dates {

    /**
     * Formato de data padrão para o formato "dd/MM/yyyy HH:mm".
     * 
     * @see java.text.SimpleDateFormat
     */
    private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    /**
     * Formato de data padrão para o formato "dd/MM/yyyy".
     * 
     * @see java.text.SimpleDateFormat
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Padrão RegEx que casa uma data no formato "xx/xx/xxxx xx:xx", sendo x um
     * dígito decimal qualquer.
     */
    private static final Pattern DATETIME_PATTERN = Pattern.compile("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}");

    /**
     * Padrão RegEx que casa uma data no formato "xx/xx/xxxx", sendo x um dígito
     * decimal qualquer.
     */
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");

    /**
     * Milissegundos por segundo.
     */
    private static final long MILLISECONDS_PER_SECOND = 1000;

    /**
     * Milissegundos por minuto.
     */
    private static final long MILLISECONDS_PER_MINUTE = 60 * MILLISECONDS_PER_SECOND;

    /**
     * Milissegundos por hora.
     */
    private static final long MILLISECONDS_PER_HOUR = 60 * MILLISECONDS_PER_MINUTE;

    /**
     * Milissegundos por dia, considerando um dia de 24 horas exatas.
     */
    private static final long MILLISECONDS_PER_DAY = 24 * MILLISECONDS_PER_HOUR;

    /**
     * Construtor privado.
     */
    private Dates() {

    }

    /**
     * Converte uma data no formato "dd/MM/yyyy" ou "dd/MM/yyyy HH:mm" para o tempo
     * elapsado, em milissegundos, desde 1° de janeiro de 1970, 00:00:00 UTC.
     * 
     * @param dateString data no formato "dd/MM/yyyy" ou "dd/MM/yyyy HH:mm".
     * @return timestamp correspondente à data.
     */
    public static long parse(String dateString) {

        Objects.requireNonNull(dateString, "data não pode ser nula");

        try {

            if (DATETIME_PATTERN.matcher(dateString).matches()) {
                return DATETIME_FORMAT.parse(dateString).getTime();
            }

            if (DATE_PATTERN.matcher(dateString).matches()) {
                return DATE_FORMAT.parse(dateString).getTime();
            }

        } catch (ParseException _) {

        }

        return -1;
    }

    /**
     * Converte um timestamp para uma data no formato "dd/MM/yyyy HH:mm".
     * 
     * @param timestamp timestamp correspondente à data.
     * @return data no formato "dd/MM/yyyy HH:mm".
     */
    public static String formatAsDateTime(long timestamp) {
        return DATETIME_FORMAT.format(timestamp);
    }

    /**
     * Converte um timestamp para uma data no formato "dd/MM/yyyy".
     * 
     * @param timestamp timestamp correspondente à data.
     * @return data no formato "dd/MM/yyyy".
     */
    public static String formatAsDate(long timestamp) {
        return DATE_FORMAT.format(timestamp);
    }

    public static String formatAsInterval(long interval) {
        long days = interval / MILLISECONDS_PER_DAY;
        interval -= days * MILLISECONDS_PER_DAY;

        long hours = interval / MILLISECONDS_PER_HOUR;
        interval -= hours * MILLISECONDS_PER_HOUR;

        long minutes = interval / MILLISECONDS_PER_MINUTE;
        interval -= minutes * MILLISECONDS_PER_MINUTE; // Desnecessário

        String formattedDays = "";

        if (days == 1) {
            formattedDays = "1 dia, ";
        } else

        if (days != 0) {
            formattedDays = Long.toString(days) + " dias, ";
        }

        return formattedDays + String.format("%02d:%02d", hours, minutes);
    }

    /**
     * Retorna o timestamp atual, isto é, o tempo elapsado, em milissegundos, desde
     * 1° de janeiro de 1970, 00:00:00 UTC.
     * 
     * @return timestamp atual.
     */
    public static long now() {
        return System.currentTimeMillis();
    }
}
