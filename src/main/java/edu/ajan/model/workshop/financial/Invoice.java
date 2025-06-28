package edu.ajan.model.workshop.financial;

import java.util.Arrays;

import edu.ajan.model.workshop.date.Dates;
import edu.ajan.model.workshop.stock.Item;

/**
 * Classe que representa uma nota fiscal.
 * 
 * @author Alan Lima
 */
public class Invoice {

    /**
     * Identificador do cliente que solicitou a nota fiscal.
     */
    private final int customer;

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
     * Valor total cobrado na nota fiscal.
     */
    private final double subtotal;

    /**
     * Timestamp que representa a data da despesa.
     */
    private long date;

    /**
     * Construtor padrão.
     */
    public Invoice() {
        this.customer = 0;
        this.products = new Item[0];
        this.services = new Item[0];
        this.additional = 0.0;
        this.subtotal = 0.0;
    }

    /**
     * Construtor parametrizado.
     * 
     * @param customer   identificador do cliente que solicitou a nota fiscal.
     * @param products   produtos incluídos na nota fiscal.
     * @param services   serviços incluídos na nota fiscal.
     * @param additional adicional a ser cobrado na nota fiscal.
     */
    public Invoice(int customer, Item[] products, Item[] services, double additional, long date) {
        this.customer = customer;
        this.products = products != null ? products : new Item[0];
        this.services = services != null ? services : new Item[0];
        this.additional = additional;
        this.subtotal = additional
                + Arrays.stream(this.products).mapToDouble(product -> product.getBatch().value()).sum()
                + Arrays.stream(this.services).mapToDouble(service -> service.getBatch().value()).sum();
        this.date = date;
    }

    /**
     * Retorna o identificador do cliente que solicitou a nota fiscal.
     * 
     * @return identificador do cliente que solicitou a nota fiscal.
     */
    public int customer() {
        return customer;
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
     * 
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
     * Retorna o subtotal da nota fiscal.
     * 
     * @return subtotal da nota fiscal.
     */
    public double subtotal() {
        return subtotal;
    }

    /**
     * Retorna a data da nota fiscal.
     * 
     * @return data da nota fiscal.
     */
    public long date() {
        return date;
    }

    /**
     * Retorna uma representação textual da nota fiscal.
     * 
     * @return representação textual da nota fiscal.
     */
    @Override
    public String toString() {
        return String.format("(%d %s %s %.2f %.2f %s)",
                customer, Arrays.toString(products), Arrays.toString(services),
                additional, subtotal, Dates.formatAsDateTime(customer));
    }
}