package edu.ajan.model.workshop.service;

/**
 * Enumeração do estados das ordens de serviço.
 */
public enum Status {

    /**
     * Denota que o veículo está aguardando o lançamento da inspeção.
     */
    PENDING,

    /**
     * Denota que o veículo está aguardando a manutenção ou está sendo manutenido.
     */
    INITIATED,

    /**
     * Denota que a manutenção do veículo está pronta e que ele já pode ser entregue.
     */
    COMPLETED,

    /**
     * Denota que o veículo foi entregue ao cliente.
     */
    DELIVERED,

    /**
     * Denota que a manutenção foi cancelada.
     */
    CANCELLED;

}
