package model.workshop.stock.transaction;

import java.util.ArrayList;
import java.util.HashMap;

import model.workshop.stock.Part;
import model.workshop.stock.Shipment;

/**
 * Classe que representa uma transação de estoque, contendo peças e remessas.
 */
public class Transaction {

    /**
     * Lista de peças associadas a esta transação.
     */
    private final Part[] parts;

    /**
     * Lista de remessas associadas a esta transação.
     */
    private final Shipment[] shipments;

    /**
     * Construtor padrão.
     */
    public Transaction() {
        parts = null;
        shipments = null;
    }

    /**
     * Construtor parametrizado.
     * 
     * @param parts     um mapa que associa o ID de uma peça a uma peça
     *                  correspondente.
     * @param shipments uma lista de remessas associadas a esta transação.
     */
    public Transaction(HashMap<Integer, Part> parts, ArrayList<Shipment> shipments) {

        this.parts = (Part[]) parts.values().toArray();
        this.shipments = (Shipment[]) shipments.toArray();

        for (int i = 0; i < this.parts.length; i++) {
            this.parts[i] = this.parts[i].deepClone();
        }
        
        for (int i = 0; i < this.shipments.length; i++) {
            this.shipments[i] = this.shipments[i].deepClone();
        }
    }

    /**
     * Retorna as partes associadas a esta transação.
     * 
     * @return um mapa que associa o ID de uma peça a uma peça correspondente.
     */
    public HashMap<Integer, Part> parts() {

        HashMap<Integer, Part> partMap = new HashMap<>();
        for (Part part : parts) {
            partMap.put(part.getPartKind(), part);
        }

        return partMap;
    }

    /**
     * Retorna as remessas associadas a esta transação.
     * 
     * @return uma lista de remessas.
     */
    public ArrayList<Shipment> shipments() {

        ArrayList<Shipment> shipmentList = new ArrayList<>();
        for (Shipment shipment : shipments) {
            shipmentList.add(shipment);
        }

        return shipmentList;
    }
}
