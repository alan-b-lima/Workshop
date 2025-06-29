package edu.ajan.model.workshop.financial;

import java.util.Arrays;

import edu.ajan.model.persistence.InstanceCountState;
import edu.ajan.model.workshop.date.Dates;
import edu.ajan.model.workshop.stock.Item;

/**
 * Classe que representa uma nota fiscal.
 * 
 * @author Alan Lima
 */
public class Invoice {

    /**
     * Contador de instâncias.
     */
    private static int instanceCount;

    /**
     * Identificador único de nota fiscal.
     */
    private final int id;

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
    private final long date;

    /**
     * Construtor padrão.
     */
    public Invoice() {
        this.id = generateNextId();
        this.customer = 0;
        this.products = new Item[0];
        this.services = new Item[0];
        this.additional = 0.0;
        this.subtotal = 0.0;
        this.date = 0L;
    }

    /**
     * Construtor parametrizado.
     * 
     * @param customer   identificador do cliente que solicitou a nota fiscal.
     * @param products   produtos incluídos na nota fiscal.
     * @param services   serviços incluídos na nota fiscal.
     * @param additional adicional a ser cobrado na nota fiscal.
     * @param date       timestamp que representa a data da nota fiscal.
     */
    public Invoice(int customer, Item[] products, Item[] services, double additional, long date) {
        this.id = generateNextId();
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
     * Retorna o identificador de notas fiscais.
     * 
     * @return identificador de notas fiscais.
     */
    public int id() {
        return id;
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
     * Retorna o número total de instâncias criadas.
     * 
     * @return número total de instâncias criadas.
     */
    public static int getInstanceCount() {
        return instanceCount;
    }

    /**
     * Restaura o contador de instâncias a partir do estado salvo.
     * 
     * @param state estado salvo do contador de instâncias.
     */
    public static void restoreInstanceCount(InstanceCountState state) {
        if (state == null) {
            return;
        }

        instanceCount = state.get(Invoice.class);
    }

    /**
     * Incrementa o contador de instâncias.
     */
    private static void incrementInstanceCount() {
        instanceCount++;
    }

    /**
     * Gera o próximo identificador e incrementa o contador de instâncias.
     * 
     * @return próximo identificador único.
     */
    private static int generateNextId() {
        incrementInstanceCount();
        return instanceCount;
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
                additional, subtotal, Dates.formatAsDateTime(date));
    }
}