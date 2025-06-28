package edu.ajan.model.workshop.service;

import java.util.Arrays;
import java.util.HashMap;

import edu.ajan.model.exception.WorkshopException;

public class Scheduler {

    private HashMap<Integer, ServiceOrder> orders;

    private HashMap<Integer, Service> services;

    private Elevator[] elevators;

    public Scheduler() {
        this.orders = new HashMap<>();
        this.services = new HashMap<>();
        this.elevators = new Elevator[] {
                new Elevator(25000.00, ElevatorFunction.BALANCING, ElevatorFunction.ALIGNING),
                new Elevator(25000.00, ElevatorFunction.GENERAL),
                new Elevator(25000.00, ElevatorFunction.GENERAL),
        };
    }

    /**
     * Retorna uma ordem de serviço a partir do seu identificador.
     * 
     * @param orderId identificador de ordem de serviço.
     * @return ordem de serviço de identificador passado, ou {@code null} se essa
     *         ordem de serviço não existir.
     */
    public ServiceOrder getOrder(int orderId) {
        return orders.get(orderId);
    }

    /**
     * Asserta a existência de uma ordem de serviço.
     * 
     * @param orderId identificador de ordem de serviço.
     * @return {@code true} se, e somente se a ordem de serviço existir.
     */
    public boolean hasOrder(int orderId) {
        return orders.containsKey(orderId);
    }

    /**
     * Adiciona um ordem de serviço ao agendador.
     * 
     * @param order ordem de serviço a ser adicionado.
     */
    public void addOrder(ServiceOrder order) {
        
        if (order == null) {
            throw new WorkshopException("ordem de serviço não pode ser nula");
        }

        for (ServiceOrder otherOrder : orders.values()) {
            if (otherOrder.isConflicting(order)) {
                throw new WorkshopException(""
                        + "ordem de serviço conflituosa, "
                        + "já há uma ordem de serviço nesse horário usando o mesmo elevador");
            }
        }

        this.orders.put(order.id(), order);
    }

    /**
     * Remove um ordem de serviço ao agendador.
     * 
     * @param orderId identificador da ordem de serviço a ser removido.
     */
    public void removeOrder(int orderId) {
        orders.remove(orderId);
    }

    /**
     * Retorna um serviço a partir do seu identificador.
     * 
     * @param serviceId identificador de serviço.
     * @return serviço de identificador passado, ou {@code null} se esse serviço não
     *         existir.
     */
    public Service getService(int serviceId) {
        return services.get(serviceId);
    }

    /**
     * Asserta a existência de um serviço.
     * 
     * @param serviceId identificador de serviço.
     * @return {@code true} se, e somente se o serviço existir.
     */
    public boolean hasService(int serviceId) {
        return services.containsKey(serviceId);
    }

    /**
     * Adiciona um serviço ao agendado.
     * 
     * @param service serviço a ser adicionado.
     */
    public void addService(Service service) {
        if (service == null) {
            throw new WorkshopException("serviço não pode ser nulo");
        }

        services.put(service.id(), service);
    }

    /**
     * Remove um serviço do agendado.
     * 
     * @param serviceId identificador do serviço a ser removido.
     */
    public void removeService(int serviceId) {
        services.remove(serviceId);
    }

    /**
     * Retorna um elevador a partir do seu identificador.
     * 
     * @param elevatorId identificador de elevador.
     * @return elevador de identificador passado, ou {@code null} se esse elevador
     *         não existir.
     */
    public Elevator getElevator(int elevatorId) {
        for (Elevator elevator : elevators) {
            if (elevator.id() == elevatorId) {
                return elevator;
            }
        }

        return null;
    }

    /**
     * Asserta a existência de um elevador.
     * 
     * @param elevatorId identificador de elevador.
     * @return {@code true} se, e somente se o elevador existir.
     */
    public boolean hasElevator(int elevatorId) {
        for (Elevator elevator : elevators) {
            if (elevator.id() == elevatorId) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adiciona um elevador ao registro.
     * 
     * @param elevator elevador a ser adicionado.
     */
    public void addElevator(Elevator elevator) {
        if (elevator == null) {
            throw new WorkshopException("elevador não pode ser nulo");
        }

        this.elevators = Arrays.copyOf(elevators, elevators.length + 1);
        this.elevators[elevators.length - 1] = elevator;
    }

    /**
     * Remove um elevador do registro.
     * 
     * @param elevatorId identificador do elevador a ser removido.
     */
    public void removeElevator(int elevatorId) {

        int index;
        findIndex: {
            for (index = 0; index < elevators.length; index++) {
                if (elevators[index].id() == elevatorId) {
                    break findIndex; // Elevador encontrado
                }
            }

            return; // Elevador não encontrado, nada a fazer
        }

        // Cria um novo array e copia os elementos, excluindo o elevador removido
        Elevator[] newElevators = Arrays.copyOf(elevators, elevators.length - 1);
        System.arraycopy(elevators, index + 1, newElevators, index, elevators.length - index - 1);

        this.elevators = newElevators;
    }
}
