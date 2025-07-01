package edu.ajan.model.workshop.service;

import java.util.Comparator;

import edu.ajan.model.exception.WorkshopException;
import edu.ajan.model.persistence.InstanceCountState;
import edu.ajan.model.workshop.date.DateSpan;
import edu.ajan.model.workshop.date.Dates;
import edu.ajan.model.workshop.financial.Invoice;
import edu.ajan.model.workshop.financial.InvoiceDraft;

/**
 * Classe que representa uma ordem de serviço.
 * 
 * @author Alan Lima
 */
public class ServiceOrder {

    /**
     * Comparador de ordem de serviço por data e hora.
     */
    public static final Comparator<ServiceOrder> STATUS_COMPARATOR = new Comparator<>() {
        @Override
        public int compare(ServiceOrder o1, ServiceOrder o2) {
            
            if (o1.status == Status.CANCELLED) {
                return o1.status == o2.status ? 0 : -1;
            }
            
            if (o2.status == Status.CANCELLED) {
                return 1;
            }

            return o1.status.ordinal() - o2.status.ordinal();
        }
    };

    /**
     * Comparador de ordem de serviço por data e hora.
     */
    public static final Comparator<ServiceOrder> DATETIME_COMPARATOR = new Comparator<>() {
        @Override
        public int compare(ServiceOrder o1, ServiceOrder o2) {
            return o1.datetime.compareTo(o2.datetime);
        }
    };

    /**
     * Valor que indica que a ordem de serviço não está associada a nenhum elevador.
     */
    public static final int UNSET_ELEVATOR = -1;

    /**
     * Contador de instâncias.
     */
    private static int instanceCount;

    /**
     * Identificador único da ordem de serviço.
     */
    private final int id;

    /**
     * Identificador do cliente.
     */
    private int customer;

    /**
     * Identificador do veículo.
     */
    private int vehicle;

    /**
     * Identificador do mecânico (funcionário).
     */
    private int mechanic;

    /**
     * Identificador do elevador.
     * 
     * {@link #UNSET_ELEVATOR} indica que a ordem de serviço não está associada a
     * nenhum elevador.
     */
    private int elevator;

    /**
     * Rascunho de nota fiscal associado à ordem de serviço.
     */
    private InvoiceDraft invoice;

    /**
     * Status da ordem de serviço.
     */
    private Status status;

    /**
     * Período de data e hora da ordem de serviço.
     */
    private DateSpan datetime;

    /**
     * Construtor padrão.
     */
    public ServiceOrder() {
        this.id = generateNextId();
        this.elevator = UNSET_ELEVATOR;
        this.invoice = new InvoiceDraft();
        this.status = Status.PENDING;
        this.datetime = new DateSpan(0, 0);
    }

    /**
     * Construtor parametrizado.
     * 
     * @param customer identificador do cliente.
     * @param vehicle  identificador do veículo.
     * @param mechanic identificador do mecânico.
     * @param elevator identificador do elevador.
     * @param status   status da ordem de serviço.
     * @param datetime período de data e hora da ordem de serviço.
     */
    public ServiceOrder(int customer, int vehicle, int mechanic, int elevator, Status status, DateSpan datetime) {
        this.id = generateNextId();
        this.setCustomer(customer);
        this.setVehicle(vehicle);
        this.setMechanic(mechanic);
        this.setElevator(elevator);
        this.setStatus(status);
        this.setDatetime(datetime);
    }

    /**
     * Construtor parametrizado.
     * 
     * @param customer identificador do cliente.
     * @param vehicle  identificador do veículo.
     * @param mechanic identificador do mecânico.
     * @param elevator identificador do elevador.
     * @param datetime período de data e hora da ordem de serviço.
     */
    public ServiceOrder(int customer, int vehicle, int mechanic, int elevator, DateSpan datetime) {
        this(customer, vehicle, mechanic, elevator, Status.PENDING, datetime);
    }

    /**
     * Construtor parametrizado.
     * 
     * @param customer identificador do cliente.
     * @param vehicle  identificador do veículo.
     * @param mechanic identificador do mecânico.
     * @param datetime período de data e hora da ordem de serviço.
     */
    public ServiceOrder(int customer, int vehicle, int mechanic, DateSpan datetime) {
        this(customer, vehicle, mechanic, UNSET_ELEVATOR, Status.PENDING, datetime);
    }

    /**
     * Retorna o identificador da ordem de serviço.
     * 
     * @return identificador da ordem de serviço.
     */
    public int id() {
        return id;
    }

    /**
     * Retorna o identificador do cliente.
     * 
     * @return identificador do cliente.
     */
    public int getCustomer() {
        return customer;
    }

    /**
     * Define o identificador do cliente.
     * 
     * @param customer identificador do cliente.
     */
    public void setCustomer(int customer) {
        this.customer = customer;
    }

    /**
     * Retorna o identificador do veículo.
     * 
     * @return identificador do veículo.
     */
    public int getVehicle() {
        return vehicle;
    }

    /**
     * Define o identificador do veículo.
     * 
     * @param vehicle identificador do veículo.
     */
    public void setVehicle(int vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Retorna o identificador do mecânico.
     * 
     * @return identificador do mecânico.
     */
    public int getMechanic() {
        return mechanic;
    }

    /**
     * Define o identificador do mecânico.
     * 
     * @param mechanic identificador do mecânico.
     */
    public void setMechanic(int mechanic) {
        this.mechanic = mechanic;
    }

    /**
     * Retorna o identificador do elevador.
     * 
     * @return identificador do elevador.
     */
    public int getElevator() {
        return elevator;
    }

    /**
     * Define o identificador do elevador.
     * 
     * @param elevator identificador do elevador.
     */
    public void setElevator(int elevator) {
        this.elevator = elevator;
    }

    /**
     * Retorna o rascunho de nota fiscal associado à ordem de serviço.
     * 
     * @return rascunho de nota fiscal associado à ordem de serviço.
     */
    public InvoiceDraft getInvoice() {
        return invoice;
    }

    /**
     * Define o status da ordem de serviço.
     * 
     * @return status da ordem de serviço.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Define o status da ordem de serviço.
     * 
     * @param status status da ordem de serviço.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Retorna o período de data e hora da ordem de serviço.
     * 
     * @return período de data e hora da ordem de serviço.
     */
    public DateSpan getDatetime() {
        return datetime;
    }

    /**
     * Define o período de data e hora da ordem de serviço.
     * 
     * @param datetime período de data e hora da ordem de serviço.
     */
    public void setDatetime(DateSpan datetime) {
        if (datetime == null) {
            throw new WorkshopException("data e hora não pode ser nulos");
        }

        this.datetime = datetime;
    }

    /**
     * Verifica se a ordem de serviço é conflitante com outra ordem de serviço.
     * 
     * @param other outra ordem de serviço a ser comparada.
     * @return {@code true} se a ordem de serviço for conflitante com outra ordem de
     *         serviço, {@code false} caso contrário.
     */
    public boolean isConflicting(ServiceOrder other) {
        return true
                && this.elevator == other.getElevator()
                && this.getDatetime().intersects(other.getDatetime());
    }

    /**
     * Sela uma ordem de serviço, criando uma nota fiscal finalizada.
     * 
     * @return uma nova nota fiscal finalizada.
     */
    public Invoice seal() {
        return new Invoice(customer, invoice.getProductsAsArray(), invoice.getServicesAsArray(), 0, Dates.now());
    }

    /**
     * Retorna o número total de instâncias criadas.
     * 
     * @return número total de instâncias criadas.
     */
    public static int getInstanceCount() {
        return instanceCount;
    }

    /**
     * Restaura o contador de instâncias a partir do estado salvo.
     * 
     * @param state estado salvo do contador de instâncias.
     */
    public static void restoreInstanceCount(InstanceCountState state) {
        if (state == null) {
            return;
        }

        instanceCount = state.get(ServiceOrder.class);
    }

    /**
     * Incrementa o contador de instâncias.
     */
    private static void incrementInstanceCount() {
        instanceCount++;
    }

    /**
     * Gera o próximo identificador e incrementa o contador de instâncias.
     * 
     * @return próximo identificador único.
     */
    private static int generateNextId() {
        incrementInstanceCount();
        return instanceCount;
    }

    /**
     * Retorna uma representação textural da ordem de serviço.
     * 
     * @return representação textural da ordem de serviço.
     */
    @Override
    public String toString() {
        return String.format("(%d %d %d %d %d %s %s %s)",
                id, customer, vehicle, mechanic, elevator, invoice, status, datetime);
    }
}