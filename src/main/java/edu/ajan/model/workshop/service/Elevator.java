package edu.ajan.model.workshop.service;

import edu.ajan.model.custom.WorkshopObject;

/**
 * Classe que representa um elevador.
 * 
 * @author Alan Lima
 */
public class Elevator extends WorkshopObject {

    /**
     * Contador de instâncias.
     */
    private static int instanceCount;

    /**
     * Identificador único do elevador.
     */
    private final int id;

    /**
     * Limite de peso do elevador.
     */
    private double weightLimit;

    /**
     * Campo de bits que informa as funções do elevador.
     * 
     * @see ElevatorFunction
     */
    private int function;

    /**
     * Constructor padrão.
     */
    private Elevator() {
        this.id = generateNextId();
    }

    /**
     * Construtor parametrizado.
     * 
     * @param weightLimit limite de peso do elevador.
     * @param function    campo de bits que informa as funções do elevador.
     */
    public Elevator(double weightLimit, int function) {
        this();
        this.setWeightLimit(weightLimit);
        this.setFunction(function);
    }

    /**
     * Construtor parametrizado.
     * 
     * @param weightLimit limite de peso do elevador.
     * @param functions   funções que o elevador pode desempenhar.
     */
    public Elevator(double weightLimit, ElevatorFunction... functions) {
        this();
        this.setWeightLimit(weightLimit);
        this.setFunction(functions);
    }

    /**
     * Construtor de cópia.
     * 
     * @param elevator elevador a ser clonado.
     */
    protected Elevator(Elevator elevator) {
        this.id = elevator.id;
        this.function = elevator.function;
        this.weightLimit = elevator.weightLimit;
    }

    /**
     * Retorna o identificador do elevador.
     * 
     * @return identificador do elevador.
     */
    public int id() {
        return id;
    }

    /**
     * Retorna o limite de peso do elevador.
     * 
     * @return limite de peso do elevador.
     */
    public double getWeightLimit() {
        return weightLimit;
    }

    /**
     * Define o limite de peso do elevador.
     * 
     * @param weightLimit limite de peso do elevador.
     */
    public void setWeightLimit(double weightLimit) {
        if (weightLimit <= 0) {
            throw new IllegalArgumentException("limite de peso deve ser maior que zero");
        }

        if (Double.isInfinite(weightLimit) || Double.isNaN(weightLimit)) {
            throw new IllegalArgumentException("limite de peso deve ser um número válido");
        }

        this.weightLimit = weightLimit;
    }

    /**
     * Campo de bits que informa as funções do elevador.
     * 
     * @return funções do elevador.
     */
    public int getFunction() {
        return function;
    }

    /**
     * Define as funções do elevador.
     * 
     * @param function funções do elevador.
     */
    public void setFunction(int function) {
        this.function = function;
    }

    /**
     * Define as funções do elevador.
     * 
     * @param functions funções do elevador.
     */
    public void setFunction(ElevatorFunction... functions) {
        for (ElevatorFunction function : functions) {
            this.function |= function.code();
        }
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
     * Cria um clone profundo do elevador.
     * 
     * @return a instânca clonada do elevador.
     */
    @Override
    public WorkshopObject deepClone() {
        return new Elevator(this);
    }

    /**
     * Retorna uma representação textual do elevador.
     * 
     * @return representação textual do elevador.
     */
    @Override
    public String toString() {
        return String.format("(%d \"%s\" %.0f)", id, function, weightLimit);
    }
}
