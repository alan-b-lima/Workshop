package main.workshop.stock;

import main.workshop.exception.WorkshopException;

/**
 * Classe que representa uma peça.
 * 
 * @author Juan Pablo
 */
public class Part {

    /**
     * Nome da peça.
     */
    private String name;

    /**
     * Valor unitário da peça.
     */
    private double unitValue;

    /*
     * Quantidade de peças.
     */
    private int quantity;

    /**
     * Construtor padrão.
     */
    public Part() {
        
    }

    /**
     * Construtor que recebe o nome, valor unitário e quantidade da peça.
     * 
     * @param name      nome da peça.
     * @param unitValue valor unitário da peça.
     * @param quantity  quantidade de peças.
     */
    public Part(String name, double unitValue, int quantity) {
        this.setName(name);
        this.setUnitValue(unitValue);
        this.setQuantity(quantity);
    }

    /**
     * Retorna o nome da peça.
     * 
     * @return nome da peça.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da peça.
     * 
     * @param name nome da peça.
     */
    public void setName(String name) {
        this.name = name;
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
     * Retorna o valor total da peça.
     * 
     * @return valor total da peça.
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
     * Define o valor total da peça.
     * 
     * @param totalValue valor total da peça.
     */
    public void setTotalValue(double totalValue) {
        this.unitValue = totalValue / (double) quantity;
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
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Aciona à quantidade de peças.
     * 
     * @param quantity quantidade de peças a serem adicionadas.
     * 
     * @throws WorkshopException caso a quantidade a ser adicionada seja negativa.
     */
    public void addQuantity(int quantity) throws WorkshopException {
        if (quantity < 0) {
            throw new WorkshopException("Quantidade inválida - não é possível adicionar uma quantidade negativa.");
        }

        this.quantity += quantity;
    }

    /**
     * Remove à quantidade de peças.
     * 
     * @param quantity quantidade de peças a serem removidas.
     * 
     * @throws WorkshopException caso a quantidade a ser removida seja maior que a
     *                           quantidade disponível ou negativa.
     */
    public void removeQuantity(int quantity) throws WorkshopException {
        if (quantity > this.quantity) {
            throw new WorkshopException("Estoque insuficiente - não há peças suficientes para remover.");
        }

        if (quantity < 0) {
            throw new WorkshopException("Quantidade inválida - não é possível remover uma quantidade negativa.");
        }

        this.quantity -= quantity;
    }

    /** 
     * Retorna uma representação textual da peça.
     * 
     * @return representação textual da peça.
     */
    @Override
    public String toString() {
        return String.format("{%s, %.2f, %d}", name, unitValue, quantity);
    }
}