package edu.ajan.model.exception;

/**
 * Classe que representa uma erro, não recuperável, da oficina. Uma vez lançado,
 * a aplicação irá abortar, tomando as medidas cabivéis, imediatamente.
 * 
 * @author Alan Lima
 */
public class WorkshopError extends Error {

    /**
     * Construtor padrão.
     */
    public WorkshopError() {
        super();
    }

    /**
     * Construtor parametrizado.
     * 
     * @param msg mensagem da exceção a ser lançada.
     */
    public WorkshopError(String msg) {
        super(msg);
    }

    /**
     * Construtor parametrizado variádico.
     * 
     * @param msg  mensagem da exceção a ser lançada.
     * @param args argumentos para formatar a mensagem.
     */
    public WorkshopError(String msg, Object... args) {
        super(String.format(msg, args));
    }
}
