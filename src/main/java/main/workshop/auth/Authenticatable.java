package main.workshop.auth;

/**
 * Interface que representa um objeto que pode ser autenticado.
 * 
 * @author Alan Lima
 */
public interface Authenticatable {

    /**
     * Método que retorna o login.
     * 
     * @return login.
     */
    long getIdentification();

    /**
     * Método que retorna a senha.
     * 
     * @return senha.
     */
    long getPassword();

    /**
     * Método que retorna o nível de autenticação.
     * 
     * @return nível de autenticação.
     */
    byte getAuthLevel();
}
