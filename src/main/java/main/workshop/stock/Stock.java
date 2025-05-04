package main.workshop.stock;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe que representa o estoque de peças.
 * 
 * @author Juan Pablo
 */
public class Stock {

    /**
     * Mapa de peças do estoque.
     */
    private HashMap<String, Part> parts;

    /**
     * Lista de remessas do estoque.
     */
    private ArrayList<Shipment> shipments;

    /**
     * Construtor padrão.
     */
    public Stock() {
        parts = new HashMap<>();
        shipments = new ArrayList<>();
    }

    /**
     * Retorna a quantidade de peças do estoque.
     * 
     * @param partName
     * @return
     */
    public int getPartQuantity(String partName) {
        if (parts.containsKey(partName)) {
            Part part = parts.get(partName);
            return part.getQuantity();
        }
        return -1;
    }

    /**
     * Adiciona as novas peças ao estoque.
     * 
     * @param partName nome da peça.
     * @param quantity quantidade da peça.
     */
    public void addPartQuantity(String partName, int quantity) {
        // Verifica se a peça existe no estoque
        if (parts.containsKey(partName)) {
            Part part = parts.get(partName);
            // Verifica se a quantidade é válida
            int currentQuantity = part.getQuantity();
            // Atualiza a quantidade da peça
            int newQuantity = currentQuantity + quantity; 
            part.setQuantity(newQuantity);
        }
    }

    /**
     * Remove a quantidade de peças do estoque.
     * 
     * @param partName nome da peça.
     * @param quantity quantidade da peça.
     */
    public void removePartQuantity(String partName, int quantity) {
        // Verifica se a peça existe no estoque
        if (parts.containsKey(partName)) {
            Part part = parts.get(partName);
            // Verifica se a quantidade é válida
            int currentQuantity = part.getQuantity();
            // Atualiza a quantidade da peça
            int newQuantity = currentQuantity - quantity; 
            part.setQuantity(newQuantity);
        }
    }

    /**
     * Retorna a lista de peças do estoque.
     * 
     * @return lista de peças do estoque.
     */
    public HashMap<String, Part> getParts() {
        return parts;
    }

    /**
     * Define o valor unitário da peça.
     * 
     * @param partName
     */
    public void setPartUnitValue(String partName) {
        if (parts.containsKey(partName)) {
            Part part = parts.get(partName);
            //Itera sobre as remessas
            for (Shipment shipment: shipments) {
                Part shipmentPart = shipment.getPart(partName);
                //Obtem o valor unitário das peças
                double weightedAveragePrice = shipmentPart.getUnitValue();
                //Defini o valor unitário da peça
                part.setUnitValue(weightedAveragePrice);
            }
        }
    }

    /**
     * Define o valor total das peças.
     * 
     * @param partName
     */
    public void setPartTotalValue(String partName) {
        if (parts.containsKey(partName)) {
            Part part = parts.get(partName);
            //Itera sobre as remessas
            for (Shipment shipment: shipments) {
                Part shipmentPart = shipment.getPart(partName);
                //Obtem o valor total das peças
                double weightedAveragePrice = shipmentPart.getTotalValue();
                //Defini o valor total das peças
                part.setTotalValue(weightedAveragePrice);
            }
        }   
    }

    /**
     * Retorna a remessa.
     * 
     * @param shipment
     * @return
     */
    public ArrayList<Shipment> getShipments() {
        return shipments;
    }

    /**
     * Adiciona uma remessa ao estoque.
     * @param shipment
     */
    public void addShipment(Shipment shipment) {
        if (shipment == null) {
            return;//Something here
        }

        //Adiciona a remessa ao estoque
        shipments.add(shipment);

        // Itera sobre as partes das remessas
        for (Part part : shipment.getParts()) {
            String partName = part.getName();
            int quantity = part.getQuantity();

            // Verifica se a peça já existe no estoque
            if (parts.containsKey(partName)) {
                // Se a peça já existe, atualiza a quantidade
                addPartQuantity(partName, quantity);
            } else {
                // Se a peça não existe, adiciona ao estoque
                parts.put(partName, part);
            }
        }
    }
}