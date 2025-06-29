package edu.ajan.model.persistence;

import java.util.HashMap;

import edu.ajan.model.workshop.common.Customer;
import edu.ajan.model.workshop.common.Vehicle;
import edu.ajan.model.workshop.financial.Expense;
import edu.ajan.model.workshop.financial.Invoice;
import edu.ajan.model.workshop.service.Elevator;
import edu.ajan.model.workshop.service.Service;
import edu.ajan.model.workshop.service.ServiceOrder;
import edu.ajan.model.workshop.staff.StaffMember;
import edu.ajan.model.workshop.stock.Product;
import edu.ajan.model.workshop.stock.Shipment;
import edu.ajan.model.workshop.stock.Supplier;

/**
 * Classe que representa o estado da contagem de instâncias.
 * 
 * @author Alan Lima
 */
public class InstanceCountState {

    private final HashMap<String, Integer> instanceCounts;

    private InstanceCountState() {
        this.instanceCounts = new HashMap<>();
    }

    /**
     * Captura o estado atual da contagem de instâncias de várias classes.
     * @return uma nova instância de {@link InstanceCountState} contendo as contagens
     *         de instâncias das classes especificadas.
     */
    public static InstanceCountState capture() {

        return new InstanceCountState()
                .add(Customer.class, Customer.getInstanceCount())
                .add(Vehicle.class, Vehicle.getInstanceCount())
                .add(Expense.class, Expense.getInstanceCount())
                .add(Invoice.class, Invoice.getInstanceCount())
                .add(Elevator.class, Elevator.getInstanceCount())
                .add(Service.class, Service.getInstanceCount())
                .add(ServiceOrder.class, ServiceOrder.getInstanceCount())
                .add(StaffMember.class, StaffMember.getInstanceCount())
                .add(Product.class, Product.getInstanceCount())
                .add(Shipment.class, Shipment.getInstanceCount())
                .add(Supplier.class, Supplier.getInstanceCount());
    }

    /**
     * Restaura a contagem de instâncias a partir do estado fornecido.
     * 
     * @param state o estado da contagem de instâncias a ser restaurado.
     */
    public static void restore(InstanceCountState state) {
        
        Customer.restoreInstanceCount(state);
        Vehicle.restoreInstanceCount(state);
        Expense.restoreInstanceCount(state);
        Invoice.restoreInstanceCount(state);
        Elevator.restoreInstanceCount(state);
        Service.restoreInstanceCount(state);
        ServiceOrder.restoreInstanceCount(state);
        StaffMember.restoreInstanceCount(state);
        Product.restoreInstanceCount(state);
        Shipment.restoreInstanceCount(state);
        Supplier.restoreInstanceCount(state);
    }

    /**
     * Adiciona a contagem de instâncias de uma classe ao estado.
     * 
     * @param clazz a classe cuja contagem de instâncias será adicionada.
     * @param instanceCount a contagem de instâncias da classe.
     * @return o estado atualizado com a nova contagem de instâncias.
     */
    private InstanceCountState add(Class<?> clazz, int instanceCount) {
        instanceCounts.put(clazz.getName(), instanceCount);
        return this;
    }

    /**
     * Obtém a contagem de instâncias de uma classe específica.
     * 
     * @param clazz a classe cuja contagem de instâncias será obtida.
     * @return a contagem de instâncias da classe, ou 0 se a classe não estiver
     *         registrada.
     */
    public int get(Class<?> clazz) {
        return instanceCounts.get(clazz.getName());
    }
}
