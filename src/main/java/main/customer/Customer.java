package main.customer;

import main.common.Person;
import main.exception.WorkshopException;

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
     * 
     * @throws WorkshopException
     */
    public Customer(String name, String phone, String cpf, String address) throws WorkshopException {
        super(name, phone, cpf);
        this.setAddress(address);
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
     * Retorna uma representação textual do cliente.
     * 
     * @return representação textual do cliente.
     */
    @Override
    public String toString() {
        return String.format("{%s, %s, %s, %s}", this.getName(), this.getPhone(), this.getCpf(), this.getAddress());
    }
}
