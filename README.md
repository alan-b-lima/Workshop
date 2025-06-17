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
- O bloco de getters de um atributo deve vir imediatamente antes do bloco de setters;
- Os blocos de getters e setters devem aparecer na mesma ordem que a declaração de seus atributos;
- A ordem de estruturação de uma classe deve ser:
    - atributos de classe (constantes ou não),
    - atributos (constantes ou não),
    - instância da própria classe (para singletons),
    - construtores,
    - método getInstance ou equivalente (para singletons),
    - getters e setters,
    - outros métodos (que não são getters nem setters),
    - substituições em geral,
    - a substituição do método toString,
    - classes internas (se houver).

## Modelo

- model\
    - auth\
        - [ ] AuthLevel.java
        - [ ] Session.java
    - custom\
        - [ ] DeepClonable.java
        - [ ] WorkshopObject.java
    - exception\
        - [ ] WorkshopException.java
    - snapshot\
        - [ ] History.java
        - [ ] Snapshot.java
        - [ ] WJson.java
    - workshop\
        - common\
            - [ ] Customer.java
            - [ ] Person.java
            - [ ] Vehicle.java
        - date\
            - [ ] DateSpan.java
            - [ ] Schedule.java
            - [ ] WDate.java
        - financial\
            - [ ] Finance.java
            - [ ] Expense.java
            - [ ] Invoice.java
        - service\
            - [ ] Elevator.java
            - [ ] Scheduler.java
            - [ ] Service.java
            - [ ] ServiceOrder.java
        - staff\
            - [ ] Employee.java
            - [ ] Manager.java
            - [ ] StaffMember.java
        - stock\
            - [ ] Product.java
            - [ ] Shipment.java
            - [ ] ShipmentItem.java
            - [ ] Stock.java
            - [ ] Supplier.java
        - [ ] Workshop.java
    - [ ] WorkshopSystem.java

### Diagrama de Classes

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
        - Product(Product)

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
        - ShipmentItem(ShipmentItem)

        + getProductId() int
        + getProduct() Product
        + setProduct(int) void
        + setProduct(Product) void
        + getUnitValue() double
        + getTotalValue() double
        + setUnitValue(double) void
        + setTotalValue(double) void
        + getQuantity() int
        + setQuantity(int) void

        + deepClone() ShipmentItem
        + toString() String
    }

    Shipment *-- ShipmentItem
    Shipment --|> WorkshopObject
    class Shipment {
        - instanceCount: int $
        
        - id: final int
        - supplier: int
        - parts: ArrayList~ShipmentItem~
        - arrival: long
        - paymentDate: long
        - accounted: boolean

        + Shipment()
        + Shipment(String)
        + Shipment(String, String)
        - Shipment(Shipment)

        + id() int
        + getSupplier() int
        + setSupplier(int) void
        + getProducts() Iterable~Product~
        + getProductStream() Stream~Product~
        + getProduct(int) Product
        + addProduct(Product) void
        + addProducts(Product...) void
        + removeProduct(int) void
        + removeProducts(int...) void
        + getArrival() long
        + hasArrived() boolean
        + setArrival(long) void
        + getPaymentDate() long
        + isPaid() boolean
        + setPaymentDate(long) void
        + isAccounted() boolean
        + setAccounted() void
        + setAccounted(boolean) void

        + getInstanceCount() int $
        - incrementInstanceCount() void $
        - generateNextId() int $

        + deepClone() Shipment
        + toString() String
    }

    class Stock {
        - products: HashMap~Integer, Product~
        - shipments: ArrayList~Shipment~
        - suppliers: ArrayList~Supplier~

        + Stock()
        - Stock(Stock)

        + getProducts() Iterable~Product~
        + getProductStream() Stream~Product~
        + getProduct(int) Product
        + addProduct(Product) void
        + addProducts(Product...) void
        + removeProduct(int) void
        + removeProducts(int...) void
        + getShipments() Iterable~Shipment~
        + getShipmentStream() Stream~Shipment~
        + getShipment(int) Shipment
        + addShipment(Shipment) void
        + addShipments(Shipment...) void
        + removeShipment(int) void
        + removeShipments(int...) void        
        + getSuppliers() Iterable~Supplier~
        + getSupplierStream() Stream~Supplier~
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
        - Supplier(Supplier)

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