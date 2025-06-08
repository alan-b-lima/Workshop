package visual.tui.shell.exit;

/**
 * Registro para mensagens de saída do shell.
 * 
 * @author Alan Lima
 */
public record ExitMessage(ExitCode code, String context) {
    
    public ExitMessage(ExitCode code) {
        this(code, null);
    }

    /**
     * Mensagem de saída padrão.
     */
    public static final ExitMessage DEFAULT = new ExitMessage(ExitCode.SUCCESS);
}