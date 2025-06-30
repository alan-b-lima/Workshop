package edu.ajan.model.persistence;

import edu.ajan.model.workshop.Workshop;

/**
 * Classe que representa um snapshot do estado do workshop.
 * 
 * @author Alan Lima
 */
public class Snapshot {

    /**
     * Estado da contagem de instâncias no momento do snapshot.
     */
    private final InstanceCountState instanceCountState;

    /**
     * Workshop associado ao snapshot.
     */
    private final Workshop workshop;

    /**
     * Timestamp que representa o momento em que o snapshot foi capturado.
     */
    private final long timestamp;

    /**
     * Construtor padrão.
     * @param workshop o workshop associado ao snapshot.
     * @param state    o estado da contagem de instâncias no momento do snapshot.
     */
    public Snapshot(Workshop workshop, InstanceCountState state) {
        this.workshop = workshop;
        this.instanceCountState = state;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Retorna o workshop associado ao snapshot.
     * 
     * @return o workshop associado ao snapshot.
     */
    public Workshop workshop() {
        return workshop;
    }
    
    /**
     * Retorna o estado da contagem de instâncias no momento do snapshot.
     * 
     * @return o estado da contagem de instâncias.
     */
    public InstanceCountState instanceCountState() {
        return instanceCountState;
    }

    /**
     * Retorna o timestamp que representa o momento em que o snapshot foi capturado.
     * 
     * @return o timestamp do snapshot.
     */
    public long timestamp() {
        return timestamp;
    }
}
