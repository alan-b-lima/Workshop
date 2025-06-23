package edu.ajan.model.auth;

/**
 * Enumerações dos níveis de acesso dos usuários da aplicação.
 * 
 * @author Alan Lima
 */
public enum AccessLevel {

    /**
     * Nível de acesso sem restrições.
     */
    SUPER (0),

    /**
     * Nível de acesso para os administradores da oficina.
     */
    ADMIN (1),

    /**
     * Nível de acesso para funcionários da oficina.
     */
    USER  (2),

    /**
     * Nível de acesso para usuários não logados.
     */
    GUEST (3);

    /**
     * Ordinal que denota os níveis de acesso.
     */
    private final int ordinal;

    /**
     * Construtor padrão
     * 
     * @param ordinal ordinal de nível de acesso.
     */
    private AccessLevel(int ordinal) {
        this.ordinal = ordinal;
    }

    /**
     * Verifica se {@code level} é um nível de acesso igual ou superior a
     * {@code this}.
     * 
     * @param level nível de acesso para atestamento.
     * @return {@code true} se o nível de acesso for suficiente, {@code false} caso
     *         contrário.
     */
    public boolean isSufficedBy(AccessLevel level) {
        // Nível de acesso superior é equivalente a ordinal menor.
        return this.ordinal >= level.ordinal;
    }
}
