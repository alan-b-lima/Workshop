package model.workshop.stock.transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        this.parts = new Part[parts.size()];
        this.shipments = new Shipment[shipments.size()];

        for (int i = 0; i < parts.size(); i++) {
            
        }
    }

    /**
     * Retorna as partes associadas a esta transação.
     * 
     * @return um mapa que associa o ID de uma peça a uma peça correspondente.
     */
    public HashMap<Integer, Part> getParts() {

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
    public ArrayList<Shipment> getShipments() {

        ArrayList<Shipment> shipmentList = new ArrayList<>();
        for (Shipment shipment : shipments) {
            shipmentList.add(shipment);
        }

        return shipmentList;
    }
}
