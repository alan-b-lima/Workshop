package main.workshop;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import main.workshop.auth.Session;
import main.workshop.customer.Customer;
import main.workshop.customer.Vehicle;
import main.workshop.personnel.Employee;
import main.workshop.personnel.Manager;
import main.workshop.service.Scheduler;
import main.workshop.stock.Stock;

/**
 * Classe que representa a oficina.
 */
public final class Workshop {

    /**
     * Instância do agendador de serviços da oficina.
     */
    private Scheduler scheduler;

    /**
     * Instância do estoque da oficina.
     */
    private Stock stock;

    /**
     * Instância da sessão atual.
     */
    private Session session;

    /**
     * Lista de clientes da oficina.
     */
    private ArrayList<Customer> customers;

    /**
     * Lista de veículos da oficina.
     */
    private ArrayList<Vehicle> vehicles;

    /**
     * Lista de funcionários da oficina.
     */
    private ArrayList<Employee> employees;

    /**
     * Instância do gerente da oficina.
     */
    private Manager manager;

    /**
     * Instância única da oficina.
     */
    private static Workshop workshop = new Workshop();

    /**
     * Construtor privado para a classe Workshop, garantindo que apenas uma
     */
    private Workshop() {
        scheduler = new Scheduler();

        customers = new ArrayList<>();
        vehicles = new ArrayList<>();
        employees = new ArrayList<>();
    }

    /**
     * Método que retorna a instância única da oficina.
     * 
     * @return instância única da oficina.
     */
    public static Workshop getInstance() {
        return workshop;
    }

    /**
     * Caminho de estado da oficina.
     */
    private static final String jsonPath = String.format("data%sWorkshop.json", File.separator);

    /**
     * Gson para serialização e deserialização de objetos.
     */
    private static final Gson GSON = new Gson();

    /**
     * Retorna o gerente da oficina.
     * 
     * @return gerente da oficina.
     */
    public Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * Retorna o estoque da oficina.
     * 
     * @return estoque da oficina.
     */
    public Stock getStock() {
        return stock;
    }

    /**
     * Retorna a sessão atual.
     * 
     * @return sessão atual.
     */
    public Session getSession() {
        return session;
    }

    /**
     * Define a sessão atual.
     * 
     * @param session sessão a ser definida.
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Retorna a lista de clientes da oficina.
     * 
     * @return lista de clientes da oficina.
     */
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    /**
     * Adiciona um cliente à lista de clientes da oficina.
     * 
     * @param customer cliente a ser adicionado.
     */
    public void addCustomers(Customer customer) {
        this.customers.add(customer);
    }

    /**
     * Retorna a lista de veículos da oficina.
     * 
     * @return lista de veículos da oficina.
     */
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * Adiciona um veículo à lista de veículos da oficina.
     * 
     * @param vehicle veículo a ser adicionado.
     */
    public void addVehicles(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }

    /**
     * Retorna a lista de funcionários da oficina.
     * 
     * @return lista de funcionários da oficina.
     */
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     * Adiciona um funcionário à lista de funcionários da oficina.
     * 
     * @param employee funcionário a ser adicionado.
     */
    public void addEmployees(Employee employee) {
        this.employees.add(employee);
    }

    /**
     * Retorna o gerente da oficina.
     * 
     * @return gerente da oficina.
     */
    public Manager getManager() {
        return manager;
    }    

    /**
     * Retorna o gerente da oficina.
     */
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    /**
     * Método que salva o estado da oficina em um arquivo JSON.
     */
    public void saveState() {

        try (FileWriter file = new FileWriter(jsonPath)) {

            GSON.toJson(workshop, file);
            file.close();

        } catch (IOException err) {
            return; // error
        }
    }

    /**
     * Método que carrega o estado da oficina a partir de um arquivo JSON.
     */
    public void loadState() {

        try (FileReader file = new FileReader(jsonPath)) {

            GSON.fromJson(file, new TypeToken<Workshop>() {});
            file.close();

        } catch (IOException err) {
            return; // error
        }
    }
}
