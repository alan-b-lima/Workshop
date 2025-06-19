package edu.ajan.model.workshop.stock;

import edu.ajan.model.custom.DeepClonable;
import edu.ajan.model.custom.WorkshopObject;

/**
 * Classe que representa um fornecedor.
 * 
 * @author Alan Lima
 */
public class Supplier extends WorkshopObject implements DeepClonable<Supplier> {

    /**
     * Contador de instâncias de fornecedores.
     */
    private static int instanceCount;

    /**
     * Identificador único do fornecedor.
     */
    private final int id;

    /**
     * Nome fantasia do fornecedor.
     */
    private String tradeName;

    /**
     * CNPJ do fornecedor.
     */
    private String cnpj;

    /**
     * Construtor padrão.
     */
    public Supplier() {
        this.id = generateNextId();
    }

    /**
     * Construtor parametrizado.
     * 
     * @param tradeName nome fantasia do fornecedor.
     * @param cnpj      CNPJ do fornecedor.
     */
    public Supplier(String tradeName, String cnpj) {
        this();
        this.setTradeName(tradeName);
        this.setCnpj(cnpj);
    }

    /**
     * Construtor de clonagem.
     * 
     * @param supplier fornecedor a ser clonado.
     */
    private Supplier(Supplier supplier) {
        this.id = supplier.id;
        this.tradeName = supplier.tradeName;
        this.cnpj = supplier.cnpj;
    }

    /**
     * Retorna o identificador único do fornecedor.
     * 
     * @return identificador do fornecedor.
     */
    public int id() {
        return id;
    }

    /**
     * Retorna o nome fantasia do fornecedor.
     * 
     * @return nome fantasia do fornecedor.
     */
    public String getTradeName() {
        return tradeName;
    }

    /**
     * Define o nome fantasia do fornecedor.
     * 
     * @param tradeName nome fantasia do fornecedor.
     */
    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    /**
     * Retorna o CNPJ do fornecedor.
     * 
     * @return CNPJ do fornecedor.
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Define o CNPJ do fornecedor.
     * 
     * @param cnpj CNPJ do fornecedor.
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Retorna o número total de instâncias de fornecedores criadas.
     * 
     * @return número total de instâncias de fornecedores.
     */
    public static int getInstanceCount() {
        return instanceCount;
    }

    /**
     * Incrementa o contador de instâncias de fornecedores.
     */
    private static void incrementInstanceCount() {
        instanceCount++;
    }

    /**
     * Gera o próximo identificador único para um novo fornecedor e incrementa o
     * contador de instâncias.
     * 
     * @return o próximo identificador único.
     */
    private static int generateNextId() {
        incrementInstanceCount();
        return instanceCount;
    }

    /**
     * Cria um clone profundo do fornecedor.
     * 
     * @return a instância clonada do fornecedor.
     */
    @Override
    public Supplier deepClone() {
        return new Supplier(this);
    }

    /**
     * Retorna uma representação textual do fornecedor.
     * 
     * @return representação textual do fornecedor.
     */
    @Override
    public String toString() {
        return String.format("(%d \"%s\" %s)", id, tradeName, cnpj);
    }
}
