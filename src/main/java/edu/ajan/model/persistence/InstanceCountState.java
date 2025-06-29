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

public class InstanceCountState {

    private final HashMap<String, Integer> instanceCounts;

    private InstanceCountState() {
        this.instanceCounts = new HashMap<>();
    }

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

    private InstanceCountState add(Class<?> clazz, int instanceCount) {
        instanceCounts.put(clazz.getName(), instanceCount);
        return this;
    }

    public int get(Class<?> clazz) {
        return instanceCounts.get(clazz.getName());
    }
}
