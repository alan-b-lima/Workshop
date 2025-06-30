package edu.ajan.model;
import edu.ajan.model.auth.AccessLevel;
import edu.ajan.model.persistence.Caretaker;
import edu.ajan.model.workshop.Workshop;
import edu.ajan.model.workshop.staff.Administrator;
import edu.ajan.model.workshop.staff.Employee;
import edu.ajan.model.workshop.staff.StaffMember;

public class Questions {

    public static void main(String[] args) {

        Caretaker.load();
        Workshop.load();

        // Questão 1
        /**
         * Implementar todas as classes com base no diagrama de classes criado.
         * Atente-se para todas as relações entre as classes e a forma correta de
         * implementar seus métodos e atributos.
         */
        {
            // Sim
        }

        // Questão 2
        /**
         * O sistema será utilizado pelos colaboradores e pelo administrador.
         */
        {
            // Sim
        }

        // Questão 3
        /**
         * Sobrescrever o método toString() de todas as classes implementadas.
         */
        {
            // Sim
        }

        // Questão 4
        /**
         * Utilizar a palavra-chave super para implementar os construtores das
         * subclasses.
         */
        {
            // Sim, as classes StaffMember, Administrator, Costumer e Employee utilizam
            // super() em seus construtores pois utiliza herança.
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

            StaffMember[] newMembers = new StaffMember[] {
                    new Administrator("Juan Pablo", "1234567890", "12345678909", 3255.21, "12345678", AccessLevel.ADMIN),
                    new Administrator("Alan Lima", "0987654321", "98765432100", 3475.22, "12345678", AccessLevel.SUPER),
                    new Employee("Maria Silva", "1122334455", "11223344517", 2500.00, "12345678", AccessLevel.USER),
                    new Employee("João Souza", "5566778899", "55667788950", 2700.00, "12345678", AccessLevel.USER)
            };

            for (StaffMember member : newMembers) {
                Workshop.workshop().memberbase().addMember(member);
            }

            System.out.println("Membros cadastrados:");
            Workshop.workshop().memberbase().getMembers().forEach(System.out::println);

            // Exemplo de alteração de um membro
            StaffMember memberToUpdate = Workshop.workshop().memberbase().getMember(2);
            if (memberToUpdate != null) {
                memberToUpdate.setName("Maria Oliveira");
                memberToUpdate.setSalary(2800.00);
                System.out.println("Membro atualizado: " + memberToUpdate);
            }
        }

        // Questão 7
        /**
         * Cadastrar, alterar ou excluir clientes.
         */
        {

        }

        // Questão 8
        /**
         * Verificar e imprimir dados das ordens de serviço de cada cliente.
         */
        {

        }

        // Questão 9
        /**
         * As ordens de serviço, ações do estoque e os clientes devem ser salvos de
         * forma dinâmica no sistema.
         */
        {
            // Sim. Toda unidade semântica e algumas classes de dados usam coleções da
            // java.util para armazenamento dinâmico.
        }

        // Questão 10
        /**
         * Cada serviço e venda efetuados vão gerar um extrato que deverá ser impresso e
         * salvo junto com a informação do cliente que contratou o serviço da oficina.
         */
        {

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

            }

            // b
            /**
             * Na segunda estratégia, implementar usando o controle de acesso do tipo
             * protected;
             */
            {

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

        }

        // Questão 13
        /**
         * Implementar a interface Comparator para as classes Agendamento e Cliente e
         * fazer comparações por diferentes atributos.
         */
        {

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

        }
    }
}
