# Sistema de Gerenciamento para uma Oficina Mecânica

Esse é um sistema de gerenciamento para uma oficina mecânica, que abrange diversas funcionalidades como agendamento de serviços, controle de estoque, gestão financeira e cadastro de clientes e veículos e gerenciamento de estoque.

## Objetivo Geral

Aplicar os conceitos de Programação Orientada a Objetos (POO) para desenvolver um sistema de gerenciamento de uma oficina mecânica, utilizando Java como linguagem de programação.

## Autores

- Alan Barbosa Lima         [@alan-b-lima](https://github.com/alan-b-lima)
- Juan Pablo Ferreira Costa [@juan-ferreirax](https://github.com/juan-ferreirax)

## Estrutura do Projeto

O projeto é estruturado em duas partes principais: **Modelo** (Backend) e **Visual** (Frontend).

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
        - [ ] Authenticable.java
        - [ ] AuthLevel.java
        - [ ] Session.java
    - custom\
        - [ ] DeepClonable.java
        - [ ] WorkshopObject.java
    - exception\
        - [ ] WorkshopException.java
    - persistence\
        - [ ] History.java
        - [ ] Snapshot.java
        - [ ] WJson.java
    - workshop\
        - common\
            - [x] Customer.java
            - [x] Person.java
            - [ ] Vehicle.java
        - date\
            - [x] Dates.java
            - [x] DateSpan.java
        - financial\
            - [ ] Finance.java
            - [x] Expense.java
            - [ ] Invoice.java
        - service\
            - [x] Elevator.java
            - [x] ElevatorElevator.java
            - [ ] Scheduler.java
            - [ ] Service.java
            - [ ] ServiceOrder.java
        - staff\
            - [ ] Administrator.java
            - [ ] Employee.java
            - [ ] StaffMember.java
        - stock\
            - [x] Product.java
            - [x] Shipment.java
            - [x] ShipmentItem.java
            - [x] Stock.java
            - [x] Supplier.java
        - [ ] Workshop.java
    - [ ] WorkshopSystem.java

### Diagrama de Classes

```mermaid
classDiagram

    class AuthLevel {
        <<enumeration>>

        SUPER,
        ADMIN,
        USER,
        GUEST,
    }

    class Session {
        - authLevel: final AuthLevel
        - member: final StaffMember

        + Session(StaffMember, String, String)
    }

    Customer --|> Person
    class Customer {
        - address: String
        - email: String

        + Customer()
        + Customer(String, String, String, String, String)
        - Customer()

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

    Elevator ..> ElevatorFunction
    class Elevator {
        - instanceCount: int

        - id: final int
        - function: int
        - weightLimit: double
        - working: boolean

        + Elevator(int, double)
        + Elevator(int, double, boolean)
        # Elevator(Elevator)

        - id() int
        - getFunction() int
        - setFunction(int) void
        - getWeightLimit() double
        - setWeightLimit(double) void
        - isWorking() boolean
        - setWorking(boolean) void

        + getInstanceCount() int $
        - incrementInstanceCount() void $
        - generateNextId() int $
        
        + deepClone() Elevator
        + toString() String
    }

    class ElevatorFunction {
        <<enum>>

        + GENERAL: ElevatorFunction $
        + ALIGNING: ElevatorFunction $
        + BALANCING: ElevatorFunction $

        + code: final int
        + name: final String

        - ElevatorFunction(int, String)

        + hasFunction(int) boolean
        + values() ElevatorFunction[] $

        + toString(int) String $
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

    Administrator --|> StaffMember
    class Administrator {
        - proLabore: double

        + Administrator()
        + Administrator(String, String, String, double, String)
        # Administrator(Administrator)

        + getProLabore() double
        + setProLabore(double) void

        + getAuthLevel() AuthLevel
        + checkCredentials(String, String) boolean

        + deepClone() Administrator
        + toString() String
    }

    Employee --|> StaffMember
    class Employee {
        - salary: double
        - authLevel: AuthLevel

        + setAuthLevel(AuthLevel)
        + getAuthLevel() AuthLevel
    }

    StaffMember --|> Person
    class StaffMember["_StaffMember_"] {
        - password: long

        + StaffMember()
        + StaffMember(String, String, String, String)
        # StaffMember(StaffMember)

        + getPassword() String
        + setPassword(String) void

        + checkCredentials(String, String) boolean
    }
```

#### Estoque

```mermaid
classDiagram

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

    class ShipmentItem {
        - product: int
        - unitValue: double
        - quantity: int

        + ShipmentItem()
        + ShipmentItem(int, double, int)
        # ShipmentItem(ShipmentItem)

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

        + deepClone() ShipmentItem
        + toString() String
    }

    Shipment *-- ShipmentItem
    class Shipment {
        - instanceCount: int $
        
        - id: final int
        - supplier: int
        - items: ArrayList~ShipmentItem~
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
        + getItems() Iterable~ShipmentItem~
        + getItem(int) ShipmentItem
        + addItem(ShipmentItem) void
        + addItems(ShipmentItem...) void
        + removeItem(int) void
        + removeItems(int...) void
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
        + addProducts(Product...) void
        + removeProduct(int) void
        + removeProducts(int...) void
        + getShipments() Iterable~Shipment~
        + getShipment(int) Shipment
        + addShipment(Shipment) void
        + addShipments(Shipment...) void
        + removeShipment(int) void
        + removeShipments(int...) void
        + getSuppliers() Iterable~Supplier~
        + getSupplier(int) Supplier
        + addSupplier(Supplier) void
        + addSuppliers(Supplier...) void
        + removeSupplier(int) void
        + removeSuppliers(int...) void

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

## Visual

- visual\
    - gui\
    - tui\
        - entry\
            - CommandEntry.java
            - ContextEntry.java
            - Entry.java
        - exit\
            - ExitCode.java
            - ExitMessage.java
        - Command.java
        - Commands.java
        - Shell.java
        - Main.java
```

### Diagrama de classes

```mermaid
classDiagram

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