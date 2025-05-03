package main.workshop.service;

import java.util.ArrayList;
import java.util.TreeSet;

import main.workshop.common.DateSpan;

/**
 * Classe que representa um elevador.
 * 
 * @author Alan Lima
 */
public class Elevator {

    /**
     * Conjunto de turnos de trabalho do elevador.
     */
    private TreeSet<DateSpan> schedule;

    /**
     * Construtor padrão.
     */
    public Elevator() {
        schedule = new TreeSet<>();
    }

    /**
     * Retorna os turnos de trabalho do elevador.
     * 
     * @return um array de turnos de trabalho do elevador.
     */
    public DateSpan[] get() {
        DateSpan[] dateSpans = new DateSpan[schedule.size()];

        int i = 0;
        for (DateSpan dateSpan : this.schedule) {
            dateSpans[i++] = dateSpan;
        }

        return dateSpans;
    }

    /**
     * Retorna os turnos de trabalho do elevador que estão dentro do intervalo
     * especificado.
     * 
     * @param start timestamp UNIX que representa o início do intervalo.
     * @param end   timestamp UNIX que representa o fim do intervalo.
     * @return um array de turnos de trabalho que estão dentro do intervalo
     *         especificado.
     */
    public DateSpan[] get(long start, long end) {
        ArrayList<DateSpan> dateSpans = new ArrayList<>();

        for (DateSpan dateSpan : this.schedule) {
            if (dateSpan.getStart() >= start && dateSpan.getEnd() <= end) {
                dateSpans.add(dateSpan);
            }
        }

        return (DateSpan[]) dateSpans.toArray();
    }

    /**
     * Adiciona um turno de trabalho ao elevador.
     * 
     * @param start timestamp UNIX que representa o início do turno.
     * @param end   timestamp UNIX que representa o fim do turno.
     */
    public void add(DateSpan dateSpan) {
        schedule.add(dateSpan);
    }

    /**
     * Adiciona um turno de trabalho ao elevador.
     * 
     * @param start timestamp UNIX que representa o início do turno.
     * @param end   timestamp UNIX que representa o fim do turno.
     */
    public void add(long start, long end) {
        schedule.add(new DateSpan(start, end));
    }
}