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

- main\
    - json\
        - [x] Json.java
    - util\
        - [ ] WDate.java
    - workshop\
        - auth\
            - [ ] Account.java
            - [ ] Session.java
        - financial\
            - [ ] IncomeExpenseManager.java
            - [x] Expense.java
            - [ ] Invoice.java
        - common\
            - [x] Cpf.java
            - [x] DateSpan.java
            - [x] Person.java
            - [x] Phone.java
        - customer\
            - [x] Customer.java
            - [x] Vehicle.java
        - exceptions\
            - [x] WorkshopException.java
        - personnel\
            - [x] Employee.java
            - [x] Manager.java
        - service\
            - [x] Elevator.java
            - [ ] Maintenance.java
            - [ ] Scheduler.java
            - [ ] Scheduling.java
            - [ ] Service.java
        - stock\
            - [x] Shipment.java
            - [ ] Stock.java
            - [x] Part.java
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
        + setStart(long)
        + setStartNow(long)
        + getEnd() long
        + setEnd(long)
        + setEndNow(long)   
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

    Employee *-- DateSpan
    Employee --|> Person
    class Employee {
        - shifts: ArrayList<DateSpan>
        - salary: double

        + Employee()
        + Employee(String, String, String, double)

        + getShifts() ArrayList<DateSpan>
        + getShifts(long, long) ArrayList<DateSpan>
        + getRecentShift() DateSpan
        + addShift(DateSpan)
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

    class Elevator {
        TreeSet<DateSpan> shifts

        + Elevator()

        + get() DateSpan[]
        + get(long, long) DateSpan[]
        + set(DateSpan)
        + set(long, long)
    }

    Maintenance --|> Scheduling
    class Maintenance {
        - status: byte
        - mechanic: Employee
        - parts: HashMap<String, Part>
        - services: ArrayList<Service>

        + Maintenance()
        + Maintenance(long, Vehicle, byte, long, byte, Employee)

        + getStatus() String
        + getStatusCode() byte
        + setStatus(byte)
        + getMechanic() Employee
        + setMechanic(Employee)
        + getParts() Part[]
        + getParts(Part) Part
        + getPartQuantity(String) int
        + getPartUnitValue(String) double
        + getPartTotalValue(String) double
        + getPart(String) Part
        + addPart(String, int)
        + getServices() Service[]
        + addService(Service)
        + getServiceValue() double
    }

    class Scheduler {
        - elevator: Elevator[]
        - schedule: ArrayList<Scheduling>
        - maxWeight: double
    }

    class Scheduling {
        - customer: long
        - vehicle: Vehicle
        - elevador: byte
        - date: DataSpan

        + Scheduling()
        + Scheduling(long, Vehicle, byte, long)

        + getCustomer() long
        + setCustomer(long)
        + getVehicle() Vehicle
        + setVehicle(Vehicle)
        + getElevator() byte
        + setElevator(byte)
        + getDate() long
        + setDate(long)
        + toMaintenance() Maintenance
    }

    class Service {
        - name: String
        - done: boolean
        - value: double

        + Service()
        + Service(String, boolean, double)

        + getName() String
        + setName(String)
        + getDone() boolean
        + setDone(boolean)
        + getValue() double
        + setValue(double)
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

    Stock *-- Part
    Stock *-- Shipment
    class Stock {
        - parts: HashMap<String, Part>
        - shipments: ArrayList<Shipment>

        + stock: Stock $

        - Stock()

        + getPartQuantity(String) int
        + addPartQuantity(String, int) int
        + removePartQuantity(String, int)
        + getPartUnitValue(String) double
        + getPartTotalValue(String) double
        + setPartUnitValue(String)
        + setPartTotalValue(String)
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