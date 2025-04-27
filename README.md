# Trabalho Prático

Esse é um Trabalho Prático desenvolvido como parte da disciplina de Programação Orientada a Objetos, do Curso de Sistemas de Informação, sob comando do Prof. Eduardo Pelli.

# Autores

- Alan Barbosa Lima [@AlanLima287](https://github.com/AlanLima287)
- Juan Pablo Ferreira Costa [@juan-ferreirax](https://github.com/juan-ferreirax)

# Padronização

- Tipos devem sempre ser compatíveis, logo conversões devem ser sempre explicitas, mesmo que a conversão implicita seja garantida;
- Todo if's, while's e for's deve ter um bloco delimitado por chaves associado, mesmo que seja composto por uma única sentença;
- Caso uma função tenha mais de duas sentenças significativas, this é obrigatório para referênciar variáveis de instância;
- Caso um atributo tenha mais de um getter/setter, o getter/setter padrão deve vir primeiro e imediatamente abaixo todos os outros deve ser postos;
- O bloco de getters de um atributo deve vir imediatamente antes do bloco de setters;
- Os blocos de getters e setters devem aparecer na mesma ordem que a declaração de seus atributos;
- A ordem de estruturação de uma classe deve ser:
    - atributos (constantes ou não),
    - intancia da própria classe (para singletons),
    - construtores,
    - método getInstance (para singletons),
    - enums,
    - atributos de classe (constantes ou não),
    - getters e setters,
    - outros métodos (que não são getters nem setters),
    - a substituição do metodo toString,
    - estruturas auxiliáres (classes, interfaces, exceções, etc).

# Estrutura do Projeto

- visual\
    - gui\
    - tui\
    - Strings.java
- main\
    - auth\
        - [ ] Account.java
        - [ ] Authenticator.java
    - financial\
        - [ ] Expense.java
        - [ ] Invoice.java
    - common\
        - [x] Cpf.java
        - [x] Person.java
        - [x] Phone.java
        - [x] Vehicle.java
    - customer\
        - [x] Customer.java
    - exceptions\
    - workshop\
        - personnel\
            - [ ] Employee.java
            - [x] Manager.java
            - [x] Shift.java
        - service\
            - [ ] Elevator.java
            - [ ] Maintenance.java
            - [ ] Part.java
            - [ ] Scheduler.java
            - [ ] Scheduling.java
            - [ ] Service.java
        - [ ] Workshop.java
    
## Diagrama de Classes

```mermaid
classDiagram

class Account {
    ...
}

class Authenticator {
    ...
}

class Expense {
    ...
}

class Invoice {
    ...
}

    class Cpf {
        - cpf long

        + Cpf()
        + Cpf(String)

        + getCpf() String
        + getFullCpf() String
        + setCpf(String)
    }

    Person *-- Cpf  
    Person *-- Phone
    class Person {
        - name: String
        - phone: Phone
        - cpf: Cpf

        + Person()
        + Person(String, String, String)

        + getName() String
        + setName(String)
        + getPhone() String
        + setPhone(String) 
        + getCpf() String
        + setCpf(String)
    }

    class Phone {
        - phone: long

        + Phone()
        + Phone(String)

        + getPhone() String
        + setPhone(String)
    }

    class Vehicle {
        - model: String
        - plate: String
        - year: int

        + Vehicle()
        + Vehicle(String, String, int)

        + getModel() String
        + setModel(String)
        + getPlate() String
        + setPlate(String)
        + getYear() int
        + setYear(int)
    }

    Customer --|> Person
    class Customer {
        - address: String

        + Customer()
        + Customer(String, String, String, String)

        + getAddress() String
        + setAddress(String)
    }

    Employee --|> Person
    class Employee {
        - shifts: ArrayList<Shift>
        - salary: double

        + Employee()
        + Employee(String, String, String, double)

        + getShifts() ArrayList<Shift>
        + getShifts(long, long) ArrayList<Shift>
        + getRecentShift() Shift
        + getSalary() double
        + setSalary(double)
    }

    Manager --|> Person
    class Manager {
        - proLabore: double

        + Manager()
        + Manager(String, String, String, String, double)
        
        + getProLabore() double
        + setProLabore(double)
    }

    class Shift {
        - start: long
        - end: long

        + Shift()
        + Shift(long)
        + Shift(long, long)

        + getStart() long
        + getStartDate() Date
        + setStart(long)
        + setStartNow(long)
        + getEnd() long
        + getEndDate() Date
        + setEnd(long)
        + setEndNow(long)

        + now() long $
    }

class Elevator {
    ...
}

class Maintenance {
    ...
}

class Part {
    ...
}

class Scheduler {
    ...
}

class Scheduling {
    ...
}

class Service {
    ...
}

class Workshop {
    ...
}
```

# Referências

https://docs.oracle.com/javase/tutorial/java/concepts/interface.html
https://docs.oracle.com/javase/tutorial/java/generics/types.html
https://docs.oracle.com/javase/tutorial/extra/generics/methods.html
https://github.com/AlanLima287/Binary_Tree/
https://github.com/dialex/JColor/
https://www.debuggex.com/