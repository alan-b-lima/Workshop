package edu.ajan.model.workshop.stock;

import edu.ajan.model.custom.DeepClonable;
import edu.ajan.model.custom.WorkshopObject;

/**
 * Classe que representa um produto no estoque.
 * 
 * @author Alan Lima
 */
public class Product extends WorkshopObject implements DeepClonable<Product> {

    /**
     * Contador de instâncias únicas de produtos criados.
     */
    private static int instanceCount = 0;

    /**
     * Valor padrão para a unidade de medida do produto.
     */
    private static final String DEFAULT_UNIT = "un";

    /**
     * Identificador único do produto.
     */
    private final int id;

    /**
     * Nome do produto.
     */
    private String name;

    /**
     * Valor unitário do produto.
     */
    private double unitValue;

    /**
     * Quantidade do produto em estoque.
     */
    private int quantity;

    /**
     * Unidade de medida do produto.
     */
    private String unit;

    /**
     * Construtor padrão.
     */
    public Product() {
        this.id = generateNextId();
    }

    /**
     * Construtor que permite definir o nome do produto.
     * 
     * @param name nome do produto.
     */
    public Product(String name) {
        this(name, 0.0, 0, DEFAULT_UNIT);
    }

    /**
     * Construtor parametrizado.
     * 
     * @param name      nome do produto.
     * @param unitValue valor unitário do produto.
     */
    public Product(String name, double unitValue) {
        this(name, unitValue, 0, DEFAULT_UNIT);
    }

    /**
     * Construtor parametrizado.
     * 
     * @param name      nome do produto.
     * @param unitValue valor unitário do produto.
     * @param quantity  quantidade do produto em estoque.
     */
    public Product(String name, double unitValue, int quantity) {
        this(name, unitValue, quantity, DEFAULT_UNIT);
    }

    /**
     * Construtor parametrizado.
     * 
     * @param name      nome do produto.
     * @param unitValue valor unitário do produto.
     * @param quantity  quantidade do produto em estoque.
     * @param unit      unidade de medida do produto.
     */
    public Product(String name, double unitValue, int quantity, String unit) {
        this();
        this.name = name;
        this.unitValue = unitValue;
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     * Construtor de clonagem.
     * 
     * @param product instância de a ser clonada.
     */
    private Product(Product product) {
        this.id = product.id;
        this.name = product.name;
        this.unitValue = product.unitValue;
        this.quantity = product.quantity;
        this.unit = product.unit;
    }

    /**
     * Retorna o identificador único do produto.
     * 
     * @return identificador único do produto.
     */
    public int id() {
        return id;
    }

    /**
     * Retorna o nome do produto.
     * 
     * @return nome do produto.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do produto.
     * 
     * @param name nome do produto.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o valor unitário do produto.
     * 
     * @return valor unitário do produto.
     */
    public double getUnitValue() {
        return unitValue;
    }

    /**
     * Calcula o valor total do produto em estoque.
     * 
     * @return valor total do produto em estoque.
     */
    public double getTotalValue() {
        return unitValue * (double) quantity;
    }

    /**
     * Define o valor unitário do produto.
     * 
     * @param unitValue valor unitário do produto.
     */
    public void setUnitValue(double unitValue) {
        this.unitValue = unitValue;
    }

    /**
     * Define o valor total do produto em estoque, recalculando o valor unitário.
     * 
     * @param totalValue valor total do produto em estoque.
     */
    public void setTotalValue(double totalValue) {
        this.unitValue = totalValue / (double) quantity;
    }

    /**
     * Retorna a quantidade do produto em estoque.
     * 
     * @return quantidade do produto em estoque.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Define a quantidade do produto em estoque.
     * 
     * @param quantity quantidade do produto em estoque.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Retorna a unidade de medida do produto.
     * 
     * @return unidade de medida do produto.
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Define a unidade de medida do produto.
     * 
     * @param unit unidade de medida do produto.
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Retorna o número total de instâncias de produtos criadas.
     * 
     * @return número total de instâncias de produtos criadas.
     */
    public static int getInstanceCount() {
        return instanceCount;
    }

    /**
     * Incrementa o contador de instâncias de produtos.
     */
    private static void incrementInstanceCount() {
        instanceCount++;
    }

    /**
     * Gera o próximo identificador único para um novo produto e incrementa o
     * contador de instâncias.
     * 
     * @return próximo identificador único.
     */
    private static int generateNextId() {
        incrementInstanceCount();
        return instanceCount;
    }

    /**
     * Cria um clone profundo do produto.
     * 
     * @return a instância clonada do produto.
     */
    @Override
    public Product deepClone() {
        return new Product(this);
    }

    /**
     * Retorna uma representação textual do produto.
     * 
     * @return representação textual do produto.
     */
    @Override
    public String toString() {
        return String.format("(%d \"%s\" %.2f %d \"%s\")", id, name, unitValue, quantity, unit);
    }
}
