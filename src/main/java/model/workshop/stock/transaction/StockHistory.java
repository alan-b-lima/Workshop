package model.workshop.stock.transaction;

import java.util.Stack;

import model.exception.WorkshopException;

/**
 * Classe que representa o histórico de transações de estoque.
 * 
 * @author Alan Lima
 */
public class StockHistory {

    /**
     * Lista ligada que armazena as transações de estoque.
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
     * Retorna o tamanho do histórico de transações.
     * 
     * @return o tamanho do histórico.
     */
    public Transaction get(int index) {
        try {
            return history.get(index);
        } catch (IndexOutOfBoundsException err) {
            throw new WorkshopException("indíce [%d] fora dos limites para tamanho %d", index, history.size());
        }
    }

    /**
     * Adiciona uma nova transação ao histórico.
     * 
     * @param transaction a transação a ser adicionada.
     */
    public void append(Transaction transaction) {
        clearEnd(index);

        history.add(transaction);
        index++;
    }

    /**
     * Desfaz a última transação, se possível.
     * 
     * @return a transação desfeita.
     * @throws WorkshopException se não houver transações para desfazer.
     */
    public Transaction undo() {
        if (index <= 0) {
            throw new WorkshopException("não há transações para reverter");
        }

        index--;
        return history.get(index);
    }

    /**
     * Refaz a última transação desfeita, se possível.
     * 
     * @return a transação refeita.
     * @throws WorkshopException se não houver transações para refazer.
     */
    public Transaction redo() {
        if (index >= history.size()) {
            throw new WorkshopException("não há transações para refazer");
        }

        Transaction transaction = history.get(index);
        index++;
        return transaction;
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
}
