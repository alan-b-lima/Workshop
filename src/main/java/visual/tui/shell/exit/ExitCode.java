package visual.tui.shell.exit;

public enum ExitCode {
    SUCCESS,
    EXIT_CONTEXT,
    ENTER_CONTEXT,
    
    FAILURE,
    INVALID_ARGUMENT,
    COMMAND_NOT_FOUND,
    PERMISSION_DENIED;
}