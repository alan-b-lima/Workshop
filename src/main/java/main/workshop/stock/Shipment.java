package main.workshop.stock;

import java.util.ArrayList;

import main.util.WDate;

/**
 * Classe que representa um envio de peças.
 * 
 * @author Juan Pablo
 */
public class Shipment {

    /**
     * ID do envio.
     */
    public final long id;

    /**
     * Lista de peças do envio.
     */
    private ArrayList<Part> parts;

    /**
     * Data de chegada do envio.
     */
    private long arrival;

    /**
     * Construtor padrão.
     */
    public Shipment() {
        this.id = nextId();
        parts = new ArrayList<>();
    }

    /**
     * Conta a quantidade de instâncias criadas.
     */
    private static long instanceCount = 0;

    /**
     * Retorna a lista de peças do envio.
     * 
     * @return lista de peças do envio.
     */
    public ArrayList<Part> getParts() {
        return parts;
    }

    /**
     * Retorna as peças pelo nome.
     *
     * @param partName nome da peça a ser retornada.
     * @return a peça de {@code partName} se encontrada, {@code null} caso contrário.
     */
    public Part getPart(String partName) {

        for (Part part : parts) {
            if (part.getName().equalsIgnoreCase(partName)) { 
                // retorna a peça se o nome for igual.
                return part; 
            }
        }
        // retorna null se nenhuma peça com o nome informado for encontrada.
        return null; 
    }

    /**
     * Adiciona uma peça ao envio. Se a peça já existir, atualiza a
     * quantidade e o valor unitário da peça.
     * 
     * @param newPart peça a ser adicionada.
     */
    public void addPart(Part newPart) {

        Part existingPart = getPart(newPart.getName());

        if (existingPart == null) {
            parts.add(newPart); 
        } 
        
        else {
            int totalQuantity = existingPart.getQuantity() + newPart.getQuantity();
            double totalValue = existingPart.getTotalValue() + newPart.getTotalValue();
            double weightedAveragePrice = totalValue / (double) totalQuantity;

            existingPart.setQuantity(totalQuantity);
            existingPart.setUnitValue(weightedAveragePrice);
        }
    }

    /**
     * Retorna a data de chegada do envio.
     * 
     * @return data de chegada do envio.
     */
    public long getArrival() {
        return arrival;
    }

    /**
     * Define a data de chegada do envio.
     * 
     * @param arrival data de chegada do envio.
     */
    public void setArrival(long arrival) {
        this.arrival = arrival;
    }

    /**
     * Define a data de chegada do envio como a data atual.
     */
    public void setArrivalNow() {
        this.arrival = WDate.now();
    }

    /**
     * Retorna o ID de cada instância.
     * 
     * @return ID único da instância.
     */
    private static long nextId() {
        Shipment.instanceCount++;

        return Shipment.instanceCount;
    }
}