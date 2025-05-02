# Trabalho Prático

Esse é um Trabalho Prático desenvolvido como parte da disciplina de Programação Orientada a Objetos, do Curso de Sistemas de Informação, sob comando do Prof. Eduardo Pelli.

# Autores

- Alan Barbosa Lima [@alan-b-lima](https://github.com/alan-b-lima)
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

- json\
    - [ ] Json.java
- main\
    - auth\
        - [ ] Account.java
        - [ ] Authenticator.java
    - financial\
        - [ ] IncomeExpenseManeger.java
        - [x] Expense.java
        - [ ] Invoice.java
        - [ ] Shipment.java
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
            - [x] Employee.java
            - [x] Manager.java
            - [x] Shift.java
        - service\
            - [ ] Elevator.java
            - [ ] Maintenance.java
            - [ ] Scheduler.java
            - [ ] Scheduling.java
            - [ ] Service.java
        - stock\
            - [ ] Stock.java
            - [ ] Part.java
        - [ ] Workshop.java
    
## Diagrama de Classes

```mermaid
classDiagram

    class Expense {
        - name: String
        - description: String
        - value: double
        - date: long

        + Expense()
        + Expense(String, String, double, long)

        + getName() String
        + setName(String)
        + getDescription() String
        + setDescription(String)
        + getValue() double
        + setValue(double)
        + getDate() long
        + setDate(long)
    }

    class Invoice {
        + id: long
        - parts: Part[]
        - services: Service[]
        - value: double
        - date: long

        + Invoice()

        ...
    }

    Shipment *-- Part
    class Shipment {
        + id: long
        - parts: ArrayList<Part>
        - arrival: long

        + Shipment()
        
        + getParts() ArrayList<Part>
        + getPart(String) Part
        + addPart(Part)
        + addPart(Part, int)
        + getArrival() long
        + setArrival(long)
        + setArrivalNow()
    }

    class Cpf {
        - cpf: long

        + Cpf()
        + Cpf(String)

        + getCpf() String
        + getFullCpf() String
        + setCpf(String)
    }

    Person *-- Cpf  
    Person *-- Phone
    class Person["_Person_"] {
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

    Employee *-- Shift
    Employee --|> Person
    class Employee {
        - shifts: ArrayList<Shift>
        - salary: double

        + Employee()
        + Employee(String, String, String, double)

        + getShifts() ArrayList<Shift>
        + getShifts(long, long) ArrayList<Shift>
        + getRecentShift() Shift
        + addShift(Shift)
        + getSalary() double
        + setSalary(double)
    }

    Manager --|> Person
    class Manager {
        - proLabore: double

        + Manager()
        + Manager(String, String, String, double)
    
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
        + setStart(long)
        + setStartNow(long)
        + getEnd() long
        + setEnd(long)
        + setEndNow(long)
    }

    class Elevator {
        - activities: ArrayList<String>

        + elevators: Elevator[] $

        + Elevator()

        + getActivities() ArrayList<String>
        + addActivity(String)
        + removeActivity(String)
    }

    class Maintenance {
        ...
    }

    class Scheduler {
        ...
    }

    class Scheduling {
        customer: long
    }

    class Service {
        ...
    }

    Stock *-- Part
    Stock *-- Shipment
    class Stock {
        - parts: HashMap<String, Part>
        - shipments: ArrayList<Shipment>

        + stock: Stock $

        + Stock()

        + getPartQuantity(String) int
        + addPartQuantity(String) int
        + removePartQuantity(String, int)
        + getPartUnitValue(String) double
        + getPartTotalValue(String) double
        + setPartUnitValue(String) double
        + getShipment(Shipment)
        + addShipment(Shipment)
    }

    class Part {
        - name: String
        - unitValue: double
        - quantity: int

        + Part()
        + Part(String, double, int)

        + getName() String
        + setName(String)
        + getUnitValue() double
        + getTotalValue() double
        + setUnitValue(double)
        + setTotalValue(double)
        + getQuantity() int
        + setQuantity(int)
    }
```

# Referências

https://docs.oracle.com/javase/tutorial/java/concepts/interface.html
https://docs.oracle.com/javase/tutorial/java/generics/types.html
https://docs.oracle.com/javase/tutorial/extra/generics/methods.html
https://github.com/AlanLima287/Binary_Tree/
https://github.com/dialex/JColor/
https://www.debuggex.com/
https://github.com/google/gson