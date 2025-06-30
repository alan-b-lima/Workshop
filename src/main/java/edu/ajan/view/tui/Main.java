package edu.ajan.view.tui;

import edu.ajan.view.tui.shell.Shell;

/**
 * Classe principal para iniciar a aplicação.
 */
public class Main {

    private static final int MAX_RETRY_ATTEMPTS = 10;

    /**
     * Método principal que inicia a aplicação.
     * 
     * @param args argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {

        try {

            Shell shell = new Shell();
            shell.launch();

        } catch (Exception err) {

            System.err.printf("Exceção não capturada: %s\n", err.getMessage());
            // TODO: Salvar o estado

        } catch (Error err) {

            System.err.println("Entrando em modo de recuperação de erro...");
            for (int i = 0; i < MAX_RETRY_ATTEMPTS; i++) {
                try {
                    
                    System.err.printf("Tentativa de recuperação %d de %d...\r", i + 1, MAX_RETRY_ATTEMPTS);
                    // TODO: Tentar salvar o estado em caso fatal
            
                    // if (/* good */) {
                    //     System.err.println("Recuperação bem-sucedida! Terminando...");
                    //     break;
                    // }

                    Thread.sleep(500);

                } catch (Exception | Error _) {
                    continue;
                }
            }

            System.err.println("Falha na recuperação de erro. Estado comprometido. Terminando...");
        }
    }
}
