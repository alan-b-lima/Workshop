package main.workshop.personnel;

import java.util.ArrayList;

import main.util.WPassword;
import main.workshop.auth.AuthLevel;
import main.workshop.auth.Authenticatable;
import main.workshop.common.DateSpan;
import main.workshop.common.Person;
import main.workshop.exception.WorkshopException;

/**
 * Classe que representa um funcionário.
 * 
 * @author Juan Pablo
 */
public class Employee extends Person implements Authenticatable {

    /**
     * Lista de turnos do funcionário.
     */
    private ArrayList<DateSpan> shifts;

    /**
     * Salário do funcionário.
     */
    private double salary;

    /**
     * Senha do funcionário.
     */
    private long password;

    /**
     * Construtor padrão.
     */
    public Employee() {
        this.shifts = new ArrayList<>();
    }
    
    /**
     * Construtor parametrizado que recebe o nome, telefone, CPF e salário do
     * funcionário.
     * 
     * @param name   nome do funcionário.
     * @param phone  telefone do funcionário.
     * @param cpf    CPF do funcionário.
     * @param salary salário do funcionário.
     * 
     * @throws WorkshopException caso o telefone ou CPF sejam inválidos.
     */
    public Employee(String name, String phone, String cpf, double salary, String password) throws WorkshopException {
        super(name, phone, cpf);
        this.shifts = new ArrayList<>();
        this.salary = salary;
        this.password = WPassword.hash(password);
    }

    /**
     * Retorna a lista de turnos do funcionário.
     * 
     * @return lista de turnos do funcionário.
     */
    public ArrayList<DateSpan> getShifts() {
        return shifts;
    }

    /**
     * Retorna os turnos do funcionário dentro do intervalo de tempo especificado.
     * 
     * @param start timestamp UNIX que representa o início do intervalo.
     * @param end   timestamp UNIX que representa o fim do intervalo.
     * @return lista de turnos dentro do intervalo especificado.
     */
    public ArrayList<DateSpan> getShifts(long start, long end) {
        ArrayList<DateSpan> shifts = new ArrayList<>();

        for (DateSpan shift : this.shifts) {
            // verifica o ponto na lista
            if (shift.getStart() >= start && shift.getEnd() <= end) {
                // adiciona a lista local se a condição for satisfeita
                shifts.add(shift);
            }
        }

        // retorna os pontos
        return shifts;
    }

    /**
     * Retorna o turno mais recente do funcionário.
     * 
     * @return turno mais recente do funcionário.
     */
    public DateSpan getRecentShift() {
        return shifts.getLast();
    }

    /**
     * Adiciona um turno à lista de turnos do funcionário.
     * 
     * @param shift turno a ser adicionado.
     */
    public void addShift(DateSpan shift) {
        // se tiver vazia apenas adiciona o turno
        if (shifts.isEmpty()) {
            shifts.add(shift);
        }

        // itera sobre a lista para encontrar a posição correta do final para o início
        for (int i = shifts.size() - 1; i >= 0; i--) {
            if (shift.getStart() >= shifts.get(i).getStart()) {
                // adiciona na posição correta
                shifts.add(i + 1, shift);
                return;
            }
        }

        // se o turno for mais recente que os outros adiciona no inicio
        shifts.add(0, shift);
    }

    /**
     * Retorna o salário do funcionário.
     * 
     * @return salário do funcionário.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Define o salário do funcionário.
     *
     * @param salary salário do funcionário.
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    @Override
    public long getIdentification() {
        return this.getNumericCpf();
    }

    @Override
    public long getPassword() {
        return password;
    }

    @Override
    public byte getAuthLevel() {
        return AuthLevel.EMPLOYEE;
    }

    /**
     * Retorna uma representação textual do funcionário.
     * 
     * @return representação textual do funcionário.
     */
    @Override
    public String toString() {
        return String.format("{%s, %s, %s, %.2f}", this.getName(), this.getPhone(), this.getCpf(), this.getSalary());
    }
}
