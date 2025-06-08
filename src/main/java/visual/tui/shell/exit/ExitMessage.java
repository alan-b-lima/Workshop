package visual.tui.shell.exit;

/**
 * Registro para mensagens de saída do shell.
 * 
 * @author Alan Lima
 */
public record ExitMessage(ExitCode code, String msg) {
    
    /**
     * Default exit message indicating a successful operation.
     */
    public static final ExitMessage DEFAULT = new ExitMessage(ExitCode.SUCCESS, null);
}