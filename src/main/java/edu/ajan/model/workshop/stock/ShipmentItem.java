package edu.ajan.model.workshop.stock;

import edu.ajan.model.custom.DeepClonable;
import edu.ajan.model.custom.WorkshopObject;
import edu.ajan.model.exception.WorkshopException;

/**
 * Classe que representa um item de remessa.
 * 
 * @author Alan Lima
 */
public class ShipmentItem extends WorkshopObject implements DeepClonable<ShipmentItem> {

    /**
     * ID do produto associado a este item de remessa.
     */
    private int product;

    /**
     * Valor unitário do produto.
     */
    private double unitValue;

    /**
     * Quantidade do produto neste item de remessa.
     */
    private int quantity;

    /**
     * Construtor padrão.
     */
    public ShipmentItem() {

    }

    /**
     * Construtor parametrizado.
     * 
     * @param product   ID do produto associado a este item de remessa.
     * @param unitValue valor unitário do produto.
     * @param quantity  quantidade do produto neste item de remessa.
     * 
     * @throws WorkshopException se a quantidade for negativa.
     */
    public ShipmentItem(int product, double unitValue, int quantity) {
        this.setProduct(product);
        this.setUnitValue(unitValue);
        this.setQuantity(quantity);
    }

    /**
     * Construtor de clonagem.
     * 
     * @param item item de remessa a ser clonado.
     */
    private ShipmentItem(ShipmentItem item) {
        this.product = item.product;
        this.unitValue = item.unitValue;
        this.quantity = item.quantity;
    }

    /**
     * Retorna o identificador do produto associado a este item de remessa.
     * 
     * @return identificador do produto.
     */
    public int getProduct() {
        return product;
    }

    /**
     * Define o identificador do produto associado a este item de remessa.
     * 
     * @param product identificador do produto.
     */
    public void setProduct(int product) {
        this.product = product;
    }

    /**
     * Retorna o valor unitário do produto neste item de remessa.
     * 
     * @return valor unitário do produto.
     */
    public double getUnitValue() {
        return unitValue;
    }

    /**
     * Retorna o valor total deste item de remessa.
     * 
     * @return valor total do item de remessa.
     */
    public double getTotalValue() {
        return unitValue * (double) quantity;
    }

    /**
     * Define o valor unitário do produto neste item de remessa.
     * 
     * @param unitValue valor unitário a ser definido.
     * 
     * @throws WorkshopException se o valor unitário for negativo.
     */
    public void setUnitValue(double unitValue) {
        if (unitValue < 0) {
            throw new WorkshopException("valor unitário não pode ser negativo");
        }

        this.unitValue = unitValue;
    }

    /**
     * Define o valor total deste item de remessa.
     * 
     * @param totalValue valor total a ser definido.
     * 
     * @throws WorkshopException se o valor total for negativo.
     */
    public void setTotalValue(double totalValue) {
        if (totalValue < 0) {
            throw new WorkshopException("valor total não pode ser negativo");
        }

        this.unitValue = totalValue / (double) quantity;
    }

    /**
     * Retorna a quantidade do produto neste item de remessa.
     * 
     * @return quantidade do produto.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Define a quantidade do produto neste item de remessa.
     * 
     * @param quantity quantidade a ser definida.
     * 
     * @throws WorkshopException se a quantidade for negativa.
     */
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new WorkshopException("quantidade não pode ser negativa");
        }

        this.quantity = quantity;
    }

    /**
     * Adiciona uma quantidade ao item de remessa.
     * 
     * @param quantity quantidade a ser adicionada.
     * @throws WorkshopException se a quantidade a ser adicionada for negativa.
     */
    public void addQuantity(int quantity) {
        if (quantity < 0) {
            throw new WorkshopException("quantidade adicionada não pode ser negativa");
        }

        this.quantity += quantity;
    }

    /**
     * Remove uma quantidade do item de remessa.
     * 
     * @param quantity quantidade a ser removida.
     * @throws WorkshopException se a quantidade a ser removida for negativa ou
     *                           maior que a quantidade atual.
     */
    public void removeQuantity(int quantity) {
        if (quantity < 0) {
            throw new WorkshopException("quantidade removida não pode ser negativa");
        }

        if (this.quantity < quantity) {
            throw new WorkshopException("quantidade %d insufiencente para a remoção de %d", this.quantity, quantity);
        }

        this.quantity -= quantity;
    }

    /**
     * Cria um clone profundo do item de remessa.
     * 
     * @return a instânca clonada do item de remessa.
     */
    @Override
    public ShipmentItem deepClone() {
        return new ShipmentItem(this);
    }

    /**
     * Retorna uma representação textual do item de remessa.
     * 
     * @return representação textual do item de remessa.
     */
    @Override
    public String toString() {
        return String.format("(%d %.2f %d)", product, unitValue, quantity);
    }
}
