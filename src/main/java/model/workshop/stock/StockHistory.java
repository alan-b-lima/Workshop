package model.workshop.stock;

import java.util.Stack;

import exception.WorkshopException;
import model.workshop.stock.Stock.Transaction;

/**
 * Classe que representa o histórico de transações de estoque.
 * 
 * @author Alan Lima
 */
public class StockHistory {

    /**
     * Coleção que armazena as transações de estoque.
     */
    private Stack<Transaction> history;

    /**
     * Índice atual do histórico, usado para controlar o estado de desfazer/refazer.
     */
    private int index;

    /**
     * Instância única do StockHistory (Singleton).
     */
    private static StockHistory instance = new StockHistory();

    /**
     * Construtor privado para garantir que a classe seja um Singleton.
     */
    private StockHistory() {
        history = new Stack<>();
        index = 0;
    }

    /**
     * Retorna a instância única do StockHistory.
     * 
     * @return a instância única do StockHistory.
     */
    public static StockHistory history() {
        return instance;
    }

    /**
     * Registra uma transação do estado atual do estoque e adiciona ao histórico.
     */
    public void record() {
        append(Stock.stock().createTransaction());
    }

    /**
     * Aplica uma transação ao estoque, restaurando o estado do estoque para o
     * estado da transação.
     * 
     * @param transaction a transação a ser aplicada.
     */
    private void apply(Transaction transaction) {
        Stock.stock().restoreTransaction(transaction);
    }

    /**
     * Adiciona uma nova transação ao histórico.
     * 
     * @param transaction a transação a ser adicionada.
     */
    private void append(Transaction transaction) {
        clearEnd(index);

        history.add(transaction);
        index++;
    }

    /**
     * Reverte para a última transação, se possível.
     * 
     * @throws WorkshopException se não houver transações para reverter.
     */
    public void undo() {
        if (index <= 0) {
            throw new WorkshopException("não há transações para reverter");
        }

        apply(history.get(--index));
    }

    /**
     * Refaz a última transação desfeita, se possível.
     * 
     * @throws WorkshopException se não houver transações para refazer.
     */
    public void redo() {
        if (index >= history.size()) {
            throw new WorkshopException("não há transações para refazer");
        }

        apply(history.get(index++));
    }

    /**
     * Limpa o histórico de transações a partir de um índice específico.
     * 
     * @param index índice a partir do qual as transações serão removidas.
     */
    private void clearEnd(int index) {
        for (int i = history.size() - 1; i >= index; i--) {
            history.remove(i);
        }
    }

    /**
     * Retorna uma representação textual do histórico de transações.
     * 
     * @return representação textual do histórico de transações.
     */
    @Override
    public String toString() {
        return String.format("(%s, %d)", history, index);
    }
}
