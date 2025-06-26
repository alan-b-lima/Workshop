package edu.ajan.model.workshop.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import edu.ajan.model.custom.WorkshopObject;

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
    protected Stock(Stock stock) {
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
     * @return produto de identificador {@code product}, ou {@code null} se um
     *         produto de identificador {@code product} não existir.
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
        products.put(product.id(), product);
    }

    /**
     * Remove um produto do estoque.
     * 
     * @param product identificador do produto a ser removido.
     */
    public void removeProduct(int product) {
        products.remove(product);
    }

    /**
     * Extensão de {@link #removeProduct(int)}. Remove vários produtos
     * do estoque.
     * 
     * @param products identificadores dos produtos a serem removidos.
     */
    public void removeProducts(int... products) {
        for (int id : products) {
            removeProduct(id);
        }
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
     * @return remessa de identificador {@code shipment}, ou {@code null} se uma
     *         remessa de identificador {@code shipment} não existir.
     */
    public Shipment getShipment(int shipment) {
        for (Shipment s : shipments) {
            if (s.id() == shipment) {
                return s;
            }
        }
        return null;
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
     * @param shipment identificador da remessa a ser removido.
     */
    public void removeShipment(int shipment) {
        shipments.removeIf(s -> s.id() == shipment);
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
     * @return fornecedor de identificador {@code supplier}, ou {@code null} se um
     *         fornecedor de identificador {@code supplier} não existir.
     */
    public Supplier getSupplier(int supplier) {
        for (Supplier s : suppliers) {
            if (s.id() == supplier) {
                return s;
            }
        }
        return null;
    }

    /**
     * Adiciona um fornecedor ao estoque.
     * 
     * @param supplier fornecedor a ser adicionado.
     */
    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    /**
     * Remove um fornecedor do estoque.
     * 
     * @param supplier identificador do fornecedor a ser removido.
     */
    public void removeSupplier(int supplier) {
        suppliers.removeIf(s -> s.id() == supplier);
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