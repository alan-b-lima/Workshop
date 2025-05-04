package main.workshop.auth;

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
