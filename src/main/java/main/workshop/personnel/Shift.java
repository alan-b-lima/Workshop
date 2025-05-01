package main.workshop.personnel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe que representa um turno de trabalho.
 * 
 * @author Alan Lima
 */
public class Shift {

    /**
     * UNIX timestamp que representa o início do turno.
     * 
     * O valor padrão, 0, significa que o turno não tem início definido.
     */
    private long start;

    /**
     * UNIX timestamp que representa o fim do turno.
     * 
     * O valor padrão, 0, significa que o turno não tem fim definido.
     */
    private long end;

    /**
     * Construtor padrão.
     */
    public Shift() {

    }

    /**
     * Construtor que recebe o início do turno.
     * 
     * @param start UNIX timestamp que representa o início do turno.
     */
    public Shift(long start) {
        this(start, 0L);
    }

    /**
     * Construtor que recebe o início e o fim do turno.
     * 
     * @param start UNIX timestamp que representa o início do turno.
     * @param end   UNIX timestamp que representa o fim do turno.
     */
    public Shift(long start, long end) {
        this.setStart(start);
        this.setEnd(end);
    }

    /**
     * Construtor que recebe o início do turno como objeto {@code Date}.
     * 
     * @param start objeto {@code Date} que representa o início do turno.
     * 
     * @see java.util.Date
     */
    public Shift(Date start) {
        this(start.getTime(), 0L);
    }

    /**
     * Construtor que recebe o início e o fim do turno como objetos {@code Date}.
     * 
     * @param start objeto {@code Date} que representa o início do turno.
     * @param end   objeto {@code Date} que representa o fim do turno.
     * 
     * @see java.util.Date
     */
    public Shift(Date start, Date end) {
        this(start.getTime(), end.getTime());
    }

    /**
     * Formato de data utilizado para representar o início e o fim do turno.
     * 
     * @see java.text.SimpleDateFormat
     */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    /**
     * Retorna o início do turno.
     * 
     * @return UNIX timestamp que representa o início do turno.
     */
    public long getStart() {
        return start;
    }

    /**
     * Retorna o início do turno como um objeto {@code Date}.
     * 
     * @return objeto {@code Date} que representa o início do turno.
     * 
     * @see java.util.Date
     */
    public Date getStartDate() {
        return new Date(start);
    }

    /**
     * Define o início do turno.
     * 
     * @param start UNIX timestamp que representa o início do turno.
     */
    public void setStart(long start) {
        this.start = start;
    }

    /**
     * Define o início do turno como um objeto {@code Date}.
     * 
     * @param start objeto {@code Date} que representa o início do turno.
     * 
     * @see java.util.Date
     */
    public void setStart(Date start) {
        this.start = start.getTime();
    }

    /**
     * Define o início do turno para o horário atual.
     */
    public void setStartNow() {
        this.start = System.currentTimeMillis();
    }

    /**
     * Retorna o fim do turno.
     * 
     * @return UNIX timestamp que representa o fim do turno.
     */
    public long getEnd() {
        return end;
    }

    /**
     * Retorna o fim do turno como um objeto {@code Date}.
     * 
     * @return objeto {@code Date} que representa o fim do turno.
     * 
     * @see java.util.Date
     */
    public Date getEndDate() {
        return new Date(end);
    }

    /**
     * Define o fim do turno.
     * 
     * @param end UNIX timestamp que representa o fim do turno.
     */
    public void setEnd(long end) {
        this.end = end;
    }

    /**
     * Define o fim do turno como um objeto {@code Date}.
     * 
     * @param end objeto {@code Date} que representa o fim do turno.
     * 
     * @see java.util.Date
     */
    public void setEnd(Date end) {
        this.end = end.getTime();
    }

    /**
     * Define o fim do turno para o horário atual.
     */
    public void setEndNow() {
        this.end = System.currentTimeMillis();
    }

    /**
     * Retorna uma representação textual do turno.
     * 
     * @return representação textual do turno.
     */
    @Override
    public String toString() {
        String start = "...", end = "...";

        if (this.start != 0) {
            start = sdf.format(this.start);
        }

        if (this.end != 0) {
            end = sdf.format(this.end);
        }

        return String.format("{%s, %s}", start, end);
    }
}