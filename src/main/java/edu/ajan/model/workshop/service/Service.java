package edu.ajan.model.workshop.service;

import edu.ajan.model.exception.WorkshopException;
import edu.ajan.model.persistence.InstanceCountState;

/**
 * Classe que representa um serviço.
 * 
 * @author Juan Pablo
 */
public class Service {

    /**
     * Contador de instâncias de serviços.
     */
    private static int instanceCount;

    /**
     * Identificador único do serviço.
     */
    private final int id;

    /**
     * Nome do serviço.
     */
    private String name;

    /**
     * Descrição do serviço.
     */
    private String description;

    /**
     * Valor do serviço.
     */
    private double value;

    /**
     * Construtor padrão.
     */
    public Service() {
        this.id = generateNextId();
    }

    /**
     * Construtor parametrizado.
     * 
     * @param name        nome do serviço.
     * @param description descrição do serviço.
     * @param value       valor do serviço.
     */
    public Service(String name, String description, double value) {
        this();
        this.setName(name);
        this.setDescription(description);
        this.setValue(value);
    }

    /**
     * Retorna o identificador único do serviço.
     * 
     * @return identificador único do serviço.
     */
    public int id() {
        return id;
    }

    /**
     * Retorna o nome do serviço.
     * 
     * @return nome do serviço.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do serviço.
     * 
     * @param name nome do serviço.
     */
    public void setName(String name) {
        if (name == null) {
            throw new WorkshopException("nome não pode ser nulo");
        }

        this.name = name;
    }

    /**
     * Retorna a descrição do serviço.
     * 
     * @return descrição do serviço.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define a descrição do serviço.
     * 
     * @param description descrição do serviço.
     */
    public void setDescription(String description) {
        if (name == null) {
            throw new WorkshopException("descrição não pode ser nula");
        }

        this.description = description;
    }

    /**
     * Retorna o valor do serviço.
     * 
     * @return valor do serviço.
     */
    public double getValue() {
        return value;
    }

    /**
     * Define o valor do serviço.
     * 
     * @param value valor do serviço.
     */
    public void setValue(double value) {
        if (value < 0.0) {
            throw new WorkshopException("valor não pode ser negativo");
        }

        this.value = value;
    }

    /**
     * Retorna o número de instâncias de serviços criadas.
     * 
     * @return número de instâncias de serviços.
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

        instanceCount = state.get(Service.class);
    }

    /**
     * Incrementa o contador de instâncias de serviços.
     */
    private static void incrementInstanceCount() {
        instanceCount++;
    }

    /**
     * Gera o próximo identificador único para um serviço.
     * 
     * @return próximo identificador único.
     */
    private static int generateNextId() {
        incrementInstanceCount();
        return instanceCount;
    }

    /**
     * Retorna a representação textual do serviço.
     * 
     * @return a instância clonada do serviço.
     */
    public String toString() {
        return String.format("(%d \"%s\" \"%s\" %.2f)", id, name, description, value);
    }
}