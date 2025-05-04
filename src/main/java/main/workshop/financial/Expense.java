package main.workshop.financial;

import main.util.WDate;

/**
 * Classe que representa uma despesa.
 * 
 * @author Juan Pablo
 */
public class Expense {

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
     * Timestamp UNIX que representa a data da despesa.
     */
    private long date;

    /**
     * Construtor padrão.
     */
    public Expense() {

    }

    /**
     * Construtor que recebe o nome, descrição, valor e data da despesa.
     * 
     * @param name        nome da despesa.
     * @param description descrição da despesa.
     * @param value       valor da despesa.
     * @param date        data da despesa.
     */
    public Expense(String name, String description, double value, long date) {
        this.setName(name);
        this.setDescription(description);
        this.setValue(value);
        this.setDate(date);
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
     * @return timestamp UNIX que representa a data da despesa.
     */
    public long getDate() {
        return date;
    }

    /**
     * Define a data da despesa.
     * 
     * @param date timestamp UNIX que representa a data da despesa.
     */
    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("{%s, %s, %.2f, %s}", name, description, value, WDate.formatDateTime(date));
    }
}
