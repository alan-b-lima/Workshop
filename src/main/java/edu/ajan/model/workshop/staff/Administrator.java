package edu.ajan.model.workshop.staff;

import edu.ajan.model.auth.AccessLevel;

/**
 * Classe que representa um administrador.
 * 
 * @author Juan Pablo
 */
public class Administrator extends StaffMember {

    /**
     * Construtor padrão.
     */
    public Administrator() {
        super();
    }
 
    /**
     * Construtor parametrizado.
     * 
     * @param name        nome do administrador.
     * @param phone       número de telefone do administrador.
     * @param cpf         CPF do administrador.
     * @param salary      salário do administrador.
     * @param password    senha do administrador.
     * @param accessLevel nível de acesso do administrador.
     */
    public Administrator(String name, String phone, String cpf, double salary, String password, AccessLevel accessLevel) {
        super(name, phone, cpf, salary, password, accessLevel);
    }

    /**
     * Construtor de clonagem.
     * 
     * @param administrator administrador a ser clonado.
     */
    protected Administrator(Administrator administrator) {
        super(administrator);
    }

    /**
     * Realiza uma cópia profunda do administrador.
     */
    public Administrator deepClone() {
        return new Administrator(this);
    }

    /**
     * Retorna uma representação em string do administrador.
     * 
     * @return representação em string do administrador.
     */
    public String toString() {
        return super.toString();
    }
}
