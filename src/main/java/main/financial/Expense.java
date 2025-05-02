package main.financial;

import java.util.Date;

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
     * UNIX timestamp que representa a data da despesa.
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

    /*
     * Retorna o nome da despesa.
     */
    public String getName() {
        return name;
    }

    /*
     * Define o nome da despesa.
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * Retorna a descrição da despesa.
     */
    public String getDescription() {
        return description;
    }

    /*
     * Define a descrição da despesa.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /*
     * Retorna o valor da despesa.
     */
    public double getValue() {
        return value;
    }

    /*
     * Define o valor da despesa.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /*
     * Retorna a data da despesa.
     */
    public long getDate() {
        return date;
    }

    /*
     * Retorna a data da despesa como um objeto Date.
     */
    public Date getDateDate() {
        return new Date(date);
    }

    /*
     * Define a data da despesa.
     */
    public void setDate(long date) {
        this.date = date;
    }
}
