package edu.ajan.model.workshop.common;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Classe que representa um registro de clientes e veículos.
 * 
 * @author Alan Lima
 */
public class Registry {

    /**
     * Mapa de clientes indexados pelo identificador do cliente.
     */
    private HashMap<Integer, Customer> customers;

    /**
     * Mapa de veículos indexados pelo identificador do veículo.
     */
    private HashMap<Integer, Vehicle> vehicles;

    /**
     * Construtor padrão.
     */
    public Registry() {
        this.customers = new HashMap<>();
        this.vehicles = new HashMap<>();
    }

    /**
     * Retorna uma estrutura iterável de clientes.
     * 
     * @return estrutura iterável de clientes.
     */
    public Collection<Customer> getCustomers() {
        return customers.values();
    }

    /**
     * Retorna um cliente a partir do seu identificador.
     * 
     * @param customerId identificador de cliente.
     * @return cliente de identificador passado, ou {@code null} se esse cliente não
     *         existir.
     */
    public Customer getCustomer(int customerId) {
        return customers.get(customerId);
    }

    /**
     * Asserta a existência de um cliente.
     * 
     * @param customerId identificador de cliente.
     * @return {@code true} se, e somente se o cliente existir.
     */
    public boolean hasCustomer(int customerId) {
        return customers.containsKey(customerId);
    }

    /**
     * Busca um cliente com base em um critério de comparação.
     * 
     * @param searchCustomer cliente a ser buscado.
     * @param comparator     critério de comparação a ser utilizado.
     * @return cliente que atende ao critério de comparação, ou {@code null} se não
     *         houver nenhum cliente que atenda ao critério.
     */
    public Customer findCustomer(Customer searchCustomer, Comparator<Customer> comparator) {
        for (Customer customer : getCustomers()) {
            if (comparator.compare(customer, searchCustomer) == 0) {
                return customer;
            }
        }

        return null;
    }

    /**
     * Adiciona um cliente ao registro.
     * 
     * @param customer cliente a ser adicionado.
     */
    public void addCustomer(Customer customer) {
        customers.put(customer.id(), customer);
    }

    /**
     * Remove um cliente do registro.
     * 
     * @param customerId identificador do cliente a ser removido.
     */
    public void removeCustomer(int customerId) {
        customers.remove(customerId);
    }

    /**
     * Retorna uma estrutura iterável de veículos.
     * 
     * @return estrutura iterável de veículos.
     */
    public Collection<Vehicle> getVehicles() {
        return vehicles.values();
    }

    /**
     * Retorna um veículo a partir do seu identificador.
     * 
     * @param vehicleId identificador de veículo.
     * @return veículo de identificador passado, ou {@code null} se esse veículo não
     *         existir.
     */
    public Vehicle getVehicle(int vehicleId) {
        return vehicles.get(vehicleId);
    }

    /**
     * Asserta a existência de um veículo.
     * 
     * @param vehicleId identificador de veículo.
     * @return {@code true} se, e somente se o veículo existir.
     */
    public boolean hasVehicle(int vehicleId) {
        return vehicles.containsKey(vehicleId);
    }

    /**
     * Adiciona um veículo ao registro.
     * 
     * @param vehicle veículo a ser adicionado.
     */
    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.id(), vehicle);
    }

    /**
     * Remove um veículo do registro.
     * 
     * @param vehicleId identificador do veículo a ser removido.
     */
    public void removeVehicle(int vehicleId) {
        vehicles.remove(vehicleId);
    }

    /**
     * Retorna uma representação textual do registro.
     * 
     * @return representação textual do registro.
     */
    @Override
    public String toString() {
        return String.format("(%s %s)", customers, vehicles);
    }
}
