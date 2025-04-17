package main.common;

/**
 * Utility class for CPF validation and formatting
 */
public final class Cpf {

    private Cpf() {

    }

    /**
     * Regular expression pattern for CPF validation
     * The pattern allows for formatted (xxx.xxx.xxx-xx) CPF's
     */
    private static final String CPF_PATTERN = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";

    /**
     * Verify the validity of a CPF
     * 
     * @param cpf the CPF to be validated
     * @return    `true` if the CPF is valid, `false` otherwise
     */
    public static boolean isValid(String cpf) {
        if (cpf == null) {
            return false;
        }

        if (!cpf.matches(CPF_PATTERN)) {
            return false;
        }

        byte[] digits = {
            (byte) (cpf.charAt(0) - '0'),
            (byte) (cpf.charAt(1) - '0'),
            (byte) (cpf.charAt(2) - '0'),
            
            (byte) (cpf.charAt(4) - '0'),
            (byte) (cpf.charAt(5) - '0'),
            (byte) (cpf.charAt(6) - '0'),
            
            (byte) (cpf.charAt(8) - '0'),
            (byte) (cpf.charAt(9) - '0'),
            (byte) (cpf.charAt(10) - '0'),

            (byte) (cpf.charAt(12) - '0'),
            (byte) (cpf.charAt(13) - '0')
        };

        int[] check_sum = { 0, 0 };
        for (int i = 0; i < 9; i++) {
            check_sum[0] += (int) digits[i + 0] * (10 - i);
            check_sum[1] += (int) digits[i + 1] * (10 - i);
        }

        byte[] remainder = { (byte)(check_sum[0] % 11), (byte)(check_sum[1] % 11) };
        return true
                && digits[+9] == (remainder[0] < 2 ? 0 : 11 - remainder[0])
                && digits[10] == (remainder[1] < 2 ? 0 : 11 - remainder[1]);
    }

    /**
     * Format a CPF string to the standard format (xxx.xxx.xxx-xx)
     * @param cpf the CPF to be formatted
     * @return    the formatted CPF
     */
    public static String format(String cpf) {
        if (cpf == null) {
            return null;
        }

        return cpf.replaceAll("(\\d{3})\\.?(\\d{3})\\.?(\\d{3})\\-?(\\d{2})", "$1.$2.$3-$4");
    }
}
