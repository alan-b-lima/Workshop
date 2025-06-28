package edu.ajan.model.workshop.stock;

import edu.ajan.model.custom.DeepClonable;
import edu.ajan.model.custom.WorkshopObject;
import edu.ajan.model.exception.WorkshopException;

/**
 * Classe que representa um produto no estoque.
 * 
 * @author Alan Lima
 */
public class Product extends WorkshopObject implements DeepClonable<Product> {

    /**
     * Contador de instâncias.
     */
    private static int instanceCount;

    /**
     * Identificador único do produto.
     */
    private final int id;

    /**
     * Nome do produto.
     */
    private String name;

    /**
     * Quantidade precificada do produto.
     */
    private PricedQuantity batch;

    /**
     * Unidade de medida do produto.
     */
    private String unit;

    /**
     * Construtor padrão.
     */
    public Product() {
        this.id = generateNextId();
        this.name = "";
        this.batch = new PricedQuantity(0, 0);
        this.unit = "";
    }

    /**
     * Construtor parametrizado.
     * 
     * @param name      nome do produto.
     * @param batch     quantidade precificada do produto.
     * @param unit      unidade de medida do produto.
     */
    public Product(String name, PricedQuantity batch, String unit) {
        this();
        this.setName(name);
        this.setBatch(batch);
        this.setUnit(unit);
    }

    /**
     * Construtor de clonagem.
     * 
     * @param product produto a ser clonado.
     */
    protected Product(Product product) {
        this.id = product.id;
        this.name = product.name;
        this.batch = product.batch; // Instância imutável, não é necessário clonar
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
     * 
     * @throws WorkshopException se o nome for nulo.
     */
    public void setName(String name) {
        if (name == null) {
            throw new WorkshopException("nome não pode ser nulo");
        }

        this.name = name;
    }

    /**
     * Retorna a quantidade precificada do produto.
     * 
     * @return quantidade precificada do produto.
     */
    public PricedQuantity getBatch() {
        return batch;
    }

    /**
     * Define a quantidade precificada do produto.
     * 
     * @param batch quantidade precificada do produto.
     */
    public void setBatch(PricedQuantity batch) {

        if (batch.quantity() < 0) {
            throw new WorkshopException("quantidade não pode ser negativa");
        }

        if (batch.value() < 0.0) {
            throw new WorkshopException("valor não pode ser negativo");
        }

        this.batch = batch;
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
     * 
     * @throws WorkshopException se a unidade de medida for nula.
     */
    public void setUnit(String unit) {
        if (unit == null) {
            throw new WorkshopException("unidade de medida não pode ser nula");
        }

        this.unit = unit;
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
        return String.format("(%d \"%s\" %s \"%s\")", id, name, batch, unit);
    }
}
