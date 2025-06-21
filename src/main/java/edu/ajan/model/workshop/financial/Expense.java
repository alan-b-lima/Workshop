package edu.ajan.model.workshop.financial;

import edu.ajan.model.custom.WorkshopObject;
import edu.ajan.model.exception.WorkshopException;
import edu.ajan.model.workshop.date.Dates;

/**
 * Classe que representa uma despesa.
 * 
 * @author Juan Pablo
 */
public class Expense extends WorkshopObject {

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
        this.setName(name);
        this.setDescription(description);
        this.setValue(value);
        this.setDate(date);
    }

    /**
     * Construtor de clonagem.
     * 
     * @param expense despesa a ser clonada.
     */
    protected Expense(Expense expense) {
        this.name = expense.name;
        this.description = expense.description;
        this.value = expense.value;
        this.date = expense.date;
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
     * Cria um clone profundo da despesa.
     * 
     * @return a instância clonada da despesa.
     */
    @Override
    public WorkshopObject deepClone() {
        return new Expense(this);
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
