package model.workshop.common;

import java.util.regex.Pattern;

import model.exception.WorkshopException;

/**
 * Classe que representa um CPF.
 * 
 * @author Alan Lima
 */
public class Cpf {

    /**
     * Representa um CPF. Cada dígito do telefone é armazenado em 4 bits,
     * totalizando 40 bits (10 dígitos). Assim, essa variável funciona com um vetor
     * de dígitos decimais.
     */
    private long cpf;

    /**
     * Construtor padrão
     */
    public Cpf() {

    }

    /**
     * Construtor parametrizado.
     * 
     * @param cpf CPF no formato "xxx.xxx.xxx-xx" ou "xxxxxxxxxxx".
     * 
     * @throws WorkshopException caso o CPF seja inválido.
     */
    public Cpf(String cpf) throws WorkshopException {
        this.setCpf(cpf);
    }

    /**
     * Máscara para pseudo-anonimização do CPF.
     */
    private static final long PSEUDO_ANOMIZATION_MASK = 0x000_FFF_FFF_00L;

    /**
     * Padrão regex para capturar 2 grupos 3 dígitos.
     */
    private static final Pattern PSEUDO_ANOMIZATION_PATTERN = Pattern.compile("(\\d{3})(\\d{3})");

    /**
     * Padrão regex para capturar 3 grupos de 3 dígitos e 1 grupo de 2 dígitos.
     */
    private static final Pattern FULL_CPF_PATTERN = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");

    /**
     * Padrão regex para validar superconjunto do formato válido de CPF.
     */
    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}");

    /**
     * Padrão regex para remover caracteres não numéricos do CPF.
     */
    private static final Pattern STRIP_CPF_PATTERN = Pattern.compile("[^\\d]");

    /**
     * Padrão regex para detectar CPF composto por dígitos repetidos.
     */
    private static final Pattern REPEATED_DIGITS_PATTERN = Pattern.compile("(\\d)\\1{10}");

    /**
     * Retorna o CPF pseudo-anomizado.
     * 
     * @return CPF pseudo-anomizado no formato "***.xxx.xxx-**".
     */
    public String getCpf() {
        return PSEUDO_ANOMIZATION_PATTERN
                .matcher(String.format("%06x", (this.cpf & PSEUDO_ANOMIZATION_MASK) >> 8))
                .replaceAll("***.$1.$2-**");
    }

    /**
     * Retorna o CPF completo.
     * 
     * @return CPF completo no formato "xxx.xxx.xxx-xx".
     */
    public String getFullCpf() {
        return FULL_CPF_PATTERN
                .matcher(String.format("%011x", this.cpf))
                .replaceAll("$1.$2.$3-$4");
    }

    /**
     * Retorna o CPF como um número inteiro. Cada 4 bits representam um dígito.
     * 
     * @return CPF como um número inteiro.
     */
    public long getNumericCpf() {
        return this.cpf;
    }

    /**
     * Define o CPF a partir de uma string.
     * 
     * @param cpf CPF no formato "xxx.xxx.xxx-xx" ou "xxxxxxxxxxx".
     * 
     * @throws WorkshopException caso o CPF seja inválido.
     */
    public void setCpf(String cpf) throws WorkshopException {

        // Verifica se o CPF é nulo ou vazio.
        if (cpf == null) {
            throw new WorkshopException("CPF inválido - CPF não pode ser nulo!");
        }

        // Verifica se o CPF pertence a um superconjunto do formato padrão.
        if (CPF_PATTERN.matcher(cpf).matches() == false) {
            throw new WorkshopException(
                    "CPF inválido - CPF deve estar no formato \"xxx.xxx.xxx-xx\" ou \"xxxxxxxxxxx\"!");
        }

        // Remove todos os caracteres não numéricos do CPF.
        String stripedCpf = STRIP_CPF_PATTERN.matcher(cpf).replaceAll("");

        // Verifica se o CPF é composto por dígitos repetidos
        if (REPEATED_DIGITS_PATTERN.matcher(stripedCpf).matches() == true) {
            throw new WorkshopException("CPF inválido - CPF não pode ser composto por dígitos repetidos!");
        }

        /*
         * Algoritmo de validação do CPF:
         * 
         * Considere o CPF ABC.DEF.GHI-JK, sendo A, B, C, D, E, F, G, H, I, J e K
         * digitos decimais de 0 a 9.
         * 
         * x := (10A + 9B + 8C + 7D + 6E + 5F + 4G + 3H + 2I) % 11
         * y := x < 2 ? 0 : 11 - x
         * 
         * z := (10B + 9C + 8D + 7E + 6F + 5G + 4H + 3I + 2J) % 11
         * w := z < 2 ? 0 : 11 - z
         * 
         * Se x != J ou y != K, CPF inválido.
         */

        int check_sum_0th = 0;
        int check_sum_1st = 0;
        for (int i = 10; i >= 2; i--) {
            check_sum_0th += (int) (stripedCpf.charAt(10 - i) - '0') * i;
            check_sum_1st += (int) (stripedCpf.charAt(11 - i) - '0') * i;
        }

        int remainder_0th = check_sum_0th % 11;
        int remainder_1st = check_sum_1st % 11;
        if (false
                || (int) (stripedCpf.charAt(9 ) - '0') != (remainder_0th < 2 ? 0 : 11 - remainder_0th)
                || (int) (stripedCpf.charAt(10) - '0') != (remainder_1st < 2 ? 0 : 11 - remainder_1st)) {

            throw new WorkshopException("CPF inválido - digitos verificadores inválidos!");
        }

        this.cpf = 0L;
        for (int i = 0; i < stripedCpf.length(); i++) {
            this.cpf |= (long) (stripedCpf.charAt(i) - '0') << ((10 - i) << 2);
        }
    }

    /**
     * Retorna a representação textual do objeto.
     * 
     * @return representação textual do CPF no formato "***.xxx.xxx-**".
     */
    @Override
    public String toString() {
        return this.getCpf();
    }
}