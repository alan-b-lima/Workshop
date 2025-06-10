package model.workshop.stock;

import model.exception.WorkshopException;

/**
 * Classe que representa uma peça.
 * 
 * @author Juan Pablo
 */
public class Part {

    /**
     * Tipo da peça.
     */
    private int partKind;

    /**
     * Valor unitário da peça.
     */
    private double unitValue;

    /**
     * Quantidade de peças.
     */
    private int quantity;

    /**
     * Construtor padrão.
     */
    public Part() {

    }

    /**
     * Construtor parametrizado.
     * 
     * @param partKind  tipo da peça.
     * @param unitValue valor unitário da peça.
     * @param quantity  quantidade de peças.
     */
    public Part(int partKind, double unitValue, int quantity) {
        this.setPartKind(partKind);
        this.setUnitValue(unitValue);
        this.setQuantity(quantity);
    }

    /**
     * Construtor de clonagem.
     * 
     * @param part peça a ser clonada.
     */
    private Part(Part part) {
        this.partKind = part.partKind;
        this.unitValue = part.unitValue;
        this.quantity = part.quantity;
    }

    /**
     * Retorna o tipo da peça.
     * 
     * @return tipo da peça.
     */
    public int getPartKind() {
        return partKind;
    }

    /**
     * Define o tipo da peça.
     * 
     * @param partKind tipo da peça.
     */
    public void setPartKind(int partKind) {
        this.partKind = partKind;
    }

    /**
     * Retorna o valor unitário da peça.
     * 
     * @return valor unitário da peça.
     */
    public double getUnitValue() {
        return unitValue;
    }

    /**
     * Retorna o valor total das peças.
     * 
     * @return valor total das peças.
     */
    public double getTotalValue() {
        return unitValue * (double) quantity;
    }

    /**
     * Define o valor unitário da peça.
     * 
     * @param unitValue valor unitário da peça.
     */
    public void setUnitValue(double unitValue) {
        this.unitValue = unitValue;
    }

    /**
     * Retorna a quantidade de peças.
     * 
     * @return quantidade de peças.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Define a quantidade de peças.
     * 
     * @param quantity quantidade de peças.
     * 
     * @throws WorkshopException caso a quantidade seja negativa.
     */
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new WorkshopException("quantidade não pode ser negativa!");
        }

        this.quantity = quantity;
    }

    /**
     * Adiciona uma quantidade de peças.
     * 
     * @param quantity quantidade de peças a adicionar.
     * 
     * @throws WorkshopException caso a quantidade seja negativa.
     */
    public void addQuantity(int quantity) {
        if (quantity < 0) {
            throw new WorkshopException("quantidade a adicionar não pode ser negativa!");
        }

        this.quantity += quantity;
    }

    /**
     * Remove uma quantidade de peças.
     * 
     * @param quantity quantidade de peças a remover.
     * 
     * @throws WorkshopException caso a quantidade seja insuficiente para remoção.
     */
    public void removeQuantity(int quantity) {
        if (this.quantity < quantity) {
            throw new WorkshopException("quantidade insuficiente para remoção!");
        }

        this.quantity -= quantity;
    }

    /**
     * Faz uma cópia profunda da peça
     * 
     * @return cópia da peça
     */
    public Part deepClone() {
        return new Part(this);
    }

    /**
     * Retorna uma representação textual da peça.
     * 
     * @return representação textual da peça.
     */
    @Override
    public String toString() {
        return String.format("Part{partKind: %d, unitValue: %.2f, quantity: %d}", partKind, unitValue, quantity);
    }
}