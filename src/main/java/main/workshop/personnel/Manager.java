package main.workshop.personnel;

import main.workshop.common.Person;
import main.workshop.exception.WorkshopException;

/**
 * Classe que representa um gerente de oficina.
 * 
 * @author Alan Lima
 */
public class Manager extends Person {

    /**
     * Pro labore do gerente.
     */
    private double proLabore;

    /**
     * Construtor padrão.
     */
    public Manager() {

    }

    /**
     * Construtor que recebe o nome, telefone, CPF e pro labore do gerente.
     * 
     * @param name      nome do gerente.
     * @param phone     telefone do gerente.
     * @param cpf       CPF do gerente.
     * @param proLabore pro labore do gerente.
     * 
     * @throws WorkshopException
     */
    public Manager(String name, String phone, String cpf, double proLabore) throws WorkshopException {
        super(name, phone, cpf);
        this.setProLabore(proLabore);
    }

    /**
     * Retorna o pro labore do gerente.
     * 
     * @return pro labore do gerente.
     */
    public double getProLabore() {
        return proLabore;
    }

    /**
     * Define o pro labore do gerente.
     * 
     * @param proLabore pro labore do gerente.
     */
    public void setProLabore(double proLabore) {
        this.proLabore = proLabore;
    }

    /**
     * Retorna uma representação textual do gerente.
     * 
     * @return representação textual do gerente.
     */
    @Override
    public String toString() {
        return String.format("{%s, %s, %s, %.2f}", this.getName(), this.getPhone(), this.getCpf(), this.getProLabore());
    }
}
