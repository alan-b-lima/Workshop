package visual.tui.shell.exit;

/**
 * Enumeração que define os códigos de saída dos comandos.
 * 
 * @author Alan Lima
 */
public enum ExitCode {

    /**
     * Sucesso na execução do comando.
     */
    SUCCESS,

    /**
     * Sucesso e saída de contexto.
     */
    EXIT_CONTEXT,

    /**
     * Sucesso e entrada em um novo contexto.
     */
    ENTER_CONTEXT,

    /**
     * Sucesso e saída do shell.
     */
    EXIT_SHELL,
    
    /**
     * Falha na execução do comando.
     */
    FAILURE,

    /**
     * Argumento inválido fornecido ao comando.
     */
    INVALID_ARGUMENT,

    /**
     * Comando não encontrado.
     */
    COMMAND_NOT_FOUND,

    /**
     * Permissão negada para executar o comando.
     */
    PERMISSION_DENIED;
}