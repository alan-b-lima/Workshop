package main.common;

public class Cpf {

    private long cpf;

    public Cpf() {
        
    }

    public Cpf(String cpf) {
        this.setCpf(cpf);
    }

    public String getFullCpf() {
        return String
                .format("%011x", this.cpf)
                .replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public String getCpf() {
        return String
                .format("%06x", (this.cpf & 0x000_FFF_FFF_00L) >> 8)
                .replaceAll("(\\d{3})(\\d{3})", "***.$1.$2-**");
    }

    public void setCpf(String cpf) {
        if (cpf == null) {
            // error
            return;
        }

        if (cpf.matches("^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$") == false) {
            // error
            return;
        }

        String stripedCpf = cpf.replaceAll("[^\\d]", "");

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

    @Override
    public String toString() {
        return this.getCpf();
    }
}
