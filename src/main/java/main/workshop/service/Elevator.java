package main.workshop.service;

import java.util.ArrayList;
import java.util.TreeSet;

import main.workshop.common.DateSpan;
import main.workshop.exception.WorkshopException;

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

    private double weightLimit;

    /**
     * Construtor padrão.
     */
    public Elevator() {
        schedule = new TreeSet<>();
    }

    /**
     * Construtor que inicializa o elevador com um limite de peso.
     * 
     * @param weightLimit limite de peso do elevador.
     */
    public Elevator(double weightLimit) {
        this.weightLimit = weightLimit;
        schedule = new TreeSet<>();
    }

    /**
     * Retorna os turnos de trabalho do elevador.
     * 
     * @return um array de turnos de trabalho do elevador.
     */
    public DateSpan[] getSchedule() {
        return (DateSpan[]) schedule.toArray();
    }

    /**
     * Retorna os turnos de trabalho do elevador contidos do intervalo
     * [{@code start}, {@code end}].
     * 
     * @param start timestamp UNIX que representa o início do intervalo.
     * @param end   timestamp UNIX que representa o fim do intervalo.
     * @return um array de turnos de trabalho que estão dentro do intervalo
     *         especificado.
     */
    public DateSpan[] getTasksExclusive(long start, long end) {
        ArrayList<DateSpan> dateSpans = new ArrayList<>();

        for (DateSpan dateSpan : this.schedule) {
            if (start <= dateSpan.getStart()) {
                if (end < dateSpan.getEnd()) {
                    break;
                }

                dateSpans.add(dateSpan);
            }
        }

        return (DateSpan[]) dateSpans.toArray();
    }

    /**
     * Retorna os turnos de trabalho do elevador com intersecção não vazia com o
     * intervalo [{@code start}, {@code end}].
     * 
     * @param start timestamp UNIX que representa o início do intervalo.
     * @param end   timestamp UNIX que representa o fim do intervalo.
     * @return um array de turnos de trabalho que estão dentro do intervalo
     *         especificado.
     */
    public DateSpan[] getTasksInclusive(long start, long end) {
        ArrayList<DateSpan> dateSpans = new ArrayList<>();

        for (DateSpan dateSpan : this.schedule) {
            if (start <= dateSpan.getStart()) {
                if (end < dateSpan.getEnd()) {
                    break;
                }

                if (dateSpan.getStart() <= end) {
                    dateSpans.add(dateSpan);
                }
            } else if (dateSpan.getStart() <= end) {
                dateSpans.add(dateSpan);
            }
        }

        return (DateSpan[]) dateSpans.toArray();
    }

    /**
     * Adiciona um turno de trabalho ao elevador.
     * 
     * @param dateSpan turno de trabalho a ser adicionado.
     */
    public void addTask(DateSpan dateSpan) {
        schedule.add(dateSpan);
    }

    /**
     * Adiciona um turno de trabalho ao elevador, caso não haja intersecção com os
     * turnos já existentes.
     * 
     * @param dateSpan turno de trabalho a ser adicionado.
     * @return {@code true} se o turno foi adicionado, {@code false} caso contrário.
     */
    public boolean addNonOverlappingTask(DateSpan dateSpan) {
        if (schedule.isEmpty()) {
            schedule.add(dateSpan);
            return true;
        }

        DateSpan[] tasks = getTasksInclusive(dateSpan.getStart(), dateSpan.getEnd());

        if (tasks.length == 0) {
            schedule.add(dateSpan);
            return true;
        }

        return false;
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

    /**
     * Retorna o limite de peso do elevador.
     * 
     * @return limite de peso do elevador.
     */
    public double getWeightLimit() {
        return weightLimit;
    }

    /**
     * Define o limite de peso do elevador.
     * 
     * @param weightLimit limite de peso do elevador.
     * 
     * @throws WorkshopException caso o limite de peso seja negativo.
     */
    public void setWeightLimit(double weightLimit) throws WorkshopException {
        if (weightLimit < 0) {
            throw new WorkshopException("Peso inválido - o peso não pode ser negativo.");
        }

        this.weightLimit = weightLimit;
    }
}