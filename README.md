# Sistema de Gerenciamento para uma Oficina Mecânica

Esse é um sistema de gerenciamento para uma oficina mecânica, que abrange diversas funcionalidades como agendamento de serviços, controle de estoque, gestão financeira e cadastro de clientes e veículos e gerenciamento de estoque.

## Objetivo Geral

Aplicar os conceitos de Programação Orientada a Objetos (POO) para desenvolver um sistema de gerenciamento de uma oficina mecânica, utilizando Java como linguagem de programação.

## Autores

- Alan Barbosa Lima         [@alan-b-lima](https://github.com/alan-b-lima)
- Juan Pablo Ferreira Costa [@juan-ferreirax](https://github.com/juan-ferreirax)

## Estrutura do Projeto

O projeto é estruturado em duas partes principais: **Modelo** (Backend) e **View** (Frontend).

### Padronização de Código

- Conversões devem ser sempre explicitas, mesmo que a conversão implicita seja garantida;
- Todo `if`, `while` e `for` deve ter um bloco delimitado por chaves associado, mesmo que seja composto por uma única sentença;
- Caso uma função tenha mais de duas sentenças significativas, `this` é obrigatório para referênciar variáveis de instância;
- Caso um atributo tenha mais de um getter/setter, o getter/setter padrão deve vir primeiro e imediatamente abaixo todos os outros deve ser postos;
- Bloco de getters de um atributo deve vir imediatamente antes do bloco de setters;
- Blocos de getters e setters devem aparecer na mesma ordem que a declaração de seus atributos;
- A ordem de estruturação de uma classe deve ser:
    - atributos de classe (constantes ou não),
    - atributos (constantes ou não),
    - instância da própria classe (para singletons),
    - construtores,
    - método getInstance ou equivalente (para singletons),
    - getters e setters,
    - outros métodos (que não são getters nem setters),
    - substituições em geral,
    - substituição do método toString, e
    - classes internas;
- Uma exceção deve ser lançada com resposta a uma condição atômica, salvo casos onde se entende maior benefício na violação dessa regra;
- Mensagens lançadas em excessões deve ser em caixa baixa, exceto para unidades capitalizadas por padrão, tal como CPF ou LaTeX;
- Mensagens lançadas em excessões devem ser orações declarativas, na estrutura `<sujeito>` `<predicado>`, com:
    - sujeito sem artigo, e
    - predicado deve conter um verbo ou locução verbal, negada ou não, que indique claramente como o sujeito causou a exceção a ser lançada.

## Modelo

- model\
    - auth\
        - [x] AccessLevel.java
        - [ ] Session.java
    - custom\
        - [x] DeepClonable.java
        - [x] WorkshopObject.java
    - exception\
        - [x] WorkshopException.java
    - persistence\
        - [ ] History.java
        - [ ] Snapshot.java
        - [ ] WJson.java
    - workshop\
        - common\
            - [x] Customer.java
            - [x] Person.java
            - [x] Vehicle.java
        - date\
            - [x] Dates.java
            - [x] DateSpan.java
        - financial\
            - [x] Expense.java
            - [ ] Finance.java
            - [ ] Invoice.java
            - [ ] InvoiceDraft.java
        - service\
            - [x] Elevator.java
            - [x] ElevatorElevator.java
            - [ ] Scheduler.java
            - [ ] Service.java
            - [ ] ServiceOrder.java
            - [ ] Status.java
        - staff\
            - [ ] Administrator.java
            - [x] Employee.java
            - [x] StaffMember.java
        - stock\
            - [x] Product.java
            - [x] Shipment.java
            - [x] Item.java
            - [x] Stock.java
            - [x] Supplier.java
        - [ ] Workshop.java
    - [ ] WorkshopSystem.java

### Diagramas de Classes

```
mermaid
classDiagram

    class AccessLevel {
        <<enum>>

        SUPER $
        ADMIN $
        USER $
        GUEST $

        + isSufficedBy(AccessLevel)
    }

    class Session {
        - accessLevel: final AccessLevel
        - member: final StaffMember

        + Session(StaffMember, String, String)
    }

    Customer --|> Person
    class Customer {
        - address: String
        - email: String

        + Customer()
        + Customer(String, String, String, String, String)
        # Customer(Customer)

        + getAddress() String
        + setAddress(String) void
        + getEmail() String
        + setEmail(String) void
 
        + deepClone() Customer
        + toString() String
    }

    class Person["_Person_"] {
        - name: String
        - phone: String
        - cpf: String

        + Person()
        + Person(String, String, String)
        # Person(Person)

        + getName() String
        + setName(String) void
        + getPhone() String
        + setPhone(String) void
        + getCpf() String
        + getFullCpf() String
        + setCpf(String) void

        + toString() String
    }

    class Vehicle {
        - instanceCount: int $

        - id: int
        - model: String
        - plate: String
        - year: int

        + Vehicle()
        + Vehicle(String, String, int)
        # Vehicle(Vehicle)

        + getModel() String
        + setModel(String) void
        + getPlate() String
        + setPlate(String) void
        + getYear() int
        + setYear(int) void

        + getInstanceCount() int $
        - incrementInstanceCount() void $
        - generateNextId() int $
        
        + deepClone() Person
        + toString() String
    }

    class Dates {
        <<utility>>

        - DATETIME_FORMAT: final SimpleDateFormat $
        - DATE_FORMAT: final SimpleDateFormat $
        - DATETIME_PATTERN: final Pattern $
        - DATE_PATTERN: final Pattern $

        - MILLISECONDS_PER_SECOND: final long $
        - MILLISECONDS_PER_MINUTE: final long $
        - MILLISECONDS_PER_HOUR: final long $
        - MILLISECONDS_PER_DAY: final long $

        - Dates()
        
        + parse(String dateString) long $
        + formatAsDateTime(long timestamp) String $
        + formatAsDate(long timestamp) String $
        + formatAsInterval(long interval) String $
        + now() long $
    }

    DateSpan ..> Dates
    class DateSpan {
        - start: long
        - end: long

        + DateSpan(long, long)
        + DateSpan(DateSpan)

        + start() long
        + end() long
        + duration() long

        + compareTo(DateSpan)
        + deepClone() DateSpan
        + toString() String
    }

    class Expense {
        - name: String
        - description: String
        - value: double
        - date: long

        + Expense()
        + Expense(String, String, double, long)
        # Expense(Expense)

        + getName() String
        + setName(String) void
        + getDescription() String
        + setDescription(String) void
        + getValue() double
        + setValue(double) void
        + getDate() long
        + setDate(long) void

        + deepClone() Expense
        + toString() String
    }

    Finance *-- Expense
    Finance *-- Invoice
    class Finance {

    }

    Invoice ..> InvoiceDraft
    Invoice ..> ServiceOrder
    class Invoice {
        - customer: final int
        - products: final Item
        - services: final int[]
        - additional: final double
        - subtotal: final double

        + Invoice()
        + Invoice(ServiceOrder)
        # Invoice(Invoice)
    }

    InvoiceDraft *-- Item
    InvoiceDraft *-- Service
    class InvoiceDraft {
        - products: ArrayList~Item~
        - services: ArrayList~Integer~
        - additional: double

        + InvoiceDraft()
        + InvoiceDraft(double)
        # InvoiceDraft(InvoiceDraft)

        + getItems() Iterable~Integer~
        + getItem(int) Item
        + addItem(Item) void
        + addItems(Item...) void
        + removeItem(int) void
        + removeItems(int...) void
    }

    Elevator ..> ElevatorFunction
    class Elevator {
        - instanceCount: int $

        - id: final int
        - function: int
        - weightLimit: double

        + Elevator(int, double)
        + Elevator(int, double, boolean)
        # Elevator(Elevator)

        - id() int
        - getFunction() int
        - setFunction(int) void
        - getWeightLimit() double
        - setWeightLimit(double) void

        + getInstanceCount() int $
        - incrementInstanceCount() void $
        - generateNextId() int $
        
        + deepClone() Elevator
        + toString() String
    }

    class ElevatorFunction {
        <<enum>>

        + GENERAL $
        + ALIGNING $
        + BALANCING $

        - code: final int
        - name: final String

        - ElevatorFunction(int, String)

        + code() int
        + name() String

        + hasFunction(int) boolean
        + values() ElevatorFunction[] $

        + toString(int) String $
        + toString() String
    }

    class Scheduler {

        - elevators: Elevator[]
        - orders: TreeSet<ServiceOrder>
        - services: ArrayList<Service>

        - instance: Scheduler $

        - Scheduler()

        + scheduler() Scheduler $
        
        + getElevators() Iterable<Elevator>
        + getOrders() Iterable<ServiceOrders>
        + getOrder(int) ServiceOrder
        + addOrder(ServiceOrder)
        + removeOrder(int)
        + getServices() Iterable<Service>
        + getService(int) Service
        + addService(Service)
        + removeService(int)

        + toString() String
    }

    class Service {
        - instanceCount: int $

        - id: int
        - name: String
        - description: String
        - value: double

        + Service()
        + Service(int, String, String, double)
        # Service(Service)
        
        + id() int
        + getName() String
        + setName(String) void
        + getDescription() String
        + setDescription(String) void
        + getValue() double
        + setValue(double) void

        + getInstanceCount() int $
        - incrementInstanceCount() void $
        - generateNextId() int $

        + deepClone() Service
        + toString() String
    }

    ServiceOrder ..> Customer
    ServiceOrder ..> Vehicle
    ServiceOrder ..> Employee
    ServiceOrder *-- InvoiceDraft
    ServiceOrder o-- DateSpan
    class ServiceOrder {
        - instanceCount: int $

        - id: int
        - customer: int
        - vehicle: int
        - mechanic: int
        - invoice: InvoiceDraft
        - status: Status
        - datetime: DateSpan

        + ServiceOrder()
        + ServiceOrder(int, int, int, Status, DateSpan)
        + ServiceOrder(int, int, int, DateSpan)
        # ServiceOrder(ServiceOrder)

        + id() int
        + getCustomer() int
        + setCustomer(int) void
        + getVehicle() int
        + setVehicle(int) void
        + getMechanic() int
        + setMechanic(int) void
        + getInvoice() InvoiceDraft
        + getStatus() Status
        + setStatus(Status) void
        + getDateTime() DateSpan
        + setDateTime(DateSpan) void

        + getInstanceCount() int $
        - incrementInstanceCount() void $
        - generateNextId() int $

        + deepClone() ServiceOrder
        + toString() String
    }

    class Status {
        <<enum>>

        PENDING $
        INITIATED $
        READY $
        DELIVERED $
        CANCELED $
    }

    Administrator --|> StaffMember
    class Administrator {
        - proLabore: double
        - accessLevel: AccessLevel

        + Administrator()
        + Administrator(String, String, String, double, String, AccessLevel)
        # Administrator(Administrator)

        + getProLabore() double
        + setProLabore(double) void
        + getAccessLevel() AccessLevel
        + setAccessLevel(AccessLevel) void

        + deepClone() Administrator
        + toString() String
    }

    Employee o-- DateSpan
    Employee --|> StaffMember
    class Employee {
        - salary: double
        - accessLevel: AccessLevel
        - shifts: TreeSet<DateSpan>
        - openShift: long

        + Employee()
        + Employee(String, String, String, double, String, AccessLevel)
        # Employee(Employee)

        + setAccessLevel(AccessLevel)
        + getAccessLevel() AccessLevel

        + deepClone() Employee
        + toString() String
    }

    StaffMember --> AccessLevel 
    StaffMember --|> Person
    class StaffMember["_StaffMember_"] {
        - password: long

        + StaffMember()
        + StaffMember(String, String, String, String)
        # StaffMember(StaffMember)

        + getPassword() String
        + setPassword(String) void
        + checkPassword() boolean
        + getAccessLevel() AccessLevel *

        + checkCredentials(String, String) boolean

        + deepClone() StaffMember
        + toString() String
    }   

    class Item {
        - product: int
        - unitValue: double
        - quantity: int

        + Item()
        + Item(int, double, int)
        # Item(Item)

        + getProduct() int
        + setProduct(int) void
        + getUnitValue() double
        + getTotalValue() double
        + setUnitValue(double) void
        + setTotalValue(double) void
        + getQuantity() int
        + setQuantity(int) void
        + addQuantity(int) void
        + removeQuantity(int) void

        + deepClone() Item
        + toString() String
    }

    class Product {
        - instanceCount: int $

        - id: final int
        - name: String
        - unitValue: double
        - quantity: int
        - unit: String

        + Product()
        + Product(String)
        + Product(String, double)
        + Product(String, double, int)
        + Product(String, double, int, String)
        # Product(Product)

        + id() int
        + getName() String
        + setName(String) void
        + getUnitValue() double
        + getTotalValue() double
        + setUnitValue(double) void
        + setTotalValue(double) void
        + getQuantity() int
        + setQuantity(int) void
        + getUnit() String
        + setUnit(String) void

        + getInstanceCount() int $
        - incrementInstanceCount() void $
        - generateNextId() int $

        + deepClone() Product
        + toString() String
    }

    Shipment *-- Item
    class Shipment {
        - instanceCount: int $
        
        - id: final int
        - supplier: int
        - items: ArrayList~Item~
        - additional: double
        - arrival: long
        - paymentDate: long
        - accounted: boolean

        + Shipment()
        + Shipment(String)
        + Shipment(String, String)
        # Shipment(Shipment)

        + id() int
        + getSupplier() int
        + setSupplier(int) void
        + getItems() Iterable~Item~
        + getItem(int) Item
        + addItem(Item) void
        + removeItem(int) void
        + getAdditional() double
        + setAdditional(double) void
        + getArrival() long
        + hasArrived() boolean
        + setArrival(long) void
        + getPaymentDate() long
        + isPaid() boolean
        + setPaymentDate(long) void
        + isAccounted() boolean
        + setAccounted(boolean) void

        + getInstanceCount() int $
        - incrementInstanceCount() void $
        - generateNextId() int $

        + deepClone() Shipment
        + toString() String
    }

    Stock *-- Product
    Stock *-- Shipment
    Stock *-- Supplier
    class Stock {
        - products: HashMap~Integer, Product~
        - shipments: ArrayList~Shipment~
        - suppliers: ArrayList~Supplier~

        - instance: Stock $

        - Stock()
        # Stock(Stock)

        + stock() Stock $

        + getProducts() Iterable~Product~
        + getProduct(int) Product
        + addProduct(Product) void
        + removeProduct(int) void
        + getShipments() Iterable~Shipment~
        + getShipment(int) Shipment
        + addShipment(Shipment) void
        + removeShipment(int) void
        + getSuppliers() Iterable~Supplier~
        + getSupplier(int) Supplier
        + addSupplier(Supplier) void
        + removeSupplier(int) void

        + deepClone() Stock
        + toString() String
    }

    class Supplier {
        - instanceCount: int $

        - id: final int
        - tradeName: String
        - cnpj: String

        + Supplier()
        + Supplier(String)
        + Supplier(String, String)
        # Supplier(Supplier)

        + id() int
        + getTradeName() String
        + setTradeName(String) void
        + getCnpj() String
        + setCnpj(String) void

        + getInstanceCount() int $
        - incrementInstanceCount() void $
        - generateNextId() int $

        + deepClone() Supplier
        + toString() String
    }
```

## View

- visual\
    - gui\
    - tui\
        - entry\
            - Command.java
            - Context.java
            - Entry.java
        - exit\
            - ExitCode.java
            - ExitMessage.java
        - Command.java
        - Commands.java
        - Shell.java
        - Main.java

### Diagrama de classes

```
mermaid
classDiagram

    Command --|> Entry
    class Command["_Command_"] {
        + execute(String[]) ExitMessage *
    }

    Context --|> Entry
    class Context {
        

        + Context()
        + Context(Context, String)

        + add(Entry...) void

        + execute(String[]) ExitMessage
    }

    class Entry["_Entry_"] {
        - name: final String
        - parent: final Context

        + Entry()
        + Entry(Context, String)

        + name() String
        + fullName() String
        + parent() Context
        
        + execute(String[]) ExitMessage *

        + compareTo() int
        + toString() String
    }

    class ExitCode {
        <<enum>>

        SUCCESS $
        EXIT_CONTEXT $
        ENTER_CONTEXT $
        EXIT_SHELL $
        FAILURE $
        INVALID_ARGUMENT $
        COMMAND_NOT_FOUND $
        PERMISSION_DENIED $
    }

    class ExitMessage {
        - code: final ExitCode
        - message: final String

        + ExitMessage(ExitCode, String)
        + code() ExitCode
        + message() String
    }
```

## Persistence

```mermaid
classDiagram

    class Caretaker {
        - head: int
    }

    Persistence ..> Caretaker
    Persistence ..> Snapshot
    class Persistence {
        + ROOT_FILEPATH: Path $
        + CARETAKER_FILENAME: Path $
        + SNAPSHOT_FILEPATH: Path $
        
        - GSON: Gson $
        - CARETAKER_TYPE: Type $
        - SNAPSHOT_TYPE: Type $
        
        - Persistence()

        + captureSnapshot() $
        + restoreSnapshot(Snapshot) $

        + loadCaretaker() Caretaker $
        + saveCaretaker(Caretaker) void $
        + loadSnapshot(int id) Snapshot $
        + loadSnapshot(Snapshot) void $

        - ~T~ load(Path, Type) T $
        - ~T~ save(Path, Type) void $
    }

    Snapshot *-- InstanceCountState
    Snapshot *-- Workshop
    class Snapshot {
        - id: final int
        - version: final int
        - identifierState: final InstanceCountState
        - workshop: final Workshop
        - timestamp: final long
    }
    
    class InstanceCountState {
        + instanceCounts: HashMap~String, Integer~
    }

    class Workshop {
        ...
    }

```

## Referências

- https://docs.oracle.com/javase/tutorial/java/concepts/interface.html
- https://docs.oracle.com/javase/tutorial/java/generics/types.html
- https://docs.oracle.com/javase/tutorial/extra/generics/methods.html
- https://github.com/AlanLima287/Binary_Tree/
- https://github.com/dialex/JColor/
- https://www.debuggex.com/
- https://github.com/google/gson
- https://mermaid.js.org/syntax/classDiagram.html
- https://www.javier8a.com/itc/bd1/articulo.pdf
- https://emerson.emory.edu/services/latex/latex_143.html
- https://tikz.dev/gd-layered
- https://tex.stackexchange.com/questions/69439/how-can-i-achieve-relative-positioning-in-tikz
- https://www.omg.org/spec/UML/2.5/PDF
- https://tex.stackexchange.com/questions/616317/tikz-determining-the-size-in-cm-of-a-finished-tikz-picture
- https://jline.org/
- https://dzone.com/articles/running-a-java-class-as-a-subprocess
- https://stackoverflow.com/questions/16994582/within-a-running-jvm-how-to-programmatically-determine-the-jvm-options-used-at