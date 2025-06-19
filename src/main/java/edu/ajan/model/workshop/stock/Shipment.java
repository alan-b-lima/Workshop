package edu.ajan.model.workshop.stock;

import java.util.ArrayList;

import edu.ajan.model.custom.DeepClonable;
import edu.ajan.model.custom.WorkshopObject;
import edu.ajan.model.exception.WorkshopException;

/**
 * Classe que representa uma remessa de produtos.
 * 
 * @author Alan Lima
 */
public class Shipment extends WorkshopObject implements DeepClonable<Shipment> {

    /**
     * Contador de instâncias de Shipment.
     */
    private static int instanceCount;

    /**
     * Identificador único da remessa.
     */
    private final int id;

    /**
     * Identificador do fornecedor da remessa.
     */
    private int supplier;

    /**
     * Lista de itens da remessa.
     */
    private ArrayList<ShipmentItem> items;

    /**
     * Valor adicional da remessa, como frete ou taxas.
     */
    private double additional;

    /**
     * Data de chegada da remessa.
     */
    private long arrival;

    /**
     * Data de pagamento da remessa.
     * 
     * {@code 0 indica que o pagamento não foi realizado ainda.}
     */
    private long paymentDate;

    /**
     * Indica se a remessa foi contabilizada no estoque.
     */
    private boolean accounted;

    /**
     * Construtor padrão.
     */
    public Shipment() {
        this.id = generateNextId();
        this.items = new ArrayList<>();
    }

    /**
     * Construtor parametrizado.
     * 
     * @param supplier    identificador do fornecedor da remessa.
     * @param additional  valor adicional da remessa, como frete ou taxas.
     * @param arrival     data de chegada da remessa.
     * @param paymentDate data de pagamento da remessa.
     * @param accounted   indica se a remessa foi contabilizada no estoque.
     */
    public Shipment(int supplier, double additional, long arrival, long paymentDate, boolean accounted) {
        this();
        this.supplier = supplier;
        this.additional = additional;
        this.arrival = arrival;
        this.paymentDate = paymentDate;
        this.accounted = accounted;
    }

    /**
     * Construtor de clonagem.
     * 
     * @param shipment remessa a ser clonada.
     */
    private Shipment(Shipment shipment) {
        this.id = shipment.id;
        this.supplier = shipment.supplier;
        this.items = new ArrayList<>(shipment.items.size());
        this.additional = shipment.additional;
        this.arrival = shipment.arrival;
        this.paymentDate = shipment.paymentDate;
        this.accounted = shipment.accounted;

        for (ShipmentItem item : items) {
            this.items.add(item.deepClone());
        }
    }

    /**
     * Retorna o identificador único da remessa.
     * 
     * @return identificador único da remessa.
     */
    public int id() {
        return id;
    }

    /**
     * Retorna o identificador do fornecedor da remessa.
     * 
     * @return identificador do fornecedor da remessa.
     */
    public int getSupplier() {
        return supplier;
    }

    /**
     * Define o identificador do fornecedor da remessa.
     * 
     * @param supplier identificador do fornecedor da remessa.
     */
    public void setSupplier(int supplier) {
        this.supplier = supplier;
    }

    /**
     * Retorna uma estrutura iterável de itens da remessa.
     * 
     * @return estrutura iterável de itens da remessa.
     */
    public Iterable<ShipmentItem> getItems() {
        return items;
    }

    /**
     * Retorna um item da remessa com base no identificador do produto.
     * 
     * @param product identificador do produto.
     * @return o item da remessa correspondente ao produto, ou {@code null} se não
     *         encontrado.
     */
    public ShipmentItem getItem(int product) {
        throw WorkshopException.methodNotImplemented("getItem");
    }

    /**
     * Adiciona um item à remessa.
     * 
     * @param item o item a ser adicionado.
     */
    public void addItem(ShipmentItem item) {
        // ShipmentItem foundItem = getItem(item.getProduct());
        // if (foundItem == null) {
        //     this.items.add(item);
        //     return;
        // }

        // foundItem.addQuantity(item.getQuantity());
        throw WorkshopException.methodNotImplemented("addItem");
    }

    /**
     * Adiciona múltiplos itens à remessa.
     * 
     * @param itens os itens a serem adicionados.
     */
    public void addItems(ShipmentItem... itens) {
        for (ShipmentItem item : itens) {
            addItem(item);
        }
    }

    /**
     * Remove um item da remessa com base no identificador do produto.
     * 
     * @param product identificador do produto a ser removido.
     */
    public void removeItem(int product) {
        throw WorkshopException.methodNotImplemented("removeItem");
    }

    /**
     * Remove múltiplos itens da remessa com base nos identificadores dos produtos.
     * 
     * @param product identificadores dos produtos a serem removidos.
     */
    public void removeItems(int... product) {
        throw WorkshopException.methodNotImplemented("removeItems");
    }

    /**
     * Retorna o valor adicional da remessa, como frete ou taxas.
     * 
     * @return valor adicional da remessa.
     */
    public double getAdditional() {
        return additional;
    }

    /**
     * Define o valor adicional da remessa, como frete ou taxas.
     * 
     * @param additional valor adicional da remessa.
     */
    public void setAdditional(double additional) {
        this.additional = additional;
    }

    /**
     * Retorna a data de chegada da remessa.
     * 
     * @return data de chegada da remessa.
     */
    public long getArrival() {
        return arrival;
    }

    /**
     * Verifica se a remessa chegou.
     * 
     * @return {@code true} se a remessa chegou, {@code false} caso contrário.
     */
    public boolean hasArrived() {
        throw WorkshopException.methodNotImplemented("hasArrived");
    }

    /**
     * Define a data de chegada da remessa.
     * 
     * @param arrival data de chegada da remessa.
     */
    public void setArrival(long arrival) {
        this.arrival = arrival;
    }

    /**
     * Retorna a data de pagamento da remessa.
     * 
     * @return data de pagamento da remessa.
     */
    public long getPaymentDate() {
        return paymentDate;
    }

    /**
     * Verifica se a remessa foi paga.
     * 
     * @return {@code true} se a remessa foi paga, {@code false} caso contrário.
     */
    public boolean isPaid() {
        throw WorkshopException.methodNotImplemented("isPaid");
    }

    /**
     * Define a data de pagamento da remessa.
     * 
     * @param paymentDate data de pagamento da remessa.
     */
    public void setPaymentDate(long paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Verifica se a remessa foi contabilizada no estoque.
     * 
     * @return {@code true} se a remessa foi contabilizada, {@code false} caso
     *         contrário.
     */
    public boolean isAccounted() {
        return accounted;
    }

    /**
     * Define que a remessa foi contabilizada no estoque.
     */
    public void setAccounted() {
        this.accounted = true;
    }

    /**
     * Define se a remessa foi contabilizada no estoque.
     * 
     * @param accounted {@code true} se a remessa foi contabilizada, {@code false}
     *                  caso contrário.
     */
    public void setAccounted(boolean accounted) {
        this.accounted = accounted;
    }

    /**
     * Retorna o número total de instâncias de Shipment criadas.
     * 
     * @return número total de instâncias de Shipment.
     */
    public static int getInstanceCount() {
        return instanceCount;
    }

    /**
     * Incrementa o contador de instâncias de Shipment.
     */
    private static void incrementInstanceCount() {
        instanceCount++;
    }

    /**
     * Gera o próximo identificador único para uma nova remessa e incrementa o
     * contador de instâncias.
     * 
     * @return o próximo identificador único.
     */
    private static int generateNextId() {
        incrementInstanceCount();
        return instanceCount;
    }

    /**
     * Cria um clone profundo da remessa.
     * 
     * @return a instânca clonada da remessa.
     */
    @Override
    public Shipment deepClone() {
        return new Shipment(this);
    }

    /**
     * Retorna uma representação textual da remessa.
     * 
     * @return representação textual da remessa.
     */
    @Override
    public String toString() {
        return String.format("(%d %d \"%s\" %.2f %d %b)",
                id, supplier, items, additional, arrival, paymentDate, accounted);
    }
}
