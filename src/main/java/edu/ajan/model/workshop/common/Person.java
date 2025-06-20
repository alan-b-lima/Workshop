package edu.ajan.model.workshop.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.ajan.model.custom.DeepClonable;
import edu.ajan.model.custom.WorkshopObject;
import edu.ajan.model.exception.WorkshopException;

/**
 * Classe abstrata que representa uma pessoa.
 * 
 * @author Alan Lima
 * @author Juan Pablo
 */
public abstract class Person extends WorkshopObject implements DeepClonable<Person> {

    /**
     * Padrão RegEx que tanto casa um superconjunto das formatações válidas de
     * números de telefone celular no Brasil, quanto pode dividir os dígitos do
     * número de telefone nos grupos apropriados para formatação.
     */
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\(?(\\d{2})\\)? ?9?(\\d{4})[- ]?(\\d{4})");

    /**
     * Padrão RegEx que tanto casa um superconjunto das formatações válidas de CPF
     * quanto pode dividir os dígitos do CPF nos grupos apropriados para formatação.
     */
    private static final Pattern CPF_PATTERN = Pattern.compile("(\\d{3})\\.?(\\d{3})\\.?(\\d{3})-?(\\d{2})");

    /**
     * Padrão RegEx que casa uma string com 11 dígitos iguais.
     */
    private static final Pattern ALL_DIGITS_EQUAL_PATTERN = Pattern.compile("(\\d)\\1{10}");

    /**
     * Padrão de substituição para número de telefone que tenha sido agrupado por
     * {@link #PHONE_PATTERN}
     */
    private static final String STANDARD_PHONE_MASK = "($1) 9$2-$3";

    /**
     * Padrão de substituição para CPF para remover dígitos não decimais, o CPF deve
     * ter sido agrupado por {@link #CPF_PATTERN}.
     */
    private static final String CPF_NON_DIGIT_REMOVAL_MASK = "$1$2$3$4";

    /**
     * Padrão de substituição para CPF pseudo-anonimizado que tenha sido agrupado
     * por {@link #CPF_PATTERN}.
     */
    private static final String PSEUDO_CPF_ANONIMIZATION_MASK = "$1.***.***-$4";

    /**
     * Padrão de substituição para CPF padrão (completo) que tenha sido agrupado por
     * {@link #CPF_PATTERN}.
     */
    private static final String STANDARD_FULL_CPF_MASK = "$1.$2.$3-$4";

    /**
     * Nome da pessoa.
     */
    private String name;

    /**
     * Número de telefone da pessoa.
     * 
     * Segue estritamente o formato (xx) 9xxxx-xxxx.
     */
    private String phone;

    /**
     * CPF da pessoa.
     * 
     * Segue estritamente o formato xxx.xxx.xxx-xx.
     */
    private String cpf;

    /**
     * Construtor padrão.
     */
    public Person() {
        this.name = "";
        this.phone = "(00) 90000-0000";
        this.cpf = "000.000.000-00";
    }

    /**
     * Construtor parametrizado.
     * 
     * @param name  nome da pessoa.
     * @param phone número de telefone da pessoa.
     * @param cpf   CPF da pessoa.
     * 
     * @throws WorkshopException se algum dos parâmetros for inválido.
     */
    public Person(String name, String phone, String cpf) {
        this.setName(name);
        this.setPhone(phone);
        this.setCpf(cpf);
    }

    /**
     * Construtor de clonagem.
     * 
     * @param person a pessoa a ser clonada.
     */
    protected Person(Person person) {
        this.name = person.name;
        this.phone = person.phone;
        this.cpf = person.cpf;
    }

    /**
     * Retorna o nome da pessoa.
     * 
     * @return nome da pessoa.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da pessoa.
     * 
     * @param name nome da pessoa.
     * 
     * @throws WorkshopException se o nome for nulo.
     */
    public void setName(String name) {
        if (name == null) {
            throw new WorkshopException("nome não pode ser nulo");
        }

        this.name = name;
    }

    /**
     * Retorna o número de telefone da pessoa.
     * 
     * @return número de telefone da pessoa.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Define o número de telefone da pessoa.
     * 
     * @param phone número de telefone da pessoa.
     * 
     * @throws WorkshopException se o número de telefone for nulo ou fora do formato
     *                           definido.
     */
    public void setPhone(String phone) {

        if (phone == null) {
            throw new WorkshopException("telefone não pode ser nulo");
        }

        Matcher phoneMatcher = PHONE_PATTERN.matcher(phone);

        if (!phoneMatcher.matches()) {
            throw new WorkshopException("telefone deve seguir o formato (xx) 9xxxx-xxxx");
        }

        String formattedPhone = phoneMatcher.replaceAll(STANDARD_PHONE_MASK);
        this.phone = formattedPhone;
    }

    /**
     * Retorna o CPF pseudo-anonimizado da pessoa.
     * 
     * @return CPF pseudo-anonimizado da pessoa.
     */
    public String getCpf() {
        return CPF_PATTERN.matcher(this.cpf).replaceAll(PSEUDO_CPF_ANONIMIZATION_MASK);
    }

    /**
     * Retorna o CPF completo da pessoa, sem formatação.
     * 
     * @return CPF completo da pessoa.
     */
    public String getFullCpf() {
        return cpf;
    }

    /**
     * Define o CPF da pessoa. O CPF deve ser válido.
     * 
     * <p> Algoritmo de validação do CPF:
     * 
     * <p> Considere o CPF {@code ABC.DEF.GHI-JK}, sendo {@code A}, {@code B},
     * {@code C}, {@code D}, {@code E}, {@code F}, {@code G}, {@code H}, {@code I},
     * {@code J} e {@code K} dígitos decimais de 0 a 9.
     * 
     * <p>
     * 
     * <p> {@code x := (10A + 9B + 8C + 7D + 6E + 5F + 4G + 3H + 2I) mod 11}
     * <p> {@code y := x < 2 ? 0 : 11 - x}
     * 
     * <p> {@code z := (10B + 9C + 8D + 7E + 6F + 5G + 4H + 3I + 2J) mod 11}
     * <p> {@code w := z < 2 ? 0 : 11 - z}
     * 
     * <p> Para o CPF ser válido, é necessário e sufiente que {@code y = J},
     * {@code w = K} e ao menos um dígito ser diferente de outro.
     * 
     * @param cpf CPF da pessoa.
     * 
     * @throws WorkshopException se o CPF for nulo, fora do padrão, tiver todos os
     *                           dígitos iguais ou tiver os dígitos verificadores
     *                           inválidos.
     */
    public void setCpf(String cpf) {

        if (cpf == null) {
            throw new WorkshopException("CPF não pode ser nulo");
        }

        Matcher cpfMatcher = CPF_PATTERN.matcher(cpf);

        if (!cpfMatcher.matches()) {
            throw new WorkshopException("CPF deve seguir o formato xxx.xxx.xxx-xx");
        }

        String stripedCpf = cpfMatcher.replaceAll(CPF_NON_DIGIT_REMOVAL_MASK);

        if (ALL_DIGITS_EQUAL_PATTERN.matcher(stripedCpf).matches()) {
            throw new WorkshopException("CPF não pode ter todos os dígitos iguais");
        }

        int checkSum1st = 0;
        int checkSum2nd = 0;

        for (int i = 10; i >= 2; i--) {
            checkSum1st += (int) (stripedCpf.charAt(10 - i) - '0') * i;
            checkSum2nd += (int) (stripedCpf.charAt(11 - i) - '0') * i;
        }

        int remainder1st = checkSum1st % 11;
        int remainder2nd = checkSum2nd % 11;

        int correct1st = remainder1st < 2 ? 0 : 11 - remainder1st;
        int correct2nd = remainder2nd < 2 ? 0 : 11 - remainder2nd;

        // Se o primeiro dígito verificador está incorreto, o segundo dígito verificador obtido não é confiável
        if (correct1st != (int) (stripedCpf.charAt(9) - '0')) {
            throw new WorkshopException("CPF com dígitos verificadores inválidos, começa com %d", correct1st);
        }

        if (correct2nd != (int) (stripedCpf.charAt(10) - '0')) {
            throw new WorkshopException("CPF com dígitos verificadores inválidos, tente %d%d", correct1st, correct2nd);
        }

        String formattedCpf = cpfMatcher.replaceAll(STANDARD_FULL_CPF_MASK);
        this.cpf = formattedCpf;
    }

    /**
     * Retorna o componente interior da tupla para anular a necessidade de remover
     * os parênteses em subclasses.
     * 
     * @return componente inteirior da tupla.
     */
    protected String superString() {
        return String.format("\"%s\" %s %s", name, phone, getCpf());
    }

    /**
     * Retorna uma representação textual da pessoa.
     * 
     * @return representação textual da pessoa.
     */
    @Override
    public String toString() {
        return "(" + superString() + ")";
    }
}