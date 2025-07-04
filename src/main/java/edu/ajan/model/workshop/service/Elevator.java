package edu.ajan.model.workshop.service;

import edu.ajan.model.persistence.InstanceCountState;

/**
 * Classe que representa um elevador.
 * 
 * @author Alan Lima
 */
public class Elevator {

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
     * Restaura o contador de instâncias a partir do estado salvo.
     * 
     * @param state estado salvo do contador de instâncias.
     */
    public static void restoreInstanceCount(InstanceCountState state) {
        if (state == null) {
            return;
        }

        instanceCount = state.get(Elevator.class);
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
     * Retorna uma representação textual do elevador.
     * 
     * @return representação textual do elevador.
     */
    @Override
    public String toString() {
        return String.format("(%d \"%s\" %.0f)", id, function, weightLimit);
    }
}
