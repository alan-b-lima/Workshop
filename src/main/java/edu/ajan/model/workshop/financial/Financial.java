package edu.ajan.model.workshop.financial;

import java.util.ArrayList;
import java.util.Collection;

import edu.ajan.model.exception.WorkshopException;
import edu.ajan.model.workshop.Workshop;
import edu.ajan.model.workshop.date.DateSpan;
import edu.ajan.model.workshop.staff.StaffMember;
import edu.ajan.model.workshop.stock.Item;
import edu.ajan.model.workshop.stock.Product;
import edu.ajan.model.workshop.stock.Stock;

/**
 * Classe que representa a unidade financeira da oficina mecânica.
 * 
 * @author Alan Lima
 * @author Juan Pablo
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
    public Collection<Expense> getExpenses() {
        return expenses;
    }

    /**
     * Retorna estrutura iterável de despesas dentro de um espaço de tempo.
     * 
     * @param start início do espaço de tempo.
     * @param end   fim do espaço de tempo.
     * @return estrutura iterável de despesas dentro do espaço de tempo.
     */
    public Collection<Expense> getExpenses(long start, long end) {
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
        for (Expense expense : expenses) {
            if (expense.id() == expenseId) {
                return expense;
            }
        }

        return null;
    }

    /**
     * Asserta a existência de uma despesa.
     * 
     * @param expenseId identificador de despesa.
     * @return {@code true} se, e somente se a despesa existir.
     */
    public boolean hasExpense(int expenseId) {
        for (Expense expense : expenses) {
            if (expense.id() == expenseId) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adiciona uma despesa ao registro.
     * 
     * @param expense despesa a ser adicionado.
     */
    public void addExpense(Expense expense) {
        if (expense == null) {
            throw new WorkshopException("despesa não pode ser nula");
        }

        expenses.add(expense);
    }

    /**
     * Remove uma despesa do registro.
     * 
     * @param expenseId identificador do despesa a ser removido.
     */
    public void removeExpense(int expenseId) {
        expenses.removeIf(expense -> expense.id() == expenseId);
    }

    /**
     * Retorna estrutura iterável de nota do fiscal.
     * 
     * @return estrutura iterável de nota do fiscal.
     */
    public Collection<Invoice> getInvoices() {
        return invoices;
    }

    /**
     * Retorna estrutura iterável de nota fiscais dentro de um espaço de tempo.
     * 
     * @param start início do espaço de tempo.
     * @param end   fim do espaço de tempo.
     * @return estrutura iterável de nota fiscais dentro do espaço de tempo.
     */
    public Collection<Invoice> getInvoices(long start, long end) {
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
        for (Invoice invoice : invoices) {
            if (invoice.id() == invoiceId) {
                return invoice;
            }
        }

        return null;
    }

    /**
     * Asserta a existência de um nota fiscal.
     * 
     * @param invoiceId identificador de nota fiscal.
     * @return {@code true} se, e somente se a nota fiscal existir.
     */
    public boolean hasInvoice(int invoiceId) {
        for (Invoice invoice : invoices) {
            if (invoice.id() == invoiceId) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adiciona uma nota fiscal ao registro.
     * 
     * @param invoice nota fiscal a ser adicionado.
     */
    public void addInvoice(Invoice invoice) {
        if (invoice == null) {
            throw new WorkshopException("nota fiscal não pode ser nula");
        }

        invoices.add(invoice);

        Stock stock = Workshop.workshop().stock();
        for (Item product : invoice.products()) {
            Product foundProduct = stock.getProduct(product.getInfo());
            foundProduct.setBatch(foundProduct.getBatch().addQuantity(-product.getBatch().quantity()));
        }
    }

    /**
     * Remove uma nota fiscal do registro.
     * 
     * @param invoiceId identificador do nota fiscal a ser removido.
     */
    public void removeInvoice(int invoiceId) {
        invoices.removeIf(invoice -> invoice.id() == invoiceId);
    }

    public String toBalanceString(long start, long end) {

        DateSpan span = new DateSpan(start, end);
        StringBuffer sb = new StringBuffer();
        double total = 0.0;

        sb.append("Despesas:\n");
        for (Expense expense : expenses) {
            if (!span.contains(expense.getDate())) {
                continue;
            }
            
            sb.append(expense).append('\n');
            total -= expense.getValue();
        }
        
        sb.append("Notas Fiscais:\n");
        for (Invoice invoice : invoices) {
            if (!span.contains(invoice.date())) {
                continue;
            }
            
            sb.append(invoice).append('\n');
            total += invoice.subtotal();
        }
        
        sb.append("Membros:\n");
        for (StaffMember member : Workshop.workshop().memberbase().getMembers()) {
            sb.append(member).append('\n');
            total -= member.getSalary();
        }

        return sb.append(String.format("Total: %.2f\n", total)).toString();
    }

    /**
     * Retorna uma representação textual da unidade financeira.
     * 
     * @return representação textual da unidade financeira.
     */
    @Override
    public String toString() {
        return String.format("(%s %s)", expenses, invoices);
    }


}
