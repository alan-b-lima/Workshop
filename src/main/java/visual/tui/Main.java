package visual.tui;

import main.util.WDate;
import main.workshop.Workshop;
import main.workshop.common.Cpf;
import main.workshop.common.DateSpan;
import main.workshop.customer.Customer;
import main.workshop.customer.Vehicle;
import main.workshop.personnel.Employee;
import main.workshop.personnel.Manager;
import main.workshop.financial.Expense;

import main.workshop.exception.WorkshopException;

public class Main {

    public static void main(String[] args) {

        {
            System.out.println("Testando CPF...");
            try {
                Cpf cpf = new Cpf("987.654.321-00");
                System.out.println("CPF anonimizado: " + cpf.getCpf());
                System.out.println("CPF completo: " + cpf.getFullCpf());
            } catch (WorkshopException err) {
                // error
            }
            System.out.println();

            try {
                Cpf cpf = new Cpf("987.654.321-01");
                System.out.println("CPF anonimizado: " + cpf.getCpf());
                System.out.println("CPF completo: " + cpf.getFullCpf());
            } catch (WorkshopException err) {
                System.out.println("Esse CPF é inválido");
            }
        }
        System.out.println();

        {
            System.out.println("Testando Cliente...");
            try {
                Customer c1 = new Customer("John Doe", "38999999999", "987.654.321-00", "Diamantina",
                        "John@gmail.com");
                System.out.println(c1);
            } catch (WorkshopException err) {
                // error
            }
        }
        System.out.println();

        {
            System.out.println("Testando Gerente...");
            try {
                Manager m1 = new Manager("John Doe", "38999999999", "987.654.321-00", 2500.00, "password123");
                System.out.println(m1);
            } catch (WorkshopException err) {
                // error
            }
        }
        System.out.println();

        {
            System.out.println("Testando Veículo...");
            try {
                Vehicle v1 = new Vehicle("BMW", "ABC1234", 2024);
                System.out.println(v1);
            } catch (WorkshopException err) {
                System.out.println(err);
            }
        }
        System.out.println();

        {
            System.out.println("Testando Despesa...");
            Expense e1 = new Expense("Café", "Café para os funcionários", 600.00, 245984465);
            System.out.println(e1);
        }
        System.out.println();

        {
            System.out.println("Testando Funcionário...");
            try {
                DateSpan shift = new DateSpan(WDate.parse("04/05/2025 18:55"), WDate.parse("04/05/2025 23:59"));
                System.out.println("Turno criado: " + shift);
            } catch (WorkshopException err) {
                System.out.println(err);
            }
        }
        System.out.println();

        Workshop workshop = Workshop.getInstance();

        try {
            // Configurações iniciais para teste
            workshop.addCustomers(
                    new Customer("John Doe", "38999999999", "987.654.321-00", "Diamantina", "john@gmail.com"));
            workshop.addVehicles(new Vehicle("BMW", "ABC1234", 2024));
            workshop.addEmployees(
                    new Employee("Jane Doe", "38999999999", "987.654.321-00", 2000.0, "securePassword123"));
            workshop.setManager(new Manager("Rafael", "38999999999", "987.654.321-00", 5000.0, "password123"));

            // Salva o estado
            workshop.saveState();
            System.out.println("Estado salvo com sucesso!");

            // Carrega o estado
            workshop.loadState();
            System.out.println("Estado carregado com sucesso!");

            // Verifica os dados carregados
            System.out.println("Clientes: " + workshop.getCustomers());
            System.out.println("Veículos: " + workshop.getVehicles());
            System.out.println("Funcionários: " + workshop.getEmployees());
            System.out.println("Gerente: " + workshop.getManager());

        } catch (WorkshopException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}