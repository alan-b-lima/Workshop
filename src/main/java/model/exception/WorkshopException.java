package model.exception;

public class WorkshopException extends Exception {

    /**
     * Construtor que recebe uma mensagem de erro.
     * 
     * @param msg mensagem da exceção a ser lançada
     */
    public WorkshopException(String msg) {
        super(String.format("\033[31m%s\033[m", msg));
    }

    /**
     * Construtor que recebe uma mensagem formatada de erro.
     * 
     * @param msg  mensagem da exceção a ser lançada
     * @param args argumentos para formatar a mensagem
     */
    public WorkshopException(String msg, Object... args) {
        this(String.format(msg, args));
    }
}
