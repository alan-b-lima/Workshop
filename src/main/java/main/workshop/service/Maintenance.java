package main.workshop.service;

import java.util.ArrayList;
import java.util.HashMap;

import main.workshop.common.DateSpan;
import main.workshop.customer.Customer;
import main.workshop.customer.Vehicle;
import main.workshop.personnel.Employee;
import main.workshop.stock.Part;

/**
 * Classe que representa uma manutenção.
 * 
 * @author Alan Lima
 */
public class Maintenance extends Scheduling {

    /**
     * Status da manutenção.
     */
    private Status status;

    /**
     * Mecânico responsável pela manutenção.
     */
    private Employee mechanic;

    /**
     * Peças utilizadas na manutenção.
     */
    private HashMap<String, Part> parts;

    /**
     * Serviços realizados na manutenção.
     */
    private ArrayList<Service> services;

    /**
     * Construtor padrão.
     */
    public Maintenance() {
        this.parts = new HashMap<>();
        this.services = new ArrayList<>();
    }

    /**
     * Construtor que inicializa a manutenção com os dados do cliente, veículo e
     * elevador.
     * 
     * @param id       cliente da manutenção.
     * @param vehicle  veículo do cliente.
     * @param elevator elevador da manutenção.
     * @param date     data da manutenção.
     * @param status   status da manutenção.
     * @param mechanic mecânico responsável pela manutenção.
     */
    public Maintenance(Customer id, Vehicle vehicle, byte elevator, DateSpan date, Status status, Employee mechanic) {
        super(id, vehicle, elevator, date);
        this.status = status;
        this.mechanic = mechanic;
        this.parts = new HashMap<>();
        this.services = new ArrayList<>();
    }

    /**
     * Enumeração que representa os status da manutenção.
     */
    public static enum Status {
        PENDING,
        IN_PROGRESS,
        READY_FOR_DELIVERY,
        DELIVERED
    }

    /**
     * Retorna o cliente da manutenção.
     * 
     * @return cliente da manutenção.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Retorna o status da manutenção.
     * 
     * @return status da manutenção.
     */
    public String getStatusString() {
        return switch (status) {
            case PENDING -> "Pendente";
            case IN_PROGRESS -> "Em andamento";
            case READY_FOR_DELIVERY -> "Pronto para entrega";
            case DELIVERED -> "Entregue";
        };
    }

    /**
     * Retorna o status da manutenção.
     * 
     * @return status da manutenção.
     */
    public Status getStatusCode() {
        return status;
    }

    /**
     * Define o status da manutenção.
     * 
     * @param status status da manutenção.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Retorna o mecânico responsável pela manutenção.
     * 
     * @return mecânico responsável pela manutenção.
     */
    public Employee getMechanic() {
        return mechanic;
    }

    /**
     * Define o mecânico responsável pela manutenção.
     * 
     * @param mechanic mecânico responsável pela manutenção.
     */
    public void setMechanic(Employee mechanic) {
        this.mechanic = mechanic;
    }

    /**
     * Retorna as peças utilizadas na manutenção.
     * 
     * @return array de peças utilizadas na manutenção.
     */
    public Part[] getParts() {
        return (Part[]) parts.values().toArray();
    }

    /**
     * Retorna a peça utilizada na manutenção.
     * 
     * @param name nome da peça utilizada na manutenção.
     * @return peça utilizada na manutenção.
     */
    public Part getPart(String name) {
        return parts.get(name);
    }

    /**
     * Adiciona uma peça à manutenção.
     * 
     * @param part peça a ser adicionada.
     */
    public void addPart(Part part) {
        Part existingPart = parts.get(part.getName());

        if (existingPart == null) {
            parts.put(part.getName(), existingPart);
            return;
        }

        // try {
        //     existingPart.addQuantity(existingPart.getQuantity());
        // } catch (WorkshopException err) {
        //     return;
        // }
    }

    /**
     * Retorna os serviços da manutenção.
     * 
     * @return array de serviços da manutenção.
     */
    public Service[] getServices() {
        return (Service[]) services.toArray();
    }

    /**
     * Adiciona um serviço à manutenção.
     * 
     * @param service serviço a ser adicionado.
     */
    public void addService(Service service) {
        services.add(service);
    }
}