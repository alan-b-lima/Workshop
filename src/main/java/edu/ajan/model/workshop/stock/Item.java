package edu.ajan.model.workshop.stock;

import edu.ajan.model.exception.WorkshopException;

/**
 * Classe que representa um item (produto ou serviço) quantificado e
 * precificado.
 * 
 * @author Alan Lima
 */
public class Item {

    /**
     * Informação de identificação de peça ou serviço.
     * 
     * O contedor é responsável pela asserção de existência estrangeira.
     */
    private int info;

    /**
     * Quantidade precificada do item.
     */
    private PricedQuantity batch;

    /**
     * Construtor padrão.
     */
    public Item() {
        this.info = 0;
        this.batch = new PricedQuantity(0, 0.0);
    }

    /**
     * Construtor parametrizado.
     * 
     * @param info  informação de identificação de peça ou serviço.
     * @param batch quantidade precificada do item.
     * 
     * @throws WorkshopException se algum parametro for inválido.
     */
    public Item(int info, PricedQuantity batch) {
        this.setInfo(info);
        this.setBatch(batch);
    }

    /**
     * Retorna a informação de idenficação do item.
     * 
     * @return informação de identificação do item.
     */
    public int getInfo() {
        return info;
    }

    /**
     * Define a informação de identificação do item.
     * 
     * @param info informação de identificação do item.
     */
    public void setInfo(int info) {
        this.info = info;
    }

    /**
     * Retorna a quantidade precificada do item.
     * 
     * @return quantidade precificada do item.
     */
    public PricedQuantity getBatch() {
        return batch;
    }

    /**
     * Define a quantidade precificada do item.
     * @param batch quantidade precificada do item.
     * 
     * @throws WorkshopException se a quantidade ou o valor forem negativos.
     */
    public void setBatch(PricedQuantity batch) {

        if (batch.quantity() < 0) {
            throw new WorkshopException("quantidade não pode ser negativa");
        }

        if (batch.value() < 0.0) {
            throw new WorkshopException("valor não pode ser negativo");
        }

        this.batch = batch;
    }

    /**
     * Retorna uma representação textual do item.
     * 
     * @return representação textual do item.
     */
    @Override
    public String toString() {
        return String.format("(%d %s)", info, batch);
    };
}
