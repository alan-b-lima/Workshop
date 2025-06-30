package edu.ajan.view.tui.shell.exit;

/**
 * Registro para mensagens de saída do shell.
 * 
 * @author Alan Lima
 */
public class ExitMessage {

    private final ExitCode code;
    private final String info;

    public ExitMessage(ExitCode code) {
        this(code, null);
    }

    public ExitMessage(ExitCode code, String message) {
        this.code = code;
        this.info = message;
    }

    public ExitMessage(ExitCode code, String format, Object... args) {
        this(code, String.format(format, args));
    }

    public ExitCode code() {
        return code;
    }

    public String message() {
        return info != null ? info : "";
    }
}