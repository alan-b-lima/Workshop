# Trabalho Prático II - Padrões de Projeto - Memento

Sem violar o encapsulamento, captura e externaliza o estado interno de um objeto, de tal forma que o objeto possa ser restaurado a esse estado mais tarde.

## Apresentação

1. Apresentação da equipe
1. Apresentação do tema
1. 

## Aplicação

```mermaid
classDiagram

    Part <-- PartKind
    class Part {
        - partKind: int
        - unitValue: double
        - quantity: int

        + Part()
        + Part(PartKind, double, int)

        + getPartKind() int
        + setPartKind(int) void
        + getUnitValue() double
        + getTotalValue() double
        + setUnitValue(double) void
        + setTotalValue(double) void
        + getQuantity() int
        + setQuantity(int) void
        + addQuantity(int) void
        + removeQuantity(int) void

        + toString() String
    }

    class PartKind {
        + id: int
        - name: String
        - unit: String

        + PartKind()
        + PartKind(String, String)

        - instanceCount: int $

        + getName() String
        + setName(String) void
        + getUnit() String
        + setUnit(String) void

        + getInstanceCount() int $
        - incrementInstanceCount() void $
        - generateNextId() int $

        + toString() String
    }

    Shipment o-- Part
    Shipment <-- Supplier
    class Shipment {
        + id: int
        - supplier: int
        - parts: ArrayList~Part~
        - arrival: long

        + Shipment()
        + Shipment(int, long)

        - instanceCount: int $

        + getSupplier() int
        + setSupplier(int) void
        + getParts() Iterator~Part~
        + getPart(int) Part
        + addPart(Part) void
        + removePart(int) void
        + getArrival() long
        + setArrival(long) void
        + setArrivalNow() void

        + getInstanceCount() int $
        - incrementInstanceCount() void $
        - generateNextId() int $

        + toString() String
    }

    class Supplier {
        + id: int
        - tradeName: String
        - cnpj: String

        + Supplier()
        + Supplier(String, String)

        - instanceCount: int $

        + getTradeName() String
        + setTradeName(String) void
        + getCnpj() String
        + setCnpj(String) void

        + getInstanceCount() int $
        - incrementInstanceCount() void $
        - generateNextId() int $

        + toString() String
    }

    Stock o-- Part
    Stock *-- Shipment
    Stock *-- Supplier
    Stock *-- PartKind
    class Stock {
        - parts: HashMap~Integer, Part~
        - shipments: ArrayList~Shipment~
        - suppliers: ArrayList~Supplier~
        - partKinds: HashMap~Integer, PartKind~

        - instance: Stock

        - Stock()

        + stock()
        
        + getParts() Iterator~Part~
        + getPart(int) Part
        + addPart(Part) void
        + removePart(int) void
        + getShipments() Iterator~Shipment~
        + getShipment(int) Shipment
        + addShipment(Shipment) void
        + removeShipment(int) void
        + getSuppliers(int) Iterator~Supplier~
        + getSupplier(int) Supplier
        + addSupplier(Supplier) void
        + removeSupplier(int) void
        + getPartKinds() Iterator~PartKind~
        + getPartKind(int) PartKind
        + addPartKind(PartKind) void
        + removePartKind(int) void

        + toString() String
    }

    class Transaction {
        - parts: Part[]
        - shipments: Shipment[]

        + Transaction(HashMap~Integer, Part~, ArrayList~Shipment~)

        + getParts() HashMap~Integer, Part~
        + getShipments() HashMap~Shipment~
    }

    class Caretaker {
           
    }
```

## Referências

- https://www.javier8a.com/itc/bd1/articulo.pdf