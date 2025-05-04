package main.util;

/**
 * Classe utilitária para manipulação de senhas.
 * 
 * @author Alan Lima
 */
public final class WPassword {

    /**
     * Classe utilitária para manipulação de senhas.
     */
    private WPassword() {

    }

    /**
     * Cria um hash a partir da senha do usuário.
     * 
     * @param password senha a ser convertida em hash.
     * @return hash da senha.
     */
    public static long hash(String password) {
        return (long) password.hashCode() * 0x1F1F1F1F_1F1F1F1FL;
    }

    /**
     * Valida a senha do usuário.
     * 
     * @param password senha a ser validada.
     * @return {@code true} se a senha for válida, {@code false} caso contrário.
     */
    public static boolean validade(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }

        if (password.length() < 5) {
            return false;
        }

        return true;
    }
}
