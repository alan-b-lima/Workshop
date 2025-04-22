package main.common;

/**
 * Classe abstrata que representa uma pessoa.
 * 
 * @author Juan Pablo
 */
public abstract class Person {

    /**
     * Nome de pessoa.
     */
    private String name;

    /**
     * Telefone de pessoa.
     */
    private Phone phone;

    /**
     * CPF de pessoa.
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
     * Contrutor parametrizado.
     * 
     * @param name  nome de pessoa.
     * @param phone telefone de pessoa.
     * @param cpf   CPF de pessoa.
     */
    public Person(String name, String phone, String cpf) {
        this.name = name;
        this.phone = new Phone(phone);
        this.cpf = new Cpf(cpf);
    }

    /**
     * Método que retorna o nome de pessoa.
     * 
     * @return nome de pessoa.
     */
    public String getName() {
        return name;
    }

    /**
     * Método que altera o nome de pessoa.
     * 
     * @param name nome de pessoa.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método que retorna o telefone de pessoa.
     * 
     * @return telefone de pessoa.
     */
    public String getPhone() {
        return phone.getPhone();
    }

    /**
     * Método que altera o telefone de pessoa.
     * 
     * @param phone telefone de pessoa.
     */
    public void setPhone(String phone) {
        this.phone.setPhone(phone);
    }

    /**
     * Método que retorna o CPF pseudo-anomizado de pessoa.
     * 
     * @return CPF pseudo-anomizado de pessoa.
     */
    public String getCpf() {
        return cpf.getCpf();
    }

    /**
     * Método que retorna o CPF completo de pessoa.
     * 
     * @return CPF completo de pessoa.
     */
    public String getFullCpf() {
        return cpf.getFullCpf();
    }

    /**
     * Método que altera o CPF de pessoa.
     * 
     * @param cpf CPF de pessoa.
     */
    public void setCpf(String cpf) {
        this.cpf.setCpf(cpf);
    }

    /**
     * Método que retorna a representação textual do objeto.
     * 
     * @return representação textual do objeto.
     */
    @Override
    public String toString() {
        return String.format("{%s, %s, %s}", name, phone, cpf);
    }
}