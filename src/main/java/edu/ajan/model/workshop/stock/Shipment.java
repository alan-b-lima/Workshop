package edu.ajan.model.workshop.stock;

import java.util.ArrayList;

import edu.ajan.model.custom.DeepClonable;
import edu.ajan.model.custom.WorkshopObject;
import edu.ajan.model.workshop.date.Dates;

/**
 * Classe que representa uma remessa de produtos.
 * 
 * @author Alan Lima
 * @author Juan Pablo
 */
public class Shipment extends WorkshopObject implements DeepClonable<Shipment> {

    /**
     * Denota invalidez de timestamp.
     */
    public static final long UNSET_TIME = -1L;

    /**
     * Contador de instâncias.
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
    private ArrayList<Item> items;

    /**
     * Valor adicional da remessa, como frete ou taxas.
     */
    private double additional;

    /**
     * Data de pagamento da remessa.
     * 
     * {@link #UNSET_TIME} indica que o pagamento não foi realizado ainda.
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
        this.paymentDate = UNSET_TIME;
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
    public Shipment(int supplier, double additional, long paymentDate, boolean accounted) {
        this();
        this.supplier = supplier;
        this.additional = additional;
        this.paymentDate = paymentDate;
        this.accounted = accounted;
    }

    /**
     * Construtor de clonagem.
     * 
     * @param shipment remessa a ser clonada.
     */
    protected Shipment(Shipment shipment) {
        this.id = shipment.id;
        this.supplier = shipment.supplier;
        this.items = new ArrayList<>(shipment.items.size());
        this.additional = shipment.additional;
        this.paymentDate = shipment.paymentDate;
        this.accounted = shipment.accounted;

        for (Item item : items) {
            this.items.add(item); // Incompleto
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
     * @param supplierId identificador do fornecedor da remessa.
     */
    public void setSupplier(int supplierId) {
        this.supplier = supplierId;
    }

    /**
     * Retorna uma estrutura iterável de itens.
     * 
     * @return estrutura iterável de itens.
     */
    public Iterable<Item> getItems() {
        return items;
    }

    /**
     * Retorna um item com base no identificador do produto.
     * 
     * @param itemId identificador do produto.
     * @return o item correspondente ao produto, ou {@code null} se não encontrado.
     */
    public Item getItem(int itemId) {
        for (Item item : items) {
            if (item.getInfo() == itemId) {
                return item;
            }
        }

        return null;
    }

    /**
     * Adiciona um item à remessa.
     * 
     * @param item o item a ser adicionado.
     */
    public void addItem(Item item) {

        Item foundItem = getItem(item.getInfo());
        if (foundItem == null) {
            this.items.add(item);
            return;
        }

        PricedQuantity newBatch = item.getBatch().add(foundItem.getBatch());
        foundItem.setBatch(newBatch);
    }

    /**
     * Remove um item com base no identificador do produto.
     * 
     * @param itemId identificador do produto a ser removido.
     */
    public void removeItem(int itemId) {
        items.removeIf(item -> item.getInfo() == itemId);
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
        return paymentDate != UNSET_TIME;
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
     * Define se a remessa foi contabilizada no estoque.
     * 
     * @param accounted {@code true} se a remessa foi contabilizada, {@code false}
     *                  caso contrário.
     */
    public void setAccounted(boolean accounted) {
        this.accounted = accounted;
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
        return String.format("(%d %d %s %.2f %s %b)",
                id, supplier, items, additional, Dates.formatAsDate(paymentDate), accounted);
    }
}