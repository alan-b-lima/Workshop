package main.workshop.customer;

import java.util.Comparator;

import main.workshop.common.Person;
import main.workshop.exception.WorkshopException;

/**
 * Classe que representa um cliente.
 * 
 * @author Alan Lima
 */
public class Customer extends Person implements Comparator<Customer> {

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
     * Compara dois clientes.
     * 
     * @param one   primeiro cliente a ser comparado.
     * @param other segundo cliente a ser comparado.
     * @return {@code 0} se os clientes forem iguais, {@code -1} se o primeiro
     *         cliente for menor que o segundo, {@code 1} caso contrário.
     */
    @Override
    public int compare(Customer one, Customer other) {
        int comparisonName = one.getName().compareTo(other.getName());

        if (comparisonName != 0) {
            return comparisonName;
        }

        return one.getCpf().compareTo(other.getCpf());
    }

    /**
     * Compara dois clientes.
     * 
     * @param obj objeto a ser comparado.
     * @return {@code true} se os clientes forem iguais, {@code false} caso
     *         contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Customer other = (Customer) obj;

        return compare(this, other) == 0;
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
