package edu.ajan.model.workshop.financial;

import java.util.Arrays;

import edu.ajan.model.workshop.stock.Item;

/**
 * Classe que representa uma nota fiscal.
 * 
 * @author Alan Lima
 */
public class Invoice {

    /**
     * Produtos incluídos na nota fiscal.
     */
    private final Item[] products;

    /**
     * Serviços incluídos na nota fiscal.
     */
    private final Item[] services;

    /**
     * Adicional a ser cobrado na nota fiscal.
     * 
     * Pode ser negativo, indicando desconto.
     */
    private final double additional;

    /**
     * Construtor padrão.
     */
    public Invoice() {
        this.products = new Item[0];
        this.services = new Item[0];
        this.additional = 0.0;
    }

    /**
     * Construtor parametrizado.
     * 
     * @param products   produtos incluídos na nota fiscal.
     * @param services   serviços incluídos na nota fiscal.
     * @param additional adicional a ser cobrado na nota fiscal.
     */
    public Invoice(Item[] products, Item[] services, double additional) {
        this.products = products != null ? products : new Item[0];
        this.services = services != null ? services : new Item[0];
        this.additional = additional;
    }

    /**
     * Retorna os produtos incluídos na nota fiscal.
     * 
     * @return produtos incluídos na nota fiscal.
     */
    public Item[] products() {
        return products;
    }

    /**
     * Retorna os serviços incluídos na nota fiscal.
     * @return serviços incluídos na nota fiscal.
     */
    public Item[] services() {
        return services;
    }

    /**
     * Retorna o adicional a ser cobrado na nota fiscal.
     * 
     * @return adicional a ser cobrado na nota fiscal.
     */
    public double additional() {
        return additional;
    }

    /**
     * Retorna uma representação textual da nota fiscal.
     * 
     * @return representação textual da nota fiscal.
     */
    @Override
    public String toString() {
        return String.format("(%s %s %.2f)",
                Arrays.toString(products), Arrays.toString(services), additional);
    }
}
