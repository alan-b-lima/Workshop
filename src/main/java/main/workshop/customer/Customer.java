package main.workshop.customer;

import main.workshop.common.Person;
import main.workshop.exception.WorkshopException;

/**
 * Classe que representa um cliente.
 * 
 * @author Alan Lima
 */
public class Customer extends Person {

    /**
     * Endereço do cliente.
     */
    private String address;

    /**
     * Email do cliente.
     */
    private String email;

    /**
     * Construtor padrão.
     */
    public Customer() {

    }

    /**
     * Construtor que recebe o nome, telefone, CPF e endereço do cliente.
     * 
     * @param name    nome do cliente.
     * @param phone   telefone do cliente.
     * @param cpf     CPF do cliente.
     * @param address endereço do cliente.
     * @param email   email do cliente.
     * 
     * @throws WorkshopException caso o telefone ou CPF sejam inválidos.
     */
    public Customer(String name, String phone, String cpf, String address, String email) throws WorkshopException {
        super(name, phone, cpf);
        this.setAddress(address);
        this.setEmail(email);
    }

    /**
     * Retorna o endereço do cliente.
     * 
     * @return endereço do cliente.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Define o endereço do cliente.
     * 
     * @param address endereço do cliente.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retorna o email do cliente.
     * 
     * @return email do cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do cliente.
     * 
     * @param email email do cliente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna uma representação textual do cliente.
     * 
     * @return representação textual do cliente.
     */
    @Override
    public String toString() {
        return String.format("{%s, %s, %s, %s}", this.getName(), this.getPhone(), this.getCpf(), this.getAddress());
    }
}
