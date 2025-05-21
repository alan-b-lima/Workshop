package model.workshop.common;

import model.workshop.exception.WorkshopException;

/**
 * Classe abstrata que representa uma pessoa.
 * 
 * @author Juan Pablo
 */
public abstract class Person {

    /**
     * Nome da pessoa.
     */
    private String name;

    /**
     * Telefone da pessoa.
     */
    private Phone phone;

    /**
     * CPF da pessoa.
     */
    private Cpf cpf;

    /**
     * Construtor padrão.
     */
    public Person() {
        this.phone = new Phone();
        this.cpf = new Cpf();
    }

    /**
     * Construtor que recebe o nome, telefone e CPF da pessoa.
     * 
     * @param name  Nome da pessoa.
     * @param phone Telefone da pessoa.
     * @param cpf   CPF da pessoa.
     * 
     * @throws WorkshopException Caso o telefone ou CPF sejam inválidos.
     */
    public Person(String name, String phone, String cpf) throws WorkshopException {
        this.name = name;
        this.phone = new Phone(phone);
        this.cpf = new Cpf(cpf);
    }

    /**
     * Retorna o telefone da pessoa.
     * 
     * @return telefone da pessoa.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da pessoa.
     * 
     * @param name nome da pessoa.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o telefone da pessoa.
     * 
     * @return telefone da pessoa.
     */
    public String getPhone() {
        return phone.getPhone();
    }

    /**
     * Define o telefone da pessoa.
     * 
     * @param phone telefone da pessoa.
     * 
     * @throws WorkshopException Caso o telefone seja inválido.
     */
    public void setPhone(String phone) throws WorkshopException {
        this.phone.setPhone(phone);
    }

    /**
     * Retorna o CPF pseudo-anonimizado da pessoa.
     * 
     * @return CPF pseudo-anonimizado da pessoa.
     */
    public String getCpf() {
        return cpf.getCpf();
    }

    /**
     * Retorna o CPF completo da pessoa.
     * 
     * @return CPF completo da pessoa.
     */
    public String getFullCpf() {
        return cpf.getFullCpf();
    }

    /**
     * Retorna o CPF numérico da pessoa.
     * 
     * @return CPF numérico da pessoa.
     */
    public long getNumericCpf() {
        return cpf.getNumericCpf();
    }

    /**
     * Define o CPF da pessoa.
     * 
     * @param cpf CPF da pessoa.
     * 
     * @throws WorkshopException Caso o CPF seja inválido.
     */
    public void setCpf(String cpf) throws WorkshopException {
        this.cpf.setCpf(cpf);
    }

    /**
     * Retorna uma representação textual da pessoa.
     * 
     * @return representação textual da pessoa.
     */
    @Override
    public String toString() {
        return String.format("{%s, %s, %s}", name, phone, cpf);
    }
}