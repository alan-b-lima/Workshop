package edu.ajan.model.workshop.financial;

import java.util.ArrayList;

public class Financial {

    /**
     * Lista de despesas.
     */
    private ArrayList<Expense> expenses;

    /**
     * Lista de nota fiscal.
     */
    private ArrayList<Invoice> invoices;

    /**
     * Construtor privado.
     */
    public Financial() {
        expenses = new ArrayList<>();
        invoices = new ArrayList<>();
    }

    /**
     * Retorna estrutura iterável de despesas.
     * 
     * @return estrutura iterável de despesas.
     */
    public Iterable<Expense> expenses() {
        return expenses;
    }

    /**
     * Retorna estrutura iterável de nota do fiscal.
     * 
     * @return estrutura iterável de nota do fiscal.
     */
    public Iterable<Invoice> invoices() {
        return invoices;
    }

    /**
     * Retorna uma representação textual do financeiro.
     * 
     * @return representação textual do financeiro.
     */
    @Override
    public String toString() {
        return String.format("(%s %s)", expenses, invoices);
    }
}
