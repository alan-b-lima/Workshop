package edu.ajan.model.workshop.financial;

import java.util.Collection;
import java.util.HashMap;

import edu.ajan.model.workshop.stock.Item;

/**
 * Classe que representa uma rascunho de nota fiscal.
 * 
 * @author Alan Lima
 */
public class InvoiceDraft {

    /**
     * Mapa de produtos indexados pelo identificador do produto.
     */
    private HashMap<Integer, Item> products;

    /**
     * Mapa de serviços indexados pelo identificador do serviço.
     */
    private HashMap<Integer, Item> services;

    /**
     * Construtor padrão.
     */
    public InvoiceDraft() {
        this.products = new HashMap<>();
        this.services = new HashMap<>();
    }

    /**
     * Retorna uma estrutura iterável de produtos.
     * 
     * @return estrutura iterável de produtos.
     */
    public Collection<Item> getProducts() {
        return products.values();
    }

    /**
     * Retorna um array de produtos.
     * 
     * @return array de produtos.
     */
    public Item[] getProductsAsArray() {        
        return products.values().toArray(new Item[0]);
    }

    /**
     * Retorna um produto a partir do seu identificador.
     * 
     * @param productId identificador de produto.
     * @return produto de identificador passado, ou {@code null} se esse produto não
     *         existir.
     */
    public Item getProduct(int productId) {
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
     * Adiciona um produto.
     * 
     * @param product produto a ser adicionado.
     */
    public void addProduct(Item product) {
        products.put(product.getInfo(), product);
    }

    /**
     * Remove um produto.
     * 
     * @param productId identificador do produto a ser removido.
     */
    public void removeProduct(int productId) {
        products.remove(productId);
    }

    /**
     * Retorna uma estrutura iterável de serviços.
     * 
     * @return estrutura iterável de serviços.
     */
    public Collection<Item> getServices() {
        return services.values();
    }

    /**
     * Retorna um array de serviços.
     * 
     * @return array de serviços.
     */
    public Item[] getServicesAsArray() {
        return services.values().toArray(new Item[0]);
    }

    /**
     * Retorna um serviço a partir do seu identificador.
     * 
     * @param serviceId identificador de serviço.
     * @return serviço de identificador passado, ou {@code null} se esse serviço não
     *         existir.
     */
    public Item getService(int serviceId) {
        return services.get(serviceId);
    }

    /**
     * Asserta a existência de um serviço.
     * 
     * @param serviceId identificador de serviço.
     * @return {@code true} se, e somente se o serviço existir.
     */
    public boolean hasService(int serviceId) {
        return services.containsKey(serviceId);
    }

    /**
     * Adiciona um serviço.
     * 
     * @param service serviço a ser adicionado.
     */
    public void addService(Item service) {
        services.put(service.getInfo(), service);
    }

    /**
     * Remove um serviço.
     * 
     * @param serviceId identificador do serviço a ser removido.
     */
    public void removeService(int serviceId) {
        services.remove(serviceId);
    }

    /**
     * Retorna uma representação textual da rascunho nota fiscal.
     * 
     * @return representação textual da rascunho nota fiscal.
     */
    @Override
    public String toString() {
        return String.format("(%s %s)", products, services);
    }
}
