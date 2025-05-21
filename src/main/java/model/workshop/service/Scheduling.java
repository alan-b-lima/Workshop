package model.workshop.service;

import model.workshop.common.DateSpan;
import model.workshop.customer.Customer;
import model.workshop.customer.Vehicle;

/**
 * Classe que representa um agendamento de manutenção.
 * 
 * @author Alan Lima
 */
public class Scheduling {

    /**
     * CPF do cliente.
     */
    private long customer;

    /**
     * Placa do veículo.
     */
    private long vehicle;

    /**
     * Elevador utilizado.
     */
    private byte elevator;

    /**
     * Data e hora do agendamento.
     */
    private DateSpan date;

    /**
     * Construtor padrão.
     */
    public Scheduling() {
        this.date = new DateSpan();
    }

    /**
     * Construtor que recebe o CPF do cliente, a placa do veículo, o elevador
     * utilizado e a data e hora do agendamento.
     * 
     * @param customer cliente.
     * @param vehicle  veículo.
     * @param elevator elevador utilizado.
     * @param start    timestamp UNIX de início do agendamento.
     * @param end      timestamp UNIX de fim do agendamento.
     */
    public Scheduling(Customer customer, Vehicle vehicle, byte elevator, long start, long end) {
        this(customer, vehicle, elevator, new DateSpan(start, end));
    }

    /**
     * Construtor que recebe o CPF do cliente, a placa do veículo, o elevador
     * utilizado e a data e hora do agendamento.
     * 
     * @param customer CPF do cliente.
     * @param vehicle  placa do veículo.
     * @param elevator elevador utilizado.
     * @param date     período de tempo do agendamento.
     */
    public Scheduling(Customer customer, Vehicle vehicle, byte elevator, DateSpan date) {
        this.customer = customer.getNumericCpf();
        this.vehicle = vehicle.getPlate().hashCode();
        this.elevator = elevator;
        this.date = date;
    }

    /**
     * Retorna o cliente.
     * 
     * @return cliente.
     */
    public Customer getCustomer() {
        // TODO: Implementar método para retornar o cliente a partir do CPF
        return null;
    }

    /**
     * Define o cliente.
     * 
     * @param customer cliente a ser definido.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer.getNumericCpf();
    }

    /**
     * Retorna o veículo.
     * 
     * @return veículo.
     */
    public long getVehicle() {
        return vehicle;
    }

    /**
     * Define o veículo.
     * 
     * @param vehicle veículo a ser definido.
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle.getPlate().hashCode();
    }

    /**
     * Retorna o elevador utilizado.
     * 
     * @return elevador utilizado.
     */
    public byte getElevator() {
        return elevator;
    }

    /**
     * Define o elevador utilizado.
     * 
     * @param elevator elevador a ser definido.
     */
    public void setElevator(byte elevator) {
        this.elevator = elevator;
    }

    /**
     * Retorna a data e hora do agendamento.
     * 
     * @return data e hora do agendamento.
     */
    public DateSpan getDate() {
        return date;
    }

    /**
     * Define a data e hora do agendamento.
     * 
     * @param date data e hora a serem definidos.
     */
    public void setDate(DateSpan date) {
        this.date = date;
    }

    /**
     * Retorna uma representação textual do agendamento.
     * 
     * @return representação textual do agendamento.
     */
    @Override
    public String toString() {
        return String.format("{%s, %s, %s, %s}", customer, vehicle, elevator, date);
    }

    // public Maintenance toMaintenance() {
    // return new Maintenance(customer, vehicle, elevator, date.getStart(),
    // date.getDuration());
    // }
}