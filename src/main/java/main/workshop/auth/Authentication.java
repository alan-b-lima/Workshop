package main.workshop.auth;

/**
 * Classe que representa a autenticação do usuário.
 * 
 * @author Alan Lima
 */
public final class Authentication {

    /**
     * Construtor padrão privado para evitar instâncias da classe.
     */
    private Authentication() {

    }

    /**
     * Método que autentica o usuário.
     * 
     * @param user     usuário a ser autenticado.
     * @param password senha do usuário.
     * @return nível de autenticação do usuário ou -1 se o usuário não for
     *         autenticado.
     */
    public static final long login(Authenticatable user, long password) {
        if (user == null) {
            return -1;
        }

        if (user.getIdentification() == 0 || user.getPassword() == 0) {
            return -1;
        }

        return user.getAuthLevel();
    }
}
