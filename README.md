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

- model\
    - json\
        - [ ] Json.java
    - util\
        - [ ] WDate.java
    - workshop\
        - auth\
            - [ ] Account.java
            - [ ] Session.java
        - financial\
            - [ ] IncomeExpenseManager.java
            - [ ] Expense.java
            - [ ] Invoice.java
        - common\
            - [ ] Cpf.java
            - [ ] DateSpan.java
            - [ ] Person.java
            - [ ] Phone.java
        - customer\
            - [ ] Customer.java
            - [ ] Vehicle.java
        - exceptions\
            - [ ] WorkshopException.java
        - personnel\
            - [ ] Employee.java
            - [ ] Manager.java
        - service\
            - [ ] Elevator.java
            - [ ] Maintenance.java
            - [ ] Scheduler.java
            - [ ] Scheduling.java
            - [ ] Service.java
        - stock\
            - [ ] Shipment.java
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

    class Cpf {
        - cpf: long

        + Cpf()
        + Cpf(String)

        + getCpf() String
        + getFullCpf() String
        + setCpf(String)
    }

    class DateSpan {
        - start: long
        - end: long

        + DateSpan()
        + DateSpan(long)
        + DateSpan(long, long)

        + getStart() long
        + setStart(long) void
        + setStartNow() void
        + getEnd() long
        + setEnd(long) void
        + setEndNow() void
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
        - shifts: ArrayList<DateSpan>
        - salary: double

        + Employee()
        + Employee(String, String, String, double)

        + getShifts() ArrayList<Shift>
        + getShifts(long, long) ArrayList<Shift>
        + getRecentShift() Shift
        + addShift(Shift) void
        + getSalary() double
        + setSalary(double) void
    }

    Manager --|> Person
    class Manager {
        - proLabore: double

        + Manager()
        + Manager(String, String, String, double)
    
        + getProLabore() double
        + setProLabore(double)
    }

    class Elevator {
        + Elevator()
    }

    class Maintenance {
        ...
    }

    class Scheduler {
        
    }

    class Scheduling {
        + id: long
        - customer: long
        - vehicle: long
        - elevador: byte
        - date: long

        + Scheduling()
        + Scheduling(Customer, Vehicle, byte, long)

        - instanceCount: long $
        - nextId() long $

        + getCustomer() long
        + setCustomer(long) void
        + getVehicle() Vehicle
        + setVehicle(Vehicle) void
        + getElevador() byte
        + setElevador(byte) void
        + getDate() long
        + setDate(long) void
    }

    class Service {
        + id: long
        - name: String
        - description: String
        - value: double

        + Service()
        + Service(String, String, double)

        + instanceCount: long $
        + nextId() long $

        + getName() String
        + setName(String) void
        + getDescription() String
        + setDescription(String) void
        + getValue() double
        + setValue(double) void
    }

    Shipment *-- Part
    class Shipment {
        + id: long
        - parts: ArrayList<Part>
        - arrival: long

        + Shipment()

        + instanceCount: long $
        + nextId() long $
        
        + getParts() ArrayList<Part>
        + getPart(String) Part
        + addPart(Part)
        + addPart(Part, int) void
        + getArrival() long
        + setArrival(long) void
        + setArrivalNow() void
    }

    Stock *-- Part
    Stock *-- Shipment
    class Stock {
        - parts: HashMap<long, Part>
        - shipments: ArrayList<Shipment>

        + Stock()

        + getPartQuantity(PartKind) int
        + addPartQuantity(PartKind, int)
        + removePartQuantity(PartKind, int)
        + getPartUnitValue(PartKind) double
        + getPartTotalValue(PartKind) double
        + setPartUnitValue(PartKind) void
        + setPartTotalValue(PartKind) void
        + getShipment(Shipment) void
        + addShipment(Shipment) void
    }

    class Part {
        - partKind: long
        - unitValue: double
        - quantity: int

        + Part()
        + Part(PartKind, double, int)

        + getPartKind() PartKind
        + setPartKind(PartKind) void
        + getUnitValue() double
        + getTotalValue() double
        + setUnitValue(double) void
        + setTotalValue(double) void
        + getQuantity() int
        + setQuantity(int) void
    }

    class PartKind {
        + id: long
        - name: String
        - description: String

        + PartKind()
        + PartKind(String, String)

        + instanceCount: long $
        + nextId() long $

        + getName() String
        + setName(String) void
        + getDescription() String
        + setDescription(String) void
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