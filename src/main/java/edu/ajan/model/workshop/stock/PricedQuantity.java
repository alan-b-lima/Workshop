package edu.ajan.model.workshop.stock;

/**
 * Classe que representa um par quantidade-valor.
 * 
 * @author Alan Lima
 */
public final class PricedQuantity {

    /**
     * Quantidade.
     */
    private final int quantity;

    /**
     * Valor.
     */
    private final double value;

    /**
     * Construtor padrão parametrizado.
     * 
     * @param quantity quantidade a ser definida.
     * @param value    valor a ser definido.
     * 
     * @throws IllegalArgumentException se a quantidade for negativa ou o valor
     *                                  for negativo.
     */
    public PricedQuantity(int quantity, double value) {
        this.quantity = quantity;
        this.value = value;
    }

    /**
     * Retorna a quantidade.
     * 
     * @return quantidade.
     */
    public int quantity() {
        return quantity;
    }

    /**
     * Retorna o valor.
     * 
     * @return valor.
     */
    public double value() {
        return value;
    }

    /**
     * Retorna o valor total, ou seja, a quantidade vezes o valor.
     * 
     * @return valor total.
     */
    public double totalValue() {
        return value * (double) quantity;
    }

    /**
     * Contabiliza a adição de uma quantidade precificada a esta quantidade
     * precificada, de acordo com a política de contabilidade fornecida.
     * 
     * @param batch  quantidade precificada para adição.
     * @param policy política de contabilidade usada.
     * @return nova instância de quantidade precificada resultante da adição.
     */
    public PricedQuantity add(PricedQuantity batch, AccountingPolicy policy) {
        return policy.evaluate(this, batch);
    }

    /**
     * Contabiliza a adição de uma quantidade precificada a esta quantidade
     * precificada, usando a política de contabilidade padrão.
     * 
     * @param batch quantidade precificada para adição.
     * @return nova instância de quantidade precificada resultante da adição.
     */
    public PricedQuantity add(PricedQuantity batch) {
        return AccountingPolicy.DEFAULT.evaluate(this, batch);
    }

    /**
     * Define uma nova quantidade para a quantidade precificada.
     * 
     * @param quantity nova quantidade a ser definida.
     * @return nova instância de quantidade precificada com o valor atualizado.
     */
    public PricedQuantity setQuantity(int quantity) {
        return new PricedQuantity(quantity, this.value);
    }

    /**
     * Adiciona quantidade da quantidade precificada.
     * 
     * @param quantity quantidade a ser adicionada.
     * @return nova instância de quantidade precificada resultante da adição.
     */
    public PricedQuantity addQuantity(int quantity) {
        return new PricedQuantity(this.quantity + quantity, this.value);
    }

    /**
     * Subtrai quantidade da quantidade precificada.
     * 
     * @param quantity quantidade a ser subtraída.
     * @return nova instância de quantidade precificada resultante da subtração.
     */
    public PricedQuantity subQuantity(int quantity) {
        return new PricedQuantity(this.quantity - quantity, this.value);
    }

    /**
     * Define um novo valor para a quantidade precificada.
     * 
     * @param value novo valor a ser definido.
     * @return nova instância de quantidade precificada com o valor atualizado.
     */
    public PricedQuantity setValue(double value) {
        return new PricedQuantity(this.quantity, value);
    }

    /**
     * Clona a quantidade precificada.
     * 
     * @return uma nova instância de quantidade precificada com os mesmos valores.
     */
    @Override
    public PricedQuantity clone() {
        return new PricedQuantity(this.quantity, this.value);
    }

    /**
     * Retorna uma representação textual do par quantidade-valor.
     * 
     * @return representação textual do par quantidade-valor.
     */
    @Override
    public String toString() {
        return String.format("(%d %.2f)", quantity, value);
    }
}
