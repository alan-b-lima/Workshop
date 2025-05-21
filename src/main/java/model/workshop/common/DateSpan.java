package model.workshop.common;

import java.util.Comparator;

import model.util.WDate;

/**
 * Classe que representa um turno de trabalho.
 * 
 * @author Alan Lima
 */
public class DateSpan implements Comparator<DateSpan> {

    /**
     * Timestamp UNIX que representa o início do turno de trabalho.
     * 
     * O valor padrão, 0, significa que o turno não tem início definido.
     */
    private long start;

    /**
     * Timestamp UNIX que representa o fim do turno de trabalho.
     * 
     * O valor padrão, 0, significa que o turno não tem fim definido.
     */
    private long end;

    /**
     * Construtor padrão.
     */
    public DateSpan() {

    }

    /**
     * Construtor que recebe o início do turno.
     * 
     * @param start timestamp UNIX que representa o início do turno.
     */
    public DateSpan(long start) {
        this(start, 0L);
    }

    /**
     * Construtor que recebe o início e o fim do turno.
     * 
     * @param start timestamp UNIX que representa o início do turno.
     * @param end   timestamp UNIX que representa o fim do turno.
     */
    public DateSpan(long start, long end) {
        this.setStart(start);
        this.setEnd(end);
    }

    /**
     * Retorna o início do turno.
     * 
     * @return timestamp UNIX que representa o início do turno.
     */
    public long getStart() {
        return start;
    }

    /**
     * Define o início do turno.
     * 
     * @param start timestamp UNIX que representa o início do turno.
     */
    public void setStart(long start) {
        this.start = start;
    }

    /**
     * Define o início do turno para o horário atual.
     */
    public void setStartNow() {
        this.start = WDate.now();
    }

    /**
     * Retorna o fim do turno.
     * 
     * @return timestamp UNIX que representa o fim do turno.
     */
    public long getEnd() {
        return end;
    }

    /**
     * Define o fim do turno.
     * 
     * @param end timestamp UNIX que representa o fim do turno.
     */
    public void setEnd(long end) {
        this.end = end;
    }

    /**
     * Define o fim do turno para o horário atual.
     */
    public void setEndNow() {
        this.end = WDate.now();
    }

    /**
     * Compara dois turnos de trabalho.
     * 
     * Uma comparação é feita primeiro pelo início do turno e, em caso de empate,
     * pelo fim do turno.
     */
    @Override
    public int compare(DateSpan o1, DateSpan o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }

        if (o1 == null) {
            return 1;
        }

        if (o2 == null) {
            return -1;
        }

        if (o1.start == o2.start) {
            return Long.compare(o1.end, o2.end);
        }

        return Long.compare(o1.start, o2.start);
    }

    /**
     * Compara dois turnos de trabalho.
     * 
     * Uma comparação é feita primeiro pelo início do turno e, em caso de empate,
     * pelo fim do turno.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        return compare(this, (DateSpan) obj) == 0;
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
            start = WDate.formatDate(this.start);
        }

        if (this.end != 0) {
            end = WDate.formatDate(this.end);
        }

        return String.format("{%s, %s}", start, end);
    }
}