package visual.tui;

import main.util.WDate;
import main.workshop.common.Cpf;
import main.workshop.common.DateSpan;
import main.workshop.customer.Customer;
import main.workshop.customer.Vehicle;
import main.workshop.personnel.Manager;
import main.workshop.financial.Expense;

import main.workshop.exception.WorkshopException;

public class Main {

    public static void main(String[] args) {

        int a = 5;

        switch (a) {
            case 1: {

                System.out.println("Testando CPF...");
                try {
                    Cpf cpf = new Cpf("987.654.321-00");
                    System.out.println("CPF anonimizado: " + cpf.getCpf());
                    System.out.println("CPF completo: " + cpf.getFullCpf());
                } catch (WorkshopException err) {
                    // error
                }

                try {
                    Cpf cpf = new Cpf("987.654.321-01");
                    System.out.println("CPF anonimizado: " + cpf.getCpf());
                    System.out.println("CPF completo: " + cpf.getFullCpf());
                } catch (WorkshopException err) {
                    System.out.println("Esse cpf é inválido");
                }

                break;
            }

            case 2: {

                System.out.println("Testando Cliente... ");
                {
                    try {
                        Customer c1 = new Customer("John Doe", "38999999999", "987.654.321-00",
                                "Diamantina", "John@gmail.com");
                        System.out.println(c1);
                    } catch (WorkshopException err) {

                    }
                }

                break;
            }

            case 3: {

                System.out.println("Testando Gerente... ");
                {
                    try {
                        Manager m1 = new Manager("John Doe", "38999999999", "987.654.321-00",
                                2500.00, "password123");
                        System.out.println(m1);

                    } catch (WorkshopException err) {

                    }
                }

                break;
            }

            case 4: {

                System.out.println("Testando Vehicle... ");
                {
                    try {
                        Vehicle v1 = new Vehicle("BMW", "ABC1234", 2024);
                        System.out.println(v1);
                    } catch (WorkshopException err) {
                        System.out.println(err);
                    }
                }

                break;
            }

            case 5: {

                System.out.println("Testando Despesa... ");
                {
                    Expense e1 = new Expense("Café", "Café para os funcionários", 600.00, 245984465);
                    System.out.println(e1);
                }

                break;
            }

            case 6: {

                System.out.println("Testando Funcionário... ");
                DateSpan shift = new DateSpan(WDate.parse("04/05/2025 18:55"), WDate.parse("04/05/2025 23:59"));
                
            }
        }
    }
    // Implementar todas as classes com base no diagrama de classes criado.
    // Atente-se para todas as relações entre as classes e a forma correta de
    // implementar seus métodos e atributos;

    // O sistema será utilizado pelos colaboradores e pelo administrador

    // Sobrescrever o método toString() de todas as classes implementadas;

    // Utilizar a palavra-chave super para implementar os construtores das
    // subclasses.

    // O sistema deverá armazenar de forma estática (Vetor com tamanho fixo) as
    // informações dos 3 elevadores da oficina.

    // Deve ser possível cadastrar os colaboradores no sistema, alterar ou editar
    // seus atributos;

    // Cadastrar, alterar ou excluir clientes;

    // Verificar e imprimir dados das ordens de serviço de cada cliente;

    // As ordens de serviço, ações do estoque e os clientes devem ser salvos de
    // forma dinâmica no sistema.

    // Cada serviço e venda efetuados vão gerar um extrato que deverá ser impresso e
    // salvo junto com a informação do cliente que contratou o serviço da oficina.

    // Criar duas variáveis de classe (static) que irão armazenar quantas instâncias
    // foram criadas dos tipos Veículo dentro da classe Sistema ou Cliente usando
    // duas soluções diferentes:

    // Uma delas utilizando o enfoque de encapsulamento de acordo com a engenharia
    // de software (atributo private static e métodos get e set);

    // Na segunda estratégia, implementar usando o controle de acesso do tipo
    // protect;

    // Explique quais são as vantagens e desvantagens de cada uma das duas
    // estratégias.

    // Criar um método de classe para classe Sistema ou Cliente que deverá retornar
    // quantas instâncias foram criadas do tipo Veículo;

    // Implementar a interface Comparator para as classes Agendamento e Cliente e
    // fazer comparações por diferentes atributos.

    // Salve e recupere todas as informações dos Clientes, Veículos, Agendamentos,
    // Produtos, RelatóriosdeVendas, Colaboradores, Estoque, etc… em um arquivo de
    // texto. Utilizem classes já prontas na internet que trabalhem com o formato
    // json. Ao manipular um arquivo utilize os conceitos aprendidos em aula para
    // alocar e desalocar recursos com segurança.

    // Gerar um JavaDoc de todo o seu projeto.
}
