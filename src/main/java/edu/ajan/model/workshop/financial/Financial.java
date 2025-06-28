package edu.ajan.model.workshop.financial;

import java.util.ArrayList;

import edu.ajan.model.exception.WorkshopException;
import edu.ajan.model.workshop.date.DateSpan;

/**
 * Classe que representa a unidade financeira da oficina mecânica.
 * 
 * @author Alan Lima
 */
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
    public Iterable<Expense> getExpenses() {
        return expenses;
    }

    /**
     * Retorna estrutura iterável de despesas dentro de um espaço de tempo.
     * 
     * @param start início do espaço de tempo.
     * @param end   fim do espaço de tempo.
     * @return estrutura iterável de despesas dentro do espaço de tempo.
     */
    public Iterable<Expense> getExpenses(long start, long end) {
        DateSpan span = new DateSpan(start, end);

        return expenses.stream()
                .filter(expense -> span.contains(expense.getDate()))
                .toList();
    }

    /**
     * Retorna uma despesa a partir do seu identificador.
     * 
     * @param expenseId identificador de despesa.
     * @return despesa de identificador passado, ou {@code null} se essa despesa não
     *         existir.
     */
    public Expense getExpense(int expenseId) {
        return expenses.get(expenseId);
    }

    /**
     * Asserta a existência de uma despesa.
     * 
     * @param expenseId identificador de despesa.
     * @return {@code true} se, e somente se a despesa existir.
     */
    public boolean hasExpense(int expenseId) {
        throw WorkshopException.methodNotImplemented("hasExpense");
    }

    /**
     * Adiciona uma despesa ao registro.
     * 
     * @param expense despesa a ser adicionado.
     */
    public void addExpense(Expense expense) {
        throw WorkshopException.methodNotImplemented("addExpense");
    }

    /**
     * Remove uma despesa do registro.
     * 
     * @param expenseId identificador do despesa a ser removido.
     */
    public void removeExpense(int expenseId) {
        expenses.remove(expenseId);
    }

    /**
     * Retorna estrutura iterável de nota do fiscal.
     * 
     * @return estrutura iterável de nota do fiscal.
     */
    public Iterable<Invoice> getInvoices() {
        return invoices;
    }

    /**
     * Retorna estrutura iterável de nota fiscais dentro de um espaço de tempo.
     * 
     * @param start início do espaço de tempo.
     * @param end   fim do espaço de tempo.
     * @return estrutura iterável de nota fiscais dentro do espaço de tempo.
     */
    public Iterable<Invoice> getInvoices(long start, long end) {
        DateSpan span = new DateSpan(start, end);

        return invoices.stream()
                .filter(invoice -> span.contains(invoice.date()))
                .toList();
    }

    /**
     * Retorna uma nota fiscal a partir do seu identificador.
     * 
     * @param invoiceId identificador de nota fiscal.
     * @return nota fiscal de identificador passado, ou {@code null} se essa nota
     *         fiscal não existir.
     */
    public Invoice getInvoice(int invoiceId) {
        return invoices.get(invoiceId);
    }

    /**
     * Asserta a existência de um nota fiscal.
     * 
     * @param invoiceId identificador de nota fiscal.
     * @return {@code true} se, e somente se a nota fiscal existir.
     */
    public boolean hasInvoice(int invoiceId) {
        throw WorkshopException.methodNotImplemented("hasInvoice");
    }

    /**
     * Adiciona uma nota fiscal ao registro.
     * 
     * @param invoice nota fiscal a ser adicionado.
     */
    public void addInvoice(Invoice invoice) {
        throw WorkshopException.methodNotImplemented("addInvoice");
    }

    /**
     * Remove uma nota fiscal do registro.
     * 
     * @param invoiceId identificador do nota fiscal a ser removido.
     */
    public void removeInvoice(int invoiceId) {
        invoices.remove(invoiceId);
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
