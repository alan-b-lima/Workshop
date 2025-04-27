package main.common;

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
     * Construtor que recebe o CPF no formato "xxx.xxx.xxx-xx" ou "xxxxxxxxxxx"
     * 
     * @param cpf CPF no formato "xxx.xxx.xxx-xx" ou "xxxxxxxxxxx"
     */
    public Cpf(String cpf) {
        this.setCpf(cpf);
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
     * Define o CPF a partir de uma string.
     * 
     * @param cpf CPF no formato "xxx.xxx.xxx-xx" ou "xxxxxxxxxxx".
     */
    public void setCpf(String cpf) {
        if (cpf == null) {
            // error
            return;
        }

        if (cpf.matches("^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$") == false) {
            // error
            return;
        }

        String stripedCpf = cpf.replaceAll("[^\\d]", "");

        if (stripedCpf.matches("^(\\d)\\1{10}$") == true) {
            // error
            return;
        }

        int[] check_sum = { 0, 0 };
        for (int i = 10; i >= 2; i--) {
            check_sum[0] += (int) (stripedCpf.charAt(10 - i) - '0') * i;
            check_sum[1] += (int) (stripedCpf.charAt(11 - i) - '0') * i;
        }

        byte[] remainder = { (byte) (check_sum[0] % 11), (byte) (check_sum[1] % 11) };
        if (false
                || (byte) (stripedCpf.charAt(9) - '0') != (remainder[0] < 2 ? 0 : 11 - remainder[0])
                || (byte) (stripedCpf.charAt(10) - '0') != (remainder[1] < 2 ? 0 : 11 - remainder[1])) {
            // error
            return;
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
