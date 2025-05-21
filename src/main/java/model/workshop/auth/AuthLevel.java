package model.workshop.auth;

/**
 * Classe que representa os níveis de autenticação do sistema.
 * 
 * @author Alan Lima
 */
public final class AuthLevel {
    
    /**
     * Construtor privado para evitar instâncias.
     */
    private AuthLevel() {
        // Não deve ser instanciado
    }

    public static final byte MANAGER = 0;
    public static final byte EMPLOYEE = 1;

}
