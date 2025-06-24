package edu.ajan.model.workshop.stock;

/**
 * Interface funcional que define uma modelo que contablidade de valores no
 * estoque.
 * 
 * @author Alan Lima
 */
@FunctionalInterface
public interface AccountingPolicy {

    /**
     * Devolve a média ponderada dos valores.
     */
    public static final AccountingPolicy WEIGHTED_AVERAGE = (int quantity1, double value1, int quantity2, double value2) -> {
        return ((double) quantity1 * value1 + (double) quantity2 * value2) / (double) (quantity1 + quantity2);
    };

    /**
     * Devolve o maior valor entre os dois fornecidos.
     */
    public static final AccountingPolicy MAX_VALUE_BETWEEN = (int _, double value1, int _, double value2) -> {
        return Math.max(value1, value2);
    };

    /**
     * Retorna, seguindo alguma métrica arbitrária, o valor da combinação das
     * peças.
     * 
     * @param quantity1 quantidade da primeira peça.
     * @param value1    valor da da primeira peça.
     * @param quantity2 quantidade da segunda peça.
     * @param value2    valor da segunda peça.
     * @return valor obtido pela aplicação de alguma métrica.
     */
    double determine(int quantity1, double value1, int quantity2, double value2);

}
