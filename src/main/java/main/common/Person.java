package main.common;

public class Person {

    private String name;
    private Phone phone;
    private Cpf cpf;

    /**
     * Construtor padrão
     */
    public Person() {
        this.phone = new Phone();
        this.cpf = new Cpf();
    }

    /**
     * Contrutor parametrizado
     * 
     * @param name  nome da pessoa
     * @param phone nº de telefone da pessoa
     * @param cpf   documento da pessoa
     * @throws Exception
     */
    public Person(String name, String phone, String cpf) {
        this.name = name;
        this.phone = new Phone(phone);
        this.cpf = new Cpf(cpf);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone.getPhone();
    }

    public void setPhone(String phone) {
        this.phone.setPhone(phone);

    }

    public String getCpf() {
        return cpf.getCpf();
    }

    public void setCpf(String cpf) {
        this.cpf.setCpf(cpf);
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s", name, phone, cpf);
    }
}