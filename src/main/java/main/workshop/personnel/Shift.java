package main.workshop.personnel;

import java.util.Date;

/**
 * Classe que representa um turno de trabalho.
 * 
 * @author Alan Lima
 */
public class Shift {

    /**
     * UNIX timestamp que representa o início do turno.
     */
    private long start;

    /**
     * UNIX timestamp que representa o fim do turno.
     */
    private long end;

    /**
     * Construtor padrão
     */
    public Shift() {

    }

    /**
     * Construtor que recebe o início do turno.
     * 
     * @param start UNIX timestamp que representa o início do turno.
     */
    public Shift(long start) {
        this.setStart(start);
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
     * Retorna o início do turno.
     * 
     * @return UNIX timestamp que representa o início do turno.
     */
    public long getStart() {
        return start;
    }

    /**
     * Retorna o início do turno como um objeto Date.
     * 
     * @return objeto Date que representa o início do turno.
     * @see java.util.Date
     */
    public Date getStartDate() {
        return new Date(start);
    }

    /**
     * Altera o início do turno.
     * 
     * @param start UNIX timestamp que representa o início do turno.
     */
    public void setStart(long start) {
        this.start = start;
    }

    /**
     * Altera o início do turno para o horário atual.
     */
    public void setStartNow() {
        this.end = Shift.now();
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
     * Retorna o fim do turno como um objeto Date.
     * 
     * @return objeto Date que representa o fim do turno.
     * @see java.util.Date
     */
    public Date getEndDate() {
        return new Date(end);
    }

    /**
     * Altera o fim do turno.
     * 
     * @param end UNIX timestamp que representa o fim do turno.
     */
    public void setEnd(long end) {
        this.end = end;
    }

    /**
     * Altera o fim do turno para o horário atual.
     */
    public void setEndNow() {
        this.end = Shift.now();
    }

    /**
     * Retorna a duração do turno em milissegundos.
     * 
     * @return duração do turno em milissegundos.
     */
    public static long now() {
        return System.currentTimeMillis();
    }
}