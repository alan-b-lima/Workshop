package main.workshop.service;

import java.util.TreeSet;

import main.workshop.exception.WorkshopException;

/**
 * Classe que representa a agenda da Oficina.
 * 
 * @author Alan Lima
 */
public class Scheduler {

    /**
     * Conjunto de agendamentos e manutenções contidos na agenda.
     */
    private TreeSet<Scheduling> schedule;

    /**
     * Conjunto de elevadores disponíveis na oficina.
     */
    private Elevator[] elevators;

    /**
     * Construtor padrão.
     */
    public Scheduler() {
        this.schedule = new TreeSet<>();
        this.elevators = new Elevator[] {
                new Elevator(1000),
                new Elevator(2000),
                new Elevator(3000)
        };
    }

    /**
     * Retorna os agendamentos e manutenções contidos na agenda.
     * 
     * @return um array de agendamentos e manutenções contidos na agenda.
     */
    public Scheduling[] getSchedule() {
        return (Scheduling[]) schedule.toArray();
    }

    /**
     * Retorna os agendamentos e manutenções contidos na agenda do intervalo
     * [{@code start}, {@code end}].
     * 
     * @param start timestamp UNIX que representa o início do intervalo.
     * @param end   timestamp UNIX que representa o fim do intervalo.
     * @return um array de agendamentos e manutenções que estão dentro do intervalo
     *         especificado.
     */
    public Scheduling[] getTasksInclusive(long start, long end) {
        Scheduling[] tasks = new Scheduling[schedule.size()];
        int i = 0;

        for (Scheduling task : schedule) {
            if (task.getDate().getStart() <= end && task.getDate().getEnd() >= start) {
                tasks[i++] = task;
            }
        }

        return tasks;
    }

    /**
     * Adiciona um novo agendamento à agenda.
     * 
     * @param scheduling turno de trabalho a ser adicionado.
     */
    public void addScheduling(Scheduling scheduling) {
        schedule.add(scheduling);
    }



    /**
     * Adiciona uma nova manutenção à agenda.
     * 
     * @param maintenance manutenção a ser adicionada.
     */
    public void addMaintenance(Maintenance maintenance) {
        schedule.add(maintenance);
    }

    /**
     * Adiciona um novo turno de trabalho à agenda.
     * 
     * @return true se o turno de trabalho foi adicionado com sucesso
     */
    public Elevator[] getElevators() {
        return elevators;
    }

    /**
     * Retorna os turnos de trabalho do elevador contidos do intervalo
     * 
     * @param index índice do elevador
     * @return o elevador com o índice especificado
     * 
     * @throws WorkshopException caso o índice seja inválido
     */
    public Elevator getElevator(int index) throws WorkshopException {
        if (index < 0 || index >= elevators.length) {
            throw new WorkshopException("Index do elevador inválido - há apenas %d elevadores.", elevators.length);
        }

        return elevators[index];
    }
}
