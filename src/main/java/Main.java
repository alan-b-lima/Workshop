import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import model.workshop.stock.Part;
import model.workshop.stock.PartKind;
import model.workshop.stock.Stock;
import model.workshop.stock.StockHistory;

public class Main {

    public static void main(String[] args) {
        
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        Stock stock = Stock.stock();
        System.out.printf("Estoque vazio:\n%s\n\n", stock);

        // Inicializa o estoque com alguns tipos de peças e partes
        {
            stock.addPartKind(new PartKind("pneu"));
            stock.addPartKind(new PartKind("bateria"));
            stock.addPartKind(new PartKind("óleo de motor", "L"));
            
            stock.addPart(new Part(stock.getPartKind("pneu").id(), 150.67, 10));
            stock.addPart(new Part(stock.getPartKind("bateria").id(), 250.23, 5));
            
            System.out.printf("Estoque inicializado:\n%s\n\n", stock);
        }
        
        // Salva o estado do estoque
        {
            StockHistory.history().record();
            System.out.printf("Estado do estoque salvo!\n\n");
        }
        
        // Adiciona mais peças ao estoque
        {
            stock.addPart(new Part(stock.getPartKind("pneu").id(), 150.67, 5));
            stock.addPart(new Part(stock.getPartKind("bateria").id(), 250.23, 3));
            stock.addPart(new Part(stock.getPartKind("óleo de motor").id(), 19.32, 50));

            System.out.printf("Estoque após adição de peças:\n%s\n\n", stock);
        }

        // Mudança indesejada
        {
            stock.removePart(stock.getPartKind("bateria").id());

            System.out.printf("Estoque após remoção [indesejada] das peças do tipo bateria:\n%s\n\n", stock);
        }

        // Desfaz a última mudança indesejada
        {
            StockHistory.history().undo();
            System.out.printf("Estado do estoque revertido!\n\n");

            System.out.printf("Estoque após reverter para a última transação:\n%s\n\n", stock);

            // Note que o estoque foi restaurado para o estado antes da remoção do pneu.
            // Porém, a adição de peças após o snapshot é perdida, pois a transação
            // não inclui essas mudanças.
        }
    }
}
