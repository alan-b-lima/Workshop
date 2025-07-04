package edu.ajan.model.workshop.common;

import edu.ajan.model.exception.WorkshopException;
import edu.ajan.model.persistence.InstanceCountState;

/**
 * Classe que representa um cliente.
 * 
 * @author Alan Lima
 */
public class Customer extends Person {

    /**
     * Contador de instâncias.
     */
    private static int instanceCount;

    /**
     * Identificador único do cliente.
     */
    private final int id;

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
        this.id = generateNextId();
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
        this.id = generateNextId();
        this.setAddress(address);
        this.setEmail(email);
    }

    /**
     * Retorna o identificador único do cliente.
     * 
     * @return identificador único do cliente.
     */
    public int id() {
        return id;
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
     * Retorna o número total de instâncias criadas.
     * 
     * @return número total de instâncias criadas.
     */
    public static int getInstanceCount() {
        return instanceCount;
    }

    /**
     * Restaura o contador de instâncias a partir do estado salvo.
     * 
     * @param state estado salvo do contador de instâncias.
     */
    public static void restoreInstanceCount(InstanceCountState state) {
        if (state == null) {
            return;
        }

        instanceCount = state.get(Customer.class);
    }

    /**
     * Incrementa o contador de instâncias.
     */
    private static void incrementInstanceCount() {
        instanceCount++;
    }

    /**
     * Gera o próximo identificador e incrementa o contador de instâncias.
     * 
     * @return próximo identificador único.
     */
    private static int generateNextId() {
        incrementInstanceCount();
        return instanceCount;
    }

    /**
     * Retorna uma representação textual do cliente.
     * 
     * @return representação textual do cliente.
     */
    @Override
    public String toString() {
        String person = super.toString();
        return String.format("(%d %s \"%s\" %s)",
            id, person.substring(1, person.length() - 1), address, email);
    }
    
}