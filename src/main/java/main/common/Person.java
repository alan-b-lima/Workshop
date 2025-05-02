package main.common;

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
     */
    public Person(String name, String phone, String cpf) {
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
     */
    public void setPhone(String phone) {
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
     * Define o CPF da pessoa.
     * 
     * @param cpf CPF da pessoa.
     */
    public void setCpf(String cpf) {
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