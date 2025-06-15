package model.workshop.stock;

import java.util.ArrayList;
import java.util.Iterator;

import custom.DeepClonable;

/**
 * Classe que representa uma remessa de peças.
 * 
 * @author Juan Pablo
 */
public class Shipment implements DeepClonable<Shipment> {

    /**
     * Identificador único da remessa.
     */
    private final int id;

    /**
     * Identificador do fornecedor que enviou a remessa.
     */
    private int supplier;

    /**
     * Lista de partes contidas na remessa.
     */
    private ArrayList<Part> parts;

    /**
     * Data de chegada da remessa.
     */
    private long arrival;

    /**
     * Construtor padrão.
     */
    public Shipment() {
        this.id = generateNextId();
        this.parts = new ArrayList<>();
    }

    /**
     * Construtor parametrizado.
     * 
     * @param supplier identificador do fornecedor que enviou a remessa.
     * @param arrival  data de chegada da remessa.
     */
    public Shipment(int supplier, long arrival) {
        this();
        this.supplier = supplier;
        this.arrival = arrival;
    }

    /**
     * Construtor de clonagem.
     * 
     * @param shipment remessa a ser clonada.
     */
    private Shipment(Shipment shipment) {
        this.id = shipment.id;
        this.supplier = shipment.supplier;
        this.arrival = shipment.arrival;

        this.parts = new ArrayList<>(shipment.parts.size());
        for (Part part : shipment.parts) {
            this.parts.add(part.deepClone());
        }
    }

    /**
     * Contador de instâncias da classe Supplier.
     */
    private static int instanceCount = 0;

    /**
     * Retorna o ID da remessa.
     * 
     * @return ID da remessa.
     */
    public int id() {
        return id;
    }

    /**
     * Retorna o ID da remessa.
     * 
     * @return ID da remessa.
     */
    public int getSupplier() {
        return supplier;
    }

    /**
     * Define o ID do fornecedor que enviou a remessa.
     * 
     * @param supplier ID do fornecedor.
     */
    public void setSupplier(int supplier) {
        this.supplier = supplier;
    }

    /**
     * Retorna a lista de partes contidas na remessa.
     * 
     * @return lista de partes.
     */
    public Iterator<Part> getParts() {
        return parts.iterator();
    }

    /**
     * Retorna uma parte específica da remessa com base no tipo.
     * 
     * @param kind tipo da parte a ser retornada.
     * @return parte correspondente ao tipo, ou null se não encontrada.
     */
    public Part getPart(int kind) {
        for (Part part : parts) {
            if (part.getPartKind() == kind) {
                return part;
            }
        }

        return null;
    }

    /**
     * Retorna uma parte específica da remessa com base no ID.
     * 
     * @param id ID da parte a ser retornada.
     * @return parte correspondente ao ID, ou null se não encontrada.
     */
    public void addPart(Part part) {
        if (part != null && !parts.contains(part)) {
            parts.add(part);
        }
    }

    /**
     * Remove uma parte específica da remessa.
     * 
     * @param part parte a ser removida.
     */
    public void removePart(Part part) {
        if (part != null) {
            parts.remove(part);
        }
    }

    /**
     * Retorna a data de chegada da remessa.
     * 
     * @return data de chegada.
     */
    public long getArrival() {
        return arrival;
    }

    /**
     * Define a data de chegada da remessa.
     * 
     * @param arrival data de chegada.
     */
    public void setArrival(long arrival) {
        this.arrival = arrival;
    }

    /**
     * Define a data de chegada da remessa para o momento atual.
     */
    public void setArrivalNow() {
        this.arrival = System.currentTimeMillis();
    }

    /**
     * Retorna o contador de instâncias da classe Supplier.
     *
     * @return contador de instâncias.
     */
    public static int getInstanceCount() {
        return instanceCount;
    }

    /**
     * Incrementa o contador de instâncias.
     */
    private static void incrementInstanceCount() {
        instanceCount++;
    }

    /**
     * Gera o próximo ID para a remessa, incrementando o contador de instâncias.
     *
     * @return próximo ID.
     */
    private static int generateNextId() {
        incrementInstanceCount();
        return instanceCount;
    }

    /**
     * Faz uma cópia profunda da remessa
     * 
     * @return cópia da remessa
     */
    public Shipment deepClone() {
        return new Shipment(this);
    }

    /**
     * Retorna uma representação textual da remessa.
     *
     * @return representação textual da remessa.
     */
    @Override
    public String toString() {
        return String.format("(%d, %d, %d)", id, supplier, arrival);
        // return String.format("Shipment{id: %d, supplier: %d, arrival: %d}", id, supplier, arrival);
    }
}