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
    public static final AccountingPolicy WEIGHTED_AVERAGE = (PricedQuantity batch0, PricedQuantity batch1) -> {
        double weighted_sum = batch0.totalValue() + batch1.totalValue();
        int total = batch0.quantity() + batch1.quantity();

        return new PricedQuantity(total, weighted_sum / (double) total);
    };

    /**
     * Devolve o maior valor entre os dois fornecidos.
     */
    public static final AccountingPolicy MAX_VALUE_BETWEEN = (PricedQuantity batch0, PricedQuantity batch1) -> {
        return new PricedQuantity(batch0.quantity() + batch1.quantity(), Math.max(batch0.value(), batch1.value()));
    };

    /**
     * Política de contabilidade padrão usada para contabilizar
     * quantidades precificadas.
     */
    public static final AccountingPolicy DEFAULT = WEIGHTED_AVERAGE;

    /**
     * Retorna, seguindo alguma métrica arbitrária, o valor da combinação das
     * quantidades precificadas.
     * 
     * @param batch0 primeira quantidade precificada.
     * @param batch1 segunda quantidade precificada.
     * @return combinação das quantidades precificadas.
     */
    PricedQuantity evaluate(PricedQuantity batch0, PricedQuantity batch1);

}
