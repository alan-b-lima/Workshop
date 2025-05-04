package main.workshop.common;

import main.workshop.exception.WorkshopException;

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
     * Construtor que recebe o CPF no formato "xxx.xxx.xxx-xx" ou "xxxxxxxxxxx".
     * 
     * @param cpf CPF no formato "xxx.xxx.xxx-xx" ou "xxxxxxxxxxx".
     * 
     * @throws WorkshopException caso o CPF seja inválido.
     */
    public Cpf(String cpf) throws WorkshopException {
        this.setCpf(cpf);
    }

    /**
     * Retorna o CPF pseudo-anomizado no formato "***.xxx.xxx-**".
     * 
     * @return CPF pseudo-anomizado no formato "***.xxx.xxx-**".
     */
    public String getCpf() {
        return String
                .format("%06x", (this.cpf & 0x000_FFF_FFF_00L) >> 8)
                .replaceAll("(\\d{3})(\\d{3})", "***.$1.$2-**");
    }

    /**
     * Retorna o CPF completo no formato "xxx.xxx.xxx-xx".
     * 
     * @return CPF completo no formato "xxx.xxx.xxx-xx".
     */
    public String getFullCpf() {
        return String
                .format("%011x", this.cpf)
                .replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    /**
     * Retorna o CPF como um número inteiro. Cada 4 bits representa um dígito.
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
        if (cpf.matches("^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$") == false) {
            throw new WorkshopException("CPF inválido - CPF deve ter 11 dígitos!");
        }

        // Remove todos os caracteres não numéricos do CPF.
        String stripedCpf = cpf.replaceAll("[^\\d]", "");

        // Verifica se o CPF é composto por dígitos repetidos
        if (stripedCpf.matches("^(\\d)\\1{10}$") == true) {
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
         * z := (10B + 9C + 8D + 7E + 6F + 5G + 4H + 3I + 2y) % 11
         * w := z < 2 ? 0 : 11 - z
         * 
         * Se x != J ou y != K, CPF inválido.
         */

        int[] check_sum = { 0, 0 };
        for (int i = 10; i >= 2; i--) {
            check_sum[0] += (int) (stripedCpf.charAt(10 - i) - '0') * i;
            check_sum[1] += (int) (stripedCpf.charAt(11 - i) - '0') * i;
        }

        byte[] remainder = { (byte) (check_sum[0] % 11), (byte) (check_sum[1] % 11) };
        if (false
                || (byte) (stripedCpf.charAt(9) - '0') != (remainder[0] < 2 ? 0 : 11 - remainder[0])
                || (byte) (stripedCpf.charAt(10) - '0') != (remainder[1] < 2 ? 0 : 11 - remainder[1])) {

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
     * @return representação textual do objeto.
     */
    @Override
    public String toString() {
        return this.getCpf();
    }
}
