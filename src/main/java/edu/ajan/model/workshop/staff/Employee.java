package edu.ajan.model.workshop.staff;

import java.util.TreeSet;

import edu.ajan.model.auth.AccessLevel;
import edu.ajan.model.exception.WorkshopException;
import edu.ajan.model.workshop.common.Person;
import edu.ajan.model.workshop.date.DateSpan;
import edu.ajan.model.workshop.date.Dates;

/**
 * Classe que representa um funcionário.
 */
public class Employee extends StaffMember {

    /**
     * Denota invalidez de timestamp.
     */
    public static final long UNSET_TIME = -1L;

    /**
     * Turnos (pontos) do funcionário.
     */
    private TreeSet<DateSpan> shifts;

    /**
     * Começo do turno atual não terminado.
     * 
     * {@link #UNSET_TIME} caso não haja um turno em aberto.
     */
    private long openShift;

    /**
     * Construtor padrão.
     */
    public Employee() {
        super();
        this.shifts = new TreeSet<>();
        this.openShift = UNSET_TIME;
    }

    /**
     * Construtor parametrizado.
     * 
     * @param name        nome do funcionário.
     * @param phone       número de telefone do funcionário.
     * @param cpf         CPF do funcionário.
     * @param salary      salário do funcionário.
     * @param password    senha do funcionário.
     * @param accessLevel nível de acesso do funcionário.
     * 
     * @throws WorkshopException se algum dos parâmetros for inválido.
     */
    public Employee(String name, String phone, String cpf, double salary, String password, AccessLevel accessLevel) {
        super(name, phone, cpf, salary, password, accessLevel);
        this.shifts = new TreeSet<>();
        this.openShift = UNSET_TIME;
    }

    /**
     * Construtor de clonagem.
     * 
     * @param employee funcionário a ser clonado.
     * 
     * @throws WorkshopException se algum dos parametros for inválido.
     */
    protected Employee(Employee employee) {
        super(employee);
        this.shifts = new TreeSet<>(employee.shifts); // DateSpan's são imutáveis, cloná-los é desnecessário.
        this.openShift = employee.openShift;
    }

    /**
     * Retorna o turno atual não terminado.
     * 
     * @return turno atual não terminado.
     */
    public Iterable<DateSpan> getShifts() {
        return shifts;
    }

    /**
     * Retorna todos os turnos que intersectam com um espaço de tempo.
     * 
     * @param start começo do espaço de tempo.
     * @param end   fim do espaço de tempo.
     * @return iterável de turnos.
     */
    public Iterable<DateSpan> getShifts(long start, long end) {
        DateSpan span = new DateSpan(start, end);
        return shifts.stream().filter(shift -> shift.intersects(span)).toList();
    }

    /**
     * Adiciona um espaço de tempo.
     * 
     * @param shift espaço de tempo a ser adicionado.
     * 
     * @throws WorkshopException se o turno for nulo ou intersectar com algum outro
     *                           turno já existente.
     */
    public void addShift(DateSpan shift) {

        if (shift == null) {
            throw new WorkshopException("turno não pode ser nulo");
        }

        if (shift.isEmpty()) {
            return;
        }

        for (DateSpan span : shifts) {
            if (span.intersects(shift)) {
                throw new WorkshopException("turnos não podem se sobrepor");
            }
        }

        shifts.add(shift);
    }

    /**
     * Remove turnos num espaço de tempo.
     * 
     * @param start começo do espaço de tempo.
     * @param end   fim do espaço de tempo.
     */
    public void removeShifts(long start, long end) {

        DateSpan span = new DateSpan(start, end);
        if (span.isEmpty()) {
            return;
        }

        shifts.removeIf(shift -> shift.intersects(span));
    }

    /**
     * Retorna a abertura do turno.
     * 
     * @return abertura do turno.
     */
    public long getOpenShift() {
        return openShift;
    }

    /**
     * Define a abertura do turno.
     * 
     * @param openShift abertura do turno.
     */
    public void setOpenShift(long openShift) {
        this.openShift = openShift;
    }

    /**
     * Registra o começo ou fim de um turno, baseado em informação passada.
     * 
     * @param timestamp momento do registro.
     * 
     * @throws WorkshopException caso o turno registrado intersectar com algum outro
     *                           turno já existente.
     */
    public void registerShift(long timestamp) {
        if (this.openShift == UNSET_TIME) {
            this.openShift = timestamp;
            return;
        }

        addShift(new DateSpan(this.openShift, timestamp));
        this.openShift = UNSET_TIME;
    }

    /**
     * Cria um clone profundo do funcionário.
     * 
     * @return a instânca clonada do funcionário.
     */
    @Override
    public Person deepClone() {
        return new Employee(this);
    }

    /**
     * Retorna uma representação textual do funcionário.
     * 
     * @return representação textual do funcionário.
     */
    @Override
    public String toString() {
        String member = super.toString();
        return String.format("(%s %s [%s))",
                member.substring(1, member.length() - 1),
                shifts, openShift != UNSET_TIME ? Dates.formatAsDateTime(openShift) : "");
    }
}
