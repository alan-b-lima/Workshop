package main.workshop.customer;

import main.workshop.exception.WorkshopException;

/**
 * Classe que representa um veículo.
 * 
 * @author Alan Lima
 */
public class Vehicle {

    /**
     * Modelo do veículo.
     */
    private String model;

    /**
     * Placa do veículo.
     */
    private String plate;

    /**
     * Ano do veículo.
     */
    private int year;

    /**
     * Construtor padrão.
     */
    public Vehicle() {

    }

    /**
     * Construtor que recebe o modelo, placa e ano do veículo.
     * 
     * @param model modelo do veículo.
     * @param plate placa do veículo.
     * @param year  ano do veículo.
     * 
     * @throws WorkshopException caso a placa seja inválida.
     */
    public Vehicle(String model, String plate, int year) throws WorkshopException {
        this.setModel(model);
        this.setPlate(plate);
        this.setYear(year);
    }

    /**
     * Retorna o modelo do veículo.
     * 
     * @return modelo do veículo.
     */
    public String getModel() {
        return model;
    }

    /**
     * Define o modelo do veículo.
     * 
     * @param model modelo do veículo.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Retorna a placa do veículo.
     * 
     * @return placa do veículo.
     */
    public String getPlate() {
        return plate;
    }

    /**
     * Define a placa do veículo.
     * 
     * @param plate placa do veículo.
     * 
     * @throws WorkshopException caso a placa seja inválida.
     */
    public void setPlate(String plate) throws WorkshopException {

        if (plate == null) {
            throw new WorkshopException("Placa inválida - placa não pode ser nula!");
        }

        if (plate.matches("^[A-Z]{3}-?\\d{4}$") == true) {
            this.plate = plate.replaceAll("-", "");
        }

        if (plate.matches("^[A-Z]{3}\\d[A-Z]\\d{2}$") == true) {
            this.plate = plate;
        }

        throw new WorkshopException("Placa inválida - placa deve ser no formato \"ABC1234\" ou \"ABC1D23\"!");
    }

    /**
     * Retorna o ano do veículo.
     * 
     * @return ano do veículo.
     */
    public int getYear() {
        return year;
    }

    /**
     * Define o ano do veículo.
     * 
     * @param year ano do veículo.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Retorna uma representação textual do veículo.
     * 
     * @return representação textual do veículo.
     */
    @Override
    public String toString() {
        return String.format("{%s, %s, %d}", this.getModel(), this.getPlate(), this.getYear());
    }
}
