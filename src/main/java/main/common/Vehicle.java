package main.common;

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
     * Construtor com parâmetros.
     * 
     * @param model modelo do veículo.
     * @param plate placa do veículo.
     * @param year  ano do veículo.
     */
    public Vehicle(String model, String plate, int year) {
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
     */
    public void setPlate(String plate) {
        this.plate = plate;
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
