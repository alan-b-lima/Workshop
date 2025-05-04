package main.workshop.service;

/**
 * Classe que representa um serviço realizado em um veículo.
 * 
 * @author Alan Lima
 */
public class Service {

    /**
     * Nome do serviço.
     */
    private String name;

    /**
     * Indica se o serviço foi realizado.
     */
    private boolean done;

    /**
     * Valor do serviço.
     */
    private double value;

    /**
     * Construtor padrão.
     */
    public Service() {

    }

    /**
     * Construtor que recebe o nome do serviço, se foi realizado e o valor do
     * serviço.
     * 
     * @param name  nome do serviço.
     * @param done  indica se o serviço foi realizado.
     * @param value valor do serviço.
     */
    public Service(String name, boolean done, double value) {
        this.name = name;
        this.done = done;
        this.value = value;
    }

    /**
     * Construtor que recebe o nome do serviço e o valor do serviço.
     * 
     * @param name  nome do serviço.
     * @param value valor do serviço.
     */
    public String getName() {
        return name;
    }

    /**
     * Construtor que recebe o nome do serviço e o valor do serviço.
     * 
     * @param name  nome do serviço.
     * @param value valor do serviço.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna se o serviço foi realizado.
     * 
     * @return {@code true} se o serviço foi realizado, {@code false} caso
     *         contrário.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Construtor que recebe se o serviço foi realizado.
     * 
     * @param done indica se o serviço foi realizado.
     */
    public void setDone(boolean done) {
        this.done = done;
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
     * Construtor que recebe o valor do serviço.
     * 
     * @param value valor do serviço.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Retorna uma representação textual do objeto.
     * 
     * @return representação textual do objeto.
     */
    @Override
    public String toString() {
        return String.format("{%s, %s, %.2f}", name, Boolean.valueOf(done), value);
    }
}