package edu.ajan.model.exception;

/**
 * Classe que representa uma exceção personalizada.
 */
public class WorkshopException extends RuntimeException {

    /**
     * Construtor padrão.
     */
    public WorkshopException() {
        super();
    }

    /**
     * Construtor parametrizado.
     * 
     * @param msg mensagem da exceção a ser lançada.
     */
    public WorkshopException(String msg) {
        super(msg);
    }

    /**
     * Construtor parametrizado variádico.
     * 
     * @param msg  mensagem da exceção a ser lançada.
     * @param args argumentos para formatar a mensagem.
     */
    public WorkshopException(String msg, Object... args) {
        super(String.format(msg, args));
    }

    /**
     * Retorna uma exceção formatado com o nome do método passado.
     * 
     * @param methodName nome do método não implementado.
     * @return exceção com a mensagem correta.
     */
    // @Deprecated(forRemoval = true) // Descomente isso na hora certa
    public static WorkshopException methodNotImplemented(String methodName) {
        return new WorkshopException("método '%s' não implementado", methodName);
    }
}