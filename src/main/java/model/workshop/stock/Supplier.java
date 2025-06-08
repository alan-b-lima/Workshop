package model.workshop.stock;

/**
 * Classe que representa um fornecedor.
 * 
 * @author Alan Lima
 */
public class Supplier {

    public final int id;
    private String tradeName;
    private String cnpj;

    /**
     * Construtor padrão.
     */
    public Supplier() {
        this.id = generateNextId();
    }

    /**
     * Construtor que recebe o nome fantasia e o CNPJ do fornecedor.
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
     * Contador de instâncias da classe Supplier.
     */
    private static int instanceCount = 0;

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
     * Retorna o contador de instâncias da classe Supplier.
     *
     * @return contador de instâncias.
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
     * Gera o próximo ID para o fornecedor, incrementando o contador de instâncias.
     *
     * @return próximo ID.
     */
    private static int generateNextId() {
        incrementInstanceCount();
        return instanceCount;
    }

    /**
     * Retorna uma representação textual do fornecedor.
     *
     * @return representação textual do fornecedor.
     */
    @Override
    public String toString() {
        return String.format("Supplier{id: %d, tradeName: %s, cnpj: %s}", id, tradeName, cnpj);
    }
}
