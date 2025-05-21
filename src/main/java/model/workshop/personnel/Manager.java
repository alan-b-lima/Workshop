package model.workshop.personnel;

import model.util.WPassword;
import model.workshop.auth.AuthLevel;
import model.workshop.auth.Authenticatable;
import model.workshop.common.Person;
import model.workshop.exception.WorkshopException;

/**
 * Classe que representa um gerente de oficina.
 * 
 * @author Alan Lima
 */
public class Manager extends Person implements Authenticatable {

    /**
     * Pro labore do gerente.
     */
    private double proLabore;

    /**
     * Senha do gerente.
     */
    private long password;

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
     * @throws WorkshopException caso o telefone ou CPF sejam inválidos.
     */
    public Manager(String name, String phone, String cpf, double proLabore, String password) throws WorkshopException {
        super(name, phone, cpf);
        this.setProLabore(proLabore);
        this.setPassword(password);
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
     * Retorna a senha do gerente.
     * 
     * @return senha do gerente.
     */
    @Override
    public long getPassword() {
        return password;
    }

    /**
     * Retorna a senha do gerente.
     * 
     * @return senha do gerente.
     */
    public void setPassword(String password) {
        this.password = WPassword.hash(password);
    }

    /**
     * Retorna o CPF do gerente como um número.
     */
    @Override
    public long getIdentification() {
        return this.getNumericCpf();
    }

    /**
     * Retorna o nível de autenticação do gerente.
     */
    @Override
    public byte getAuthLevel() {
        return AuthLevel.EMPLOYEE;
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
