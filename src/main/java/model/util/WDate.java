package model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.workshop.exception.WorkshopException;

/**
 * Classe utilitária para conversão de datas.
 * 
 * @author Alan Lima
 */
public final class WDate {

    /**
     * Construtor privado para evitar instanciação da classe.
     */
    private WDate() {

    }

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
     * Mensagem de erro padrão para data inválida.
     */
    private static final String INVALID_DATE = "Data inválida - data deve estar no formato \"dd/MM/yyyy\" ou \"dd/MM/yyyy HH:mm\"!";

    /**
     * Converte uma data no formato "dd/MM/yyyy" ou "dd/MM/yyyy HH:mm" para um UNIX
     * timestamp.
     * Um timestamp UNIX é o número de milissegundos desde 1 de janeiro de 1970,
     * 00:00:00.
     * 
     * @param dateString data no formato "dd/MM/yyyy" ou "dd/MM/yyyy HH:mm"
     * @return timestamp UNIX correspondente à data
     * 
     * @throws WorkshopException caso a data não esteja no formato esperado ou
     *                           seja inválida.
     */
    public static long parse(String dateString) throws WorkshopException {
        if (dateString == null || dateString.isEmpty()) {
            throw new WorkshopException("Data inválida - data não pode ser nula ou vazia!");
        }

        if (dateString.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}") == true) {
            try {
                return DATETIME_FORMAT.parse(dateString).getTime();
            } catch (ParseException e) {
                throw new WorkshopException(INVALID_DATE);
            }
        }

        if (dateString.matches("\\d{2}/\\d{2}/\\d{4}") == true) {
            try {
                return DATE_FORMAT.parse(dateString).getTime();
            } catch (ParseException e) {
                throw new WorkshopException(INVALID_DATE);
            }
        }

        throw new WorkshopException(INVALID_DATE);
    }

    /**
     * Converte um objeto {@code Date} para um UNIX timestamp.
     * Um timestamp UNIX é o número de milissegundos desde 1 de janeiro de 1970,
     * 00:00:00.
     * 
     * @param date objeto {@code Date} correspondente à data
     * @return timestamp UNIX correspondente à data
     * 
     * @throws WorkshopException caso a data seja nula.
     */
    public static long parse(Date date) throws WorkshopException {
        if (date == null) {
            throw new WorkshopException("Data inválida - data não pode ser nula!");
        }

        return date.getTime();
    }

    /**
     * Converte um timestamp UNIX para uma data no formato "dd/MM/yyyy HH:mm".
     * 
     * @param timestamp timestamp UNIX correspondente à data.
     * @return data no formato "dd/MM/yyyy HH:mm".
     */
    public static String formatDateTime(long timestamp) {
        return DATETIME_FORMAT.format(timestamp);
    }

    /**
     * Converto objeto {@code Date} para uma data no formato "dd/MM/yyyy HH:mm".
     * 
     * @param date objeto {@code Date} correspondente à data.
     * @return data no formato "dd/MM/yyyy HH:mm".
     * 
     * @see java.util.Date
     */
    public static String formatDateTime(Date date) {
        return DATETIME_FORMAT.format(date);
    }

    /**
     * Converte um timestamp UNIX para uma data no formato "dd/MM/yyyy".
     * 
     * @param timestamp timestamp UNIX correspondente à data.
     * @return data no formato "dd/MM/yyyy".
     */
    public static String formatDate(long timestamp) {
        return DATE_FORMAT.format(timestamp);
    }

    /**
     * Converto objeto {@code Date} para uma data no formato "dd/MM/yyyy".
     * 
     * @param date objeto {@code Date} correspondente à data.
     * @return data no formato "dd/MM/yyyy".
     * 
     * @see java.util.Date
     */
    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    /**
     * Retorna o timestamp UNIX atual.
     * 
     * @return timestamp UNIX atual.
     */
    public static long now() {
        return System.currentTimeMillis();
    }
}
