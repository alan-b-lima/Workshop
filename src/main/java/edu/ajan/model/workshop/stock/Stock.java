package edu.ajan.model.workshop.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import edu.ajan.model.custom.WorkshopObject;

/**
 * Classe que representa o estoque.
 * 
 * @author Alan Lima
 */
public class Stock extends WorkshopObject {

    /**
     * Mapa de produtos indexados pelo identificador do produto.
     */
    private HashMap<Integer, Product> products;

    /**
     * Lista de remessas.
     */
    private ArrayList<Shipment> shipments;

    /**
     * Lista de fornecedores.
     */
    private TreeMap<Integer, Supplier> suppliers;

    /**
     * Construtor padrão.
     */
    public Stock() {
        this.products = new HashMap<>();
        this.shipments = new ArrayList<>();
        this.suppliers = new TreeMap<>();
    }

    /**
     * Constructor de clonagem.
     * 
     * @param stock estoque a ser clonado.
     */
    protected Stock(Stock stock) {
        this.products = new HashMap<>(stock.products.size());
        this.shipments = new ArrayList<>(stock.shipments.size());
        this.suppliers = new TreeMap<>();

        for (Product product : stock.products.values()) {
            this.products.put(product.id(), product.deepClone());
        }

        for (Shipment shipment : stock.shipments) {
            this.shipments.add(shipment.deepClone());
        }

        for (Supplier supplier : stock.suppliers.values()) {
            this.suppliers.put(supplier.id(), supplier.deepClone());
        }
    }

    /**
     * Retorna uma estrutura iterável de produtos.
     * 
     * @return estrutura iterável de produtos.
     */
    public Iterable<Product> getProducts() {
        return products.values();
    }

    /**
     * Retorna um produto a partir do seu identificador.
     * 
     * @param productId identificador de produto.
     * @return produto de identificador passado, ou {@code null} se essa produto não
     *         existir.
     */
    public Product getProduct(int productId) {
        return products.get(productId);
    }

    /**
     * Asserta a existência de um produto.
     * 
     * @param productId identificador de produto.
     * @return {@code true} se, e somente se o produto existir.
     */
    public boolean hasProduct(int productId) {
        return products.containsKey(productId);
    }

    /**
     * Adiciona um produto ao estoque.
     * 
     * @param product produto a ser adicionado.
     */
    public void addProduct(Product product) {
        products.put(product.id(), product);
    }

    /**
     * Remove um produto do estoque.
     * 
     * @param productId identificador do produto a ser removido.
     */
    public void removeProduct(int productId) {
        products.remove(productId);
    }

    /**
     * Retorna uma estrutura iterável de remessas.
     * 
     * @return estrutura iterável de remessas.
     */
    public Iterable<Shipment> getShipments() {
        return shipments;
    }

    /**
     * Retorna uma remessa a partir do seu identificador.
     * 
     * @param shipmentId identificador de remessa.
     * @return remessa de identificador passado, ou {@code null} se essa remessa não
     *         existir.
     */
    public Shipment getShipment(int shipmentId) {
        for (Shipment shipment : shipments) {
            if (shipment.id() == shipmentId) {
                return shipment;
            }
        }

        return null;
    }

    /**
     * Asserta a existência de uma remessa.
     * 
     * @param shipmentId identificador de remessa.
     * @return {@code true} se, e somente se a remessa existir.
     */
    public boolean hasShipment(int shipmentId) {
        for (Shipment shipment : shipments) {
            if (shipment.id() == shipmentId) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adiciona uma remessa ao estoque.
     * 
     * @param shipment remessa a ser adicionado.
     */
    public void addShipment(Shipment shipment) {
        shipments.add(shipment);
    }

    /**
     * Remove uma remessa do estoque.
     * 
     * @param shipmentId identificador da remessa a ser removido.
     */
    public void removeShipment(int shipmentId) {
        shipments.removeIf(shipment -> shipment.id() == shipmentId);
    }

    /**
     * Contabiliza um agendamento no estoque.
     * 
     * @param shipmentId identificador da remessa a ser contabilizado.
     */
    public void accountShipment(int shipmentId) {

        Shipment shipment = getShipment(shipmentId);
        if (shipment == null || shipment.isAccounted()) {
            return;
        }

        for (Item item : shipment.getItems()) {

            Product product = getProduct(item.getInfo());
            if (product == null) {
                continue;
            }

            PricedQuantity newBatch = item.getBatch().add(product.getBatch());
            product.setBatch(newBatch);
        }

        shipment.setAccounted(true);
    }

    /**
     * Retorna uma estrutura iterável de fornecedores.
     * 
     * @return estrutura iterável de fornecedores.
     */
    public Iterable<Supplier> getSuppliers() {
        return suppliers.values();
    }

    /**
     * Retorna um fornecedor a partir do seu identificador.
     * 
     * @param supplierId identificador de fornecedor.
     * @return fornecedor de identificador passado, ou {@code null} se essa
     *         fornecedor não existir.
     */
    public Supplier getSupplier(int supplierId) {
        return suppliers.get(supplierId);
    }
    
    /**
     * Asserta a existência de um produto.
     * 
     * @param supplierId identificador de produto.
     * @return {@code true} se, e somente se o produto existir.
     */
    public boolean hasSupplier(int supplierId) {
        return suppliers.containsKey(supplierId);
    }

    /**
     * Adiciona um fornecedor ao estoque.
     * 
     * @param supplier fornecedor a ser adicionado.
     */
    public void addSupplier(Supplier supplier) {
        suppliers.put(supplier.id(), supplier);
    }

    /**
     * Remove um fornecedor do estoque.
     * 
     * @param supplierId identificador do fornecedor a ser removido.
     */
    public void removeSupplier(int supplierId) {
        suppliers.remove(supplierId);
    }

    /**
     * Cria um clone profundo do estoque.
     * 
     * @return a instância clonada do estoque.
     */
    @Override
    public WorkshopObject deepClone() {
        return new Stock(this);
    }

    /**
     * Retorna uma representação textual do estoque.
     * 
     * @return representação textual do estoque.
     */
    @Override
    public String toString() {
        return String.format("(%s %s %s)", products, shipments, suppliers);
    }
}