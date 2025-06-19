package edu.ajan.model.workshop.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import edu.ajan.model.custom.WorkshopObject;
import edu.ajan.model.exception.WorkshopException;

/**
 * Classe singleton que representa o estoque.
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
    private TreeSet<Supplier> suppliers;

    /**
     * Instância única do estoque.
     */
    private static Stock instance = new Stock();

    /**
     * Construtor padrão privado.
     */
    private Stock() {
        this.products = new HashMap<>();
        this.shipments = new ArrayList<>();
        this.suppliers = new TreeSet<>();
    }

    /**
     * Constructor de clonagem.
     * 
     * @param stock estoque a ser clonado.
     */
    private Stock(Stock stock) {
        this.products = new HashMap<>(stock.products.size());
        this.shipments = new ArrayList<>(stock.shipments.size());
        this.suppliers = new TreeSet<>();

        for (Product product : stock.products.values()) {
            this.products.put(product.id(), product.deepClone());
        }

        for (Shipment shipment : stock.shipments) {
            this.shipments.add(shipment.deepClone());
        }

        for (Supplier supplier : stock.suppliers) {
            this.suppliers.add(supplier.deepClone());
        }
    }

    /**
     * Retorna a instância única do estoque.
     * 
     * @return instância única do estoque.
     */
    public Stock stock() {
        return instance;
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
     * Retorna um produto a partir do seu idenficador.
     * 
     * @param product identificador de produto.
     * @return produto de identificador {@product}, ou {@code null}, caso um produto
     *         de identificador {@product} não existir.
     */
    public Product getProduct(int product) {
        return products.get(product);
    }

    /**
     * Adiciona um produto ao estoque.
     * 
     * @param product produto a ser adicionado.
     */
    public void addProduct(Product product) {
        throw WorkshopException.methodNotImplemented("addProduct");
    }

    /**
     * Extensão de {@link #addProduct(Product)}. Adiciona vários produtos
     * ao estoque.
     * 
     * @param products produtos a serem adicionados.
     */
    public void addProducts(Product... products) {
        throw WorkshopException.methodNotImplemented("addProducts");
    }

    /**
     * Remove um produto do estoque.
     * 
     * @param product identificador do produto a ser removido.
     */
    public void removeProduct(int product) {
        throw WorkshopException.methodNotImplemented("removeProduct");
    }

    /**
     * Extensão de {@link #removeProduct(int)}. Remove vários produtos
     * do estoque.
     * 
     * @param products identificadores dos produtos a serem removidos.
     */
    public void removeProducts(int... products) {
        throw WorkshopException.methodNotImplemented("removeProducts");
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
     * Retorna uma remessa a partir do seu idenficador.
     * 
     * @param shipment identificador de remessa.
     * @return remessa de identificador {@shipment}, ou {@code null}, caso uma
     *         remessa de identificador {@shipment} não existir.
     */
    public Shipment getShipment(int shipment) {
        throw WorkshopException.methodNotImplemented("getShipment");
    }

    /**
     * Adiciona uma remessa ao estoque.
     * 
     * @param shipment remessa a ser adicionado.
     */
    public void addShipment(Shipment shipment) {
        throw WorkshopException.methodNotImplemented("addShipment");
    }

    /**
     * Extensão de {@link #addShipment(Shipment)}. Adiciona vários remessas
     * ao estoque.
     * 
     * @param shipments remessas a serem adicionados.
     */
    public void addShipments(Shipment... shipments) {
        throw WorkshopException.methodNotImplemented("addShipments");
    }

    /**
     * Remove uma remessa do estoque.
     * 
     * @param shipment identificador da remessa a ser removido.
     */
    public void removeShipment(int shipment) {
        throw WorkshopException.methodNotImplemented("removeShipment");
    }

    /**
     * Extensão de {@link #removeShipment(int)}. Remove várias remessas
     * do estoque.
     * 
     * @param shipments identificadores das remessas a serem removidos.
     */
    public void removeShipments(int... shipments) {
        throw WorkshopException.methodNotImplemented("removeShipments");
    }

    /**
     * Retorna uma estrutura iterável de fornecedores.
     * 
     * @return estrutura iterável de fornecedores.
     */
    public Iterable<Supplier> getSuppliers() {
        return suppliers;
    }

    /**
     * Retorna um fornecedor a partir do seu idenficador.
     * 
     * @param supplier identificador de fornecedor.
     * @return fornecedor de identificador {@supplier}, ou {@code null}, caso um
     *         fornecedor de identificador {@supplier} não existir.
     */
    public Supplier getSupplier(int supplier) {
        throw WorkshopException.methodNotImplemented("getSupplier");
    }

    /**
     * Adiciona um fornecedor ao estoque.
     * 
     * @param supplier fornecedor a ser adicionado.
     */
    public void addSupplier(Supplier supplier) {
        throw WorkshopException.methodNotImplemented("addSupplier");
    }

    /**
     * Extensão de {@link #addSupplier(Supplier)}. Adiciona vários fornecedores.
     * 
     * @param suppliers fornecedores a serem adicionados.
     */
    public void addSuppliers(Supplier... suppliers) {
        throw WorkshopException.methodNotImplemented("addSuppliers");
    }

    /**
     * Remove um fornecedor do estoque.
     * 
     * @param supplier identificador do fornecedor a ser removido.
     */
    public void removeSupplier(int supplier) {
        throw WorkshopException.methodNotImplemented("removeSupplier");
    }

    /**
     * Extensão de {@link #removeSupplier(int)}. Remove vários fornecedores.
     * 
     * @param suppliers identificadores dos fornecedores a serem removidos.
     */
    public void removeSuppliers(int... suppliers) {
        throw WorkshopException.methodNotImplemented("removeSuppliers");
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
