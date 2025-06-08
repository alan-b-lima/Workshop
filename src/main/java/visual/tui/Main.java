package visual.tui;

import visual.tui.shell.Shell;

/**
 * Classe principal para iniciar a aplicação.
 */
public class Main {

    /**
     * Método principal que inicia a aplicação.
     * 
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {

        Shell shell = new Shell();
        shell.launch();
        
    }
}
