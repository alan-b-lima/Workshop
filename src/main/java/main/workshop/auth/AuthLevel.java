package main.workshop.auth;

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
        
    }

    /**
     * Nível de autenticação do gerente.
     */
    public static final byte MANAGER = 0;

    /**
     * Nível de autenticação do funcionário.
     */
    public static final byte EMPLOYEE = 1;
}