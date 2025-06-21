package edu.ajan.model.workshop.common;

import edu.ajan.model.exception.WorkshopException;

/**
 * Classe que representa um cliente.
 */
public class Customer extends Person {

    /**
     * Endereço do cliente.
     */
    private String address;

    /**
     * Email do cliente.
     * 
     * Não há verificação de formato.
     */
    private String email;

    /**
     * Construtor padrão.
     */
    public Customer() {
        this.address = "";
        this.email = "";
    }

    /**
     * Construtor paramentrizado.
     * 
     * @param name    nome do cliente.
     * @param phone   número de telofone do cliente.
     * @param cpf     CPF do cliente.
     * @param address endereço do cliente.
     * @param email   email do cliente.
     * 
     * @throws WorkshopException se algum dos argumentos for inválido.
     */
    public Customer(String name, String phone, String cpf, String address, String email) {
        super(name, phone, cpf);
        this.setAddress(address);
        this.setEmail(email);
    }

    /**
     * Construtor de clonagem.
     * 
     * @param customer cliente a ser clonado.
     */
    protected Customer(Customer customer) {
        super(customer);
        this.address = customer.address;
        this.email = customer.email;
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
        if (address == null) {
            throw new WorkshopException("endereço não pode ser nulo");
        }

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
        if (email == null) {
            throw new WorkshopException("email não pode ser nulo");
        }

        this.email = email;
    }

    /**
     * Cria um clone profundo do cliente.
     * 
     * @return a instância clonada do cliente.
     */
    @Override
    public Customer deepClone() {
        return new Customer(this);
    }

    /**
     * Retorna uma representação textual do cliente.
     * 
     * @return representação textual do cliente.
     */
    @Override
    public String toString() {
        String person = super.toString();
        return String.format("(%s \"%s\" %s)", person.substring(1, person.length() - 1), address, email);
    }
}