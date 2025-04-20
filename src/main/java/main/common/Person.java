package main.common;

public abstract class Person {
    private String name;
    private String phone;
    private String cpf;

    /**
     * Construtor padrão
     */
    public Person() {

    }

    /**
     * Contrutor parametrizado
     * 
     * @param name nome da pessoa
     * @param phone nº de telefone da pessoa
     * @param cpf documento da pessoa
     * @throws Exception
     */
    public Person(String name, String phone, String cpf) throws Exception {
        this.name = name;
        this.phone = phone;
        Cpf.isValid(cpf);

        if (Cpf.isValid(cpf) == false) {
            throw new Exception("O CPF informado é inválido!");
        }

        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "%s - %s - %s".formatted(name, phone, cpf);
    }
}
