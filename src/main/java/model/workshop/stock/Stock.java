package model.workshop.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Classe que representa o estoque de peças, remessas e fornecedores.
 * 
 * @author Alan Lima
 */
public class Stock {

    /**
     * Mapa que associa o ID de uma peça a uma peça correspondente.
     */
    private HashMap<Integer, Part> parts;

    /**
     * Lista de remessas.
     */
    private ArrayList<Shipment> shipments;

    /**
     * Lista de fornecedores.
     */
    private ArrayList<Supplier> suppliers;

    /**
     * Mapa que associa o ID de um tipo de peça a um tipo de peça correspondente.
     */
    private HashMap<Integer, PartKind> partKinds;

    private static Stock instance = new Stock();

    /**
     * Construtor padrão.
     */
    public Stock() {
        parts = new HashMap<>();
        shipments = new ArrayList<>();
        suppliers = new ArrayList<>();
        partKinds = new HashMap<>();
    }

    public static Stock stock() {
        return instance;
    }

    /**
     * Retorna um iterador sobre as partes do estoque.
     *
     * @return um iterador sobre as partes.
     */
    public Iterator<Part> getParts() {
        return parts.values().iterator();
    }

    /**
     * Retorna uma parte do estoque pelo ID.
     *
     * @param id o ID da parte.
     * @return a parte correspondente ao ID, ou null se não existir.
     */
    public Part getPart(int id) {
        return parts.get(id);
    }

    /**
     * Adiciona uma parte ao estoque.
     *
     * @param part a parte a ser adicionada.
     */
    public void addPart(Part part) {
        parts.put(part.getPartKind(), part);
    }

    /**
     * Remove uma parte do estoque pelo ID.
     *
     * @param id o ID da parte a ser removida.
     */
    public void removePart(int id) {
        parts.remove(id);
    }

    /**
     * Retorna um iterador sobre as remessas do estoque.
     *
     * @return um iterador sobre as remessas.
     */
    public Iterator<Shipment> getShipments() {
        return shipments.iterator();
    }

    /**
     * Retorna uma remessa do estoque pelo ID.
     *
     * @param id o ID da remessa.
     * @return a remessa correspondente ao ID, ou null se não existir.
     */
    public Shipment getShipment(int id) {
        for (Shipment shipment : this.shipments) {
            if (shipment.id() == id) {
                return shipment;
            }
        }

        return null;
    }

    /**
     * Adiciona uma remessa ao estoque.
     *
     * @param shipment a remessa a ser adicionada.
     */
    public void addShipment(Shipment shipment) {
        shipments.add(shipment);
    }

    /**
     * Remove uma remessa do estoque pelo ID.
     *
     * @param shipmentId o ID da remessa a ser removida.
     */
    public void removeShipment(int shipmentId) {
        shipments.remove(getShipment(shipmentId));
    }

    /**
     * Retorna um iterador sobre os fornecedores do estoque.
     *
     * @return um iterador sobre os fornecedores.
     */
    public Iterator<Supplier> getSuppliers() {
        return suppliers.iterator();
    }

    /**
     * Retorna um fornecedor do estoque pelo ID.
     *
     * @param id o ID do fornecedor.
     * @return o fornecedor correspondente ao ID, ou null se não existir.
     */
    public Supplier getSupplier(int id) {
        for (Supplier supplier : this.suppliers) {
            if (supplier.id() == id) {
                return supplier;
            }
        }

        return null;
    }

    /**
     * Adiciona um fornecedor ao estoque.
     *
     * @param supplier o fornecedor a ser adicionado.
     */
    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    /**
     * Remove um fornecedor do estoque pelo ID.
     *
     * @param supplierId o ID do fornecedor a ser removido.
     */
    public void removeSupplier(int supplierId) {
        suppliers.remove(getSupplier(supplierId));
    }

    /**
     * Retorna um iterador sobre os tipos de peças do estoque.
     *
     * @return um iterador sobre os tipos de peças.
     */
    public Iterator<PartKind> getPartKinds() {
        return partKinds.values().iterator();
    }

    /**
     * Retorna um tipo de peça do estoque pelo ID.
     *
     * @param id o ID do tipo de peça.
     * @return o tipo de peça correspondente ao ID, ou null se não existir.
     */
    public PartKind getPartKind(int id) {
        return partKinds.get(id);
    }

    /**
     * Retorna um tipo de peça do estoque pelo nome.
     *
     * @param id o ID do tipo de peça.
     * @return o tipo de peça correspondente ao ID, ou null se não existir.
     */
    public PartKind getPartKind(String name) {
        for (Entry<Integer, PartKind> pKindEntry : this.partKinds.entrySet()) {
            if (pKindEntry.getValue().getName().equals(name)) {
                return pKindEntry.getValue();
            }
        }

        return null;
    }

    /**
     * Adiciona um tipo de peça ao estoque.
     *
     * @param partKind o tipo de peça a ser adicionado.
     */
    public void addPartKind(PartKind partKind) {
        partKinds.put(partKind.id(), partKind);
    }

    /**
     * Remove um tipo de peça do estoque pelo ID.
     *
     * @param partKindId o ID do tipo de peça a ser removido.
     */
    public void removePartKind(int partKindId) {
        partKinds.remove(partKindId);
    }

    /**
     * Cria um memento do estoque, contendo as partes e remessas atuais.
     *
     * @return um memento do estoque.
     */
    public Transaction createTransaction() {
        return this.new Transaction();
    }

    /**
     * Restaura o estoque a partir de um memento, substituindo as partes e remessas
     * atuais pelas partes e remessas do memento.
     * 
     * @param transaction o memento que contém as partes e remessas a serem
     *                    restauradas.
     */
    public void restoreTransaction(Transaction transaction) {
        this.parts = transaction.parts();
        this.shipments = transaction.shipments();
    }

    /**
     * Retorna uma representação textual do estoque.
     * 
     * @return representação textual do estoque.
     */
    @Override
    public String toString() {
        return String.format("(%s, %s, %s, %s)", parts, shipments, suppliers, partKinds);
        // return String.format("Stock{parts: %s, shipments: %s, suppliers: %s, partKinds: %s}", parts, shipments, suppliers, partKinds);
    }

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

            int i = 0;

            this.parts = new Part[Stock.this.parts.size()];
            for (Entry<Integer, Part> entry : Stock.this.parts.entrySet()) {
                this.parts[i++] = entry.getValue().deepClone();
            }

            i = 0;

            this.shipments = new Shipment[Stock.this.shipments.size()];
            for (Shipment shipment : Stock.this.shipments) {
                this.shipments[i++] = shipment.deepClone();
            }
        }

        /**
         * Retorna as partes associadas a esta transação.
         * 
         * @return um mapa que associa o ID de uma peça a uma peça correspondente.
         */
        public HashMap<Integer, Part> parts() {

            HashMap<Integer, Part> partMap = new HashMap<>(parts.length);
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

            ArrayList<Shipment> shipmentList = new ArrayList<>(shipments.length);
            for (Shipment shipment : shipments) {
                shipmentList.add(shipment);
            }

            return shipmentList;
        }

        /**
         * Retorna uma representação textual da transação
         * 
         * @return representação textual da transação
         */
        @Override
        public String toString() {
            return String.format("(%s, %s)", parts, shipments);
            // return String.format("Transaction{parts: %s, shipments: %s}", parts, shipments);
        }
    }
}