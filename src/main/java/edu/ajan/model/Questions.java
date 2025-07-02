package edu.ajan.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import edu.ajan.model.workshop.Workshop;
import edu.ajan.model.workshop.common.*;
import edu.ajan.model.workshop.date.*;
import edu.ajan.model.workshop.financial.*;
import edu.ajan.model.workshop.service.*;
import edu.ajan.model.workshop.staff.*;
import edu.ajan.model.workshop.stock.*;

/**
 * Classe que contém o método principal para executar as questões do exercício.
 * 
 * @author Alan Lima
 * @author Juan Pablo
 */
public class Questions {

    /**
     * Método principal que executa as questões do exercício.
     * @param args argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {

        Workshop.load();

        // Questão 1
        /**
         * Implementar todas as classes com base no diagrama de classes criado.
         * Atente-se para todas as relações entre as classes e a forma correta de
         * implementar seus métodos e atributos.
         */
        {
            // Feito
        }

        // Questão 2
        /**
         * O sistema será utilizado pelos colaboradores e pelo administrador.
         */
        {
            // Feito
        }

        // Questão 3
        /**
         * Sobrescrever o método toString() de todas as classes implementadas.
         */
        {
            // Feito
        }

        // Questão 4
        /**
         * Utilizar a palavra-chave super para implementar os construtores das
         * subclasses.
         */
        {
            // Feito, as classes StaffMember, Administrator, Costumer e Employee utilizam
            // super() em seus construtores, pois utilizam herança.
        }

        // Questão 5
        /**
         * O sistema deverá armazenar de forma estática (Vetor com tamanho fixo) as
         * informações dos 3 elevadores da oficina.
         */
        {
            // Na classe Scheduler, há um atributo elevators do tipo Elevator[]
        }

        // Questão 6
        /**
         * Deve ser possível cadastrar os colaboradores no sistema, alterar ou editar
         * seus atributos.
         */
        {
            // Funcionários (Employee) e Administradores (Administrator) compartilham o
            // limite superior Membro (StaffMember), essa última uma classe abstrata

            final MemberBase memberbase = Workshop.workshop().memberbase();

            StaffMember[] members = new StaffMember[] {
                    new Administrator("Juan Pablo", "(12) 3456-7890", "123.456.789-09", 3255.21, "12345678", AccessLevel.ADMIN),
                    new Administrator("Alan Lima", "(09) 8765-4321", "987.654.321-00", 3475.22, "12345678", AccessLevel.SUPER),
                    new Employee("Maria Silva", "(11) 2233-4455", "112.233.445-17", 2500.00, "12345678", AccessLevel.USER),
                    new Employee("João Souza", "(55) 6677-8899", "556.677.889-50", 2700.00, "12345678", AccessLevel.GUEST),
            };

            for (StaffMember member : members) {
                memberbase.addMember(member);
            }

            System.out.println("Membros cadastrados:");
            for (StaffMember member : memberbase.getMembers()) {
                System.out.println(member);
            }

            StaffMember updatedMember = memberbase.getMember(2);
            if (updatedMember != null) {
                updatedMember.setName("Maria Lopez");
                updatedMember.setPhone("(12) 3456-7890");
            }

            System.out.println();
            System.out.println("Membros cadastrados após edição:");
            for (StaffMember member : memberbase.getMembers()) {
                System.out.println(member);
            }
        }

        // Questão 7
        /**
         * Cadastrar, alterar ou excluir clientes.
         */
        {
            final Registry registry = Workshop.workshop().registry();

            Customer[] customers = new Customer[] {
                    new Customer("Breno Augusto", "1234567890", "98765432100", "Serro", "breno@gmail.com"),
                    new Customer("Vitor Mozer", "1234567890", "98765432100", "Diamantina", "vitor@gmail.com"),
                    new Customer("Luiz Felipe", "1234567890", "98765432100", "Serro", "luiz@yahoo.com"),
                    new Customer("Otávio Calazans", "1234567890", "98765432100", "Capelinha", "otavio@gmail.com"),
            };

            for (Customer customer : customers) {
                registry.addCustomer(customer);
            }

            System.out.println("Clientes cadastrados:");
            for (Customer customer : registry.getCustomers()) {
                System.out.println(customer);
            }

            Customer updatedMember = registry.getCustomer(2);
            if (updatedMember != null) {
                updatedMember.setName("Luiz Felipe Melo");
            }

            System.out.println();
            System.out.println("Clientes cadastrados após edição:");
            for (Customer customer : registry.getCustomers()) {
                System.out.println(customer);
            }

            registry.removeCustomer(1);

            System.out.println();
            System.out.println("Clientes cadastrados após exclusão:");
            for (Customer customer : registry.getCustomers()) {
                System.out.println(customer);
            }
        }

        // Questão 8
        /**
         * Verificar e imprimir dados das ordens de serviço de cada cliente.
         */
        {
            // Feito
        }

        // Questão 9
        /**
         * As ordens de serviço, ações do estoque e os clientes devem ser salvos de
         * forma dinâmica no sistema.
         */
        {
            // Sim. Toda unidade semântica e algumas classes de dados usam coleções da
            // java.util para armazenamento dinâmico
        }

        // Questão 10
        /**
         * Cada serviço e venda efetuados vão gerar um extrato que deverá ser impresso e
         * salvo junto com a informação do cliente que contratou o serviço da oficina.
         */
        {
            // Feito
        }

        // Questão 11
        /**
         * Criar duas variáveis de classe (static) que irão armazenar quantas instâncias
         * foram criadas dos tipos Veículo dentro da classe Sistema ou Cliente usando
         * duas soluções diferentes:
         */
        {
            // a
            /**
             * Uma delas utilizando o enfoque de encapsulamento de acordo com a engenharia
             * de software (atributo private static e métodos get e set);
             */
            {
                // Feito
            }

            // b
            /**
             * Na segunda estratégia, implementar usando o controle de acesso do tipo
             * protected;
             */
            {
                // Feito, a class Vehicle possui o contador de instâncias protegido.
            }

            // c
            /**
             * Explique quais são as vantagens e desvantagens de cada uma das duas
             * estratégias.
             */
            {
                // Estratégia com enfoque no encapsulamento: Essa estratégia é mais recomendada
                // pois ela preserva os princípios do encapsulamento contribuindo para a
                // segurança, manutenabilidade e o controle exclusivo sobre seu estado. A
                // desvantagem é que requer mais código com métodos getters e setters separados.
                // Já a estratégia com acesso do tipo protected não é recomendada pois o
                // encapsulamento é quebrado, comprometendo a segurança uma vez que qualquer
                // classe no mesmo pacote ou subclasse possa alterar diretamente o valor do
                // contador colocando por exemplo um valor negativo. O único ponto positivo é a
                // facíl implementação, porém ao custo de comprometer a qualidade do software.
            }
        }

        // Questão 12
        /**
         * Criar um método de classe para classe Sistema ou Cliente que deverá retornar
         * quantas instâncias foram criadas do tipo Veículo.
         */
        {
            System.out.printf("A quantidade de instâncias de veículos criadas foi %d\n", Vehicle.getInstanceCount());
        }

        // Questão 13
        /**
         * Implementar a interface Comparator para as classes Agendamento e Cliente e
         * fazer comparações por diferentes atributos.
         */
        {
            Customer c1 = new Customer("Otávio Calazans", "1234567890", "98765432100", "Capelinha", "otavio@gmail.com");
            Customer c2 = new Customer("Otávio Calazans", "0987654321", "12345678909", "Minas Novas", "otavio@yahoo.com");

            int nameComp = Customer.NAME_COMPARATOR.compare(c1, c2);
            int emailComp = Customer.EMAIL_COMPARATOR.compare(c1, c2);

            System.out.printf("%s e %s têm nomes %s\n", c1.getName(), c2.getName(), nameComp == 0 ? "iguais" : "diferentes");
            System.out.printf("%s e %s têm emails %s\n", c1.getName(), c2.getName(), emailComp == 0 ? "iguais" : "diferentes");

            long now = Dates.now();
            long hour = Dates.MILLISECONDS_PER_HOUR;

            ServiceOrder so1 = new ServiceOrder(1, 1, 1, 1, Status.DELIVERED, new DateSpan(now + 0 * hour, now + 2 * hour));
            ServiceOrder so2 = new ServiceOrder(1, 1, 1, 1, Status.DELIVERED, new DateSpan(now + 3 * hour, now + 4 * hour));

            int statusComp = ServiceOrder.STATUS_COMPARATOR.compare(so1, so2);
            int datetimeComp = ServiceOrder.DATETIME_COMPARATOR.compare(so1, so2);

            System.out.printf("As ordens de serviço estão %s\n", statusComp == 0 ? "no mesmo estado" : "em estados diferentes");
            System.out.printf("A primeira ordem de serviço é %s da segunda\n", datetimeComp <= 0 ? (datetimeComp != 0 ? "antes" : "no momento") : "depois");
        }

        // Questão 14
        /**
         * Salve e recupere todas as informações dos Clientes, Veículos, Agendamentos,
         * Produtos, RelatóriosdeVendas, Colaboradores, Estoque, etc... em um arquivo de
         * texto. Utilizem classes já prontas na internet que trabalhem com o formato
         * JSON. Ao manipular um arquivo utilize os conceitos aprendidos em aula para
         * alocar e desalocar recursos com segurança.
         */
        {
            // As funções Workshop.load() e Workshop.save(), respectivamente, carregam e
            // salvam os dados, se possível, tomando as medidas cabíveis em caso de erro
        }

        // Questão 15
        /**
         * Gerar um JavaDoc de todo o seu projeto.
         */
        {
            // Gerado.
        }

        // Questão 15
        /**
         * Instaciar um iterator para a arraylist de pessoas/funcionario/cliente (qual
         * estiver usando)
         * 
         * Fazer testes no main em pecorrer o arraylist com chamadas usando o código:
         * 
         * <pre>
         * while (iterator.hasnext()) {
         *     imprimir(iterator.next());
         * }
         * </pre>
         * 
         * Explicar como isso está acontecendo.
         * Qual relação do código acima com o foreach em java?
         * 
         * Testar o foreach.
         */
        {
            Iterator<Customer> iterator = Workshop.workshop().registry().getCustomers().iterator();

            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }

        // Questão 16
        /**
         * Apresentar no main testes do comparator implementado.
         * 
         * Utilizar e apresentar no main a aplicação do método sort da classe
         * collections passando o comparator criado para ordenar a lista de
         * pessoas/usuário/cliente (qual estiver usando) com dois paramêtros diferentes.
         * Ou seja, rodar duas vezes.
         */
        {
            List<Customer> customers = new ArrayList<>(Workshop.workshop().registry().getCustomers());

            Collections.sort(customers, Customer.EMAIL_COMPARATOR);
            Collections.sort(customers, Customer.NAME_COMPARATOR);

            System.out.println("Clientes após ordenação por (nome, email):");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }

        // Questão 17
        /**
         * Criar um método find para clientes utilizando o interator e comparator.
         * Apresentar testes do método iomplementado.
         * 
         * Fazer chamadas ao binarySearch() da classe collections e comparar com o find
         * IMPLEMENTADO.
         */
        {
            // Testando o método find
            Customer searchCustomer = new Customer("Luiz Felipe", "1234567890", "98765432100", "", "");
            Customer foundCustomer = Workshop.workshop().registry().findCustomer(searchCustomer, Customer.NAME_COMPARATOR);

            if (foundCustomer != null) {
                System.out.println("Cliente encontrado: " + foundCustomer);
            } else {
                System.out.println("Cliente não encontrado.");
            }

            // Testando o método binarySearch
            List<Customer> customers = new ArrayList<>(Workshop.workshop().registry().getCustomers());
            Collections.sort(customers, Customer.NAME_COMPARATOR);

            int index = Collections.binarySearch(customers, searchCustomer, Customer.NAME_COMPARATOR);
            if (index >= 0) {
                System.out.println("Cliente encontrado pelo binarySearch: " + customers.get(index));
            } else {
                System.out.println("Cliente não encontrado pelo binarySearch.");
            }
        }

        // Questão 18
        /**
         * Apresentar o funcionamento básico para o atendimento de 10 clientes da
         * oficina, desde o cadastro do cliente até a criação das ordens de serviço para
         * cada atendimento, com as baixas no estoque e denotação correta dos serciços
         * realizados, até finalizar com a emissão de nota fiscal após a coclusão das
         * vendas.
         */
        {
            Customer[] customers = new Customer[] {
                    new Customer("Breno Augusto", "1234567890", "98765432100", "Serro", "breno@gmail.com"),
                    new Customer("Vitor Mozer", "1234567890", "98765432100", "Diamantina", "vitor@gmail.com"),
                    new Customer("Luiz Felipe", "1234567890", "98765432100", "Serro", "luiz@yahoo.com"),
                    new Customer("Otávio Calazans", "1234567890", "98765432100", "Capelinha", "otávio@gmail.com"),
                    new Customer("Ana Clara", "1234567890", "98765432100", "Serro", "ana_clara@gmail.com"),
                    new Customer("Pedro Henrique", "1234567890", "98765432100", "Diamantina", "pedro@gmail.com"),
                    new Customer("Mariana Costa", "1234567890", "98765432100", "Serro", "mariana@gmail.com"),
                    new Customer("Lucas Martins", "1234567890", "98765432100", "Capelinha", ""),
                    new Customer("Fernanda Lima", "1234567890", "98765432100", "Serro", ""),
                    new Customer("Gabriel Souza", "1234567890", "98765432100", "Diamantina", ""),
            };

            Vehicle[] vehicles = new Vehicle[] {
                    new Vehicle("Fusca", "ABC1234", 2010),
                    new Vehicle("Civic", "XYZ5678", 2015),
                    new Vehicle("Onix", "LMN9012", 2018),
                    new Vehicle("Gol", "OPQ3456", 2020),
                    new Vehicle("Palio", "RST7890", 2012),
                    new Vehicle("HB20", "UVW1234", 2016),
                    new Vehicle("Corolla", "JKL5678", 2019),
                    new Vehicle("Fox", "GHI9012", 2017),
                    new Vehicle("Sandero", "DEF3456", 2021),
                    new Vehicle("Argo", "ABC7890", 2022),
            };

            for (Customer customer : customers) {
                Workshop.workshop().registry().addCustomer(customer);
            }

            for (Vehicle vehicle : vehicles) {
                Workshop.workshop().registry().addVehicle(vehicle);
            }

            Product gear = new Product("Engrenagem", 34, 2.12, "un");
            Product engine = new Product("Motor", 12, 967.33, "un");
            Workshop.workshop().stock().addProduct(gear);
            Workshop.workshop().stock().addProduct(engine);

            Service oilExchange = new Service("Troca de óleo", "", 150.54);
            Workshop.workshop().scheduler().addService(oilExchange);

            long now = Dates.now();
            long hour = Dates.MILLISECONDS_PER_HOUR;

            ServiceOrder[] orders = new ServiceOrder[10];
            for (int i = 0; i < orders.length; i++) {
                orders[i] = new ServiceOrder(customers[i].id(), vehicles[i].id(), 1, 2, new DateSpan(now + i * hour, now + (i + 1) * hour));
                Workshop.workshop().scheduler().addOrder(orders[i]);
                
                orders[i].getInvoice().addProduct(new Item(gear.id(), 2, gear.getBatch().value()));
                orders[i].getInvoice().addProduct(new Item(engine.id(), 1, engine.getBatch().value()));
                orders[i].getInvoice().addService(new Item(oilExchange.id(), 1, oilExchange.getValue()));
                
                Invoice invoice = orders[i].seal();
                System.out.println(invoice);
            }
        }

        Workshop.save();
    }
}