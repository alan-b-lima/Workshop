import model.workshop.stock.PartKind;
import model.workshop.stock.Stock;
import model.workshop.stock.Supplier;

public class Main {

    public static void main(String[] args) {

        Stock stock = Stock.stock();

        stock.addPartKind(new PartKind("engrenagem"));
        stock.addPartKind(new PartKind("óleo", "L"));

        stock.addSupplier(new Supplier("Fabinho Peças", "00.623.904/0001-73"));
    }
}
