package edu.ajan.model.workshop.financial;

import edu.ajan.model.exception.WorkshopException;
import edu.ajan.model.persistence.InstanceCountState;
import edu.ajan.model.workshop.date.Dates;

/**
 * Classe que representa uma despesa.
 * 
 * @author Juan Pablo
 */
public class Expense {

    /**
     * Contador de instâncias.
     */
    private static int instanceCount;

    /**
     * Identificador único de despesa.
     */
    private final int id;

    /**
     * Nome da despesa.
     */
    private String name;

    /**
     * Descrição da despesa.
     */
    private String description;

    /**
     * Valor da despesa.
     */
    private double value;

    /**
     * Timestamp que representa a data da despesa.
     */
    private long date;

    /**
     * Construtor padrão.
     */
    public Expense() {
        this.id = generateNextId();
        this.name = "";
        this.description = "";
        this.value = 0.0;
        this.date = 0L;
    }

    /**
     * Construtor parametrizado.
     * 
     * @param name        nome da despesa.
     * @param description descrição da despesa.
     * @param value       valor da despesa.
     * @param date        data da despesa.
     * 
     * @throws WorkshopException se algum dos argumentos for inválido.
     */
    public Expense(String name, String description, double value, long date) {
        this.id = generateNextId();
        this.setName(name);
        this.setDescription(description);
        this.setValue(value);
        this.setDate(date);
    }

    /**
     * Retorna o identificador da despesa.
     * 
     * @return identificador da despesa.
     */
    public int id() {
        return id;
    }

    /**
     * Retorna o nome da despesa.
     * 
     * @return nome da despesa.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da despesa.
     * 
     * @param name nome da despesa.
     */
    public void setName(String name) {
        if (name == null) {
            throw new WorkshopException("nome não pode ser nulo");
        }

        this.name = name;
    }

    /**
     * Retorna a descrição da despesa.
     * 
     * @return descrição da despesa.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define a descrição da despesa.
     * 
     * @param description descrição da despesa.
     */
    public void setDescription(String description) {
        if (description == null) {
            throw new WorkshopException("descrição não pode ser nula");
        }

        this.description = description;
    }

    /**
     * Retorna o valor da despesa.
     * 
     * @return valor da despesa.
     */
    public double getValue() {
        return value;
    }

    /**
     * Define o valor da despesa.
     * 
     * @param value valor da despesa.
     */
    public void setValue(double value) {
        if (value < 0) {
            throw new WorkshopException("valor não pode ser negativo");
        }

        this.value = value;
    }

    /**
     * Retorna a data da despesa.
     * 
     * @return timestamp que representa a data da despesa.
     */
    public long getDate() {
        return date;
    }

    /**
     * Define a data da despesa.
     * 
     * @param date timestamp que representa a data da despesa.
     */
    public void setDate(long date) {
        this.date = date;
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

        instanceCount = state.get(Expense.class);
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
     * Retorna uma representação textual da despesa.
     * 
     * @return representação textual da despesa.
     */
    @Override
    public String toString() {
        return String.format("(\"%s\" \"%s\" %.2f %s)", name, description, value, Dates.formatAsDate(date));
    }
}
