package model.workshop.common;

import java.util.regex.Pattern;

import model.exception.WorkshopException;

/**
 * Classe que representa um telefone.
 * 
 * @author Alan Lima
 */
public class Phone {

    /**
     * Representa um número de telefone. Cada dígito do telefone é armazenado em 4
     * bits, totalizando 40 bits (10 dígitos). Assim, essa variável funciona com um
     * vetor de dígitos decimais.
     */
    private long phone;

    /**
     * Construtor padrão.
     */
    public Phone() {

    }

    /**
     * Construtor que recebe o telefone.
     * 
     * @param phone telefone no formato "(xx) 9xxxx-xxxx".
     * 
     * @throws WorkshopException caso o telefone seja inválido.
     */
    public Phone(String phone) throws WorkshopException {
        this.setPhone(phone);
    }

    /**
     * Padrão regex para capturar 1 grupo de 2 dígitos, 2 grupos de 4 dígitos.
     */
    private static final Pattern STRIGIFICATION_PHONE_PATTERN = Pattern.compile("^(\\d{2})(\\d{4})(\\d{4})$");

    /**
     * Padrão regex para validar formato válido de telefone.
     */
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(\\(\\d{2}\\)|\\d{2}) ?9?\\d{4}[- ]?\\d{4}$");

    /**
     * Padrão regex para remover caracteres não numéricos do telefone.
     */
    private static final Pattern STRIP_PHONE_PATTERN = Pattern.compile("[^\\d]");

    /**
     * Retorna o telefone.
     * 
     * @return telefone no formato "(xx) 9xxxx-xxxx".
     */
    public String getPhone() {
        return STRIGIFICATION_PHONE_PATTERN
                .matcher(String.format("%010x", this.phone))
                .replaceAll("$1 9$2-$3");
    }

    /**
     * Define o telefone a partir de uma string.
     * 
     * @param phone telefone no formato.
     * 
     * @throws WorkshopException caso o telefone seja inválido.
     */
    public void setPhone(String phone) throws WorkshopException {

        // Verifica se o telefone é nulo.
        if (phone == null) {
            throw new WorkshopException("Telefone inválido - telefone não pode ser nulo!");
        }

        // Verifica se o telefone pertence ao formato padrão.
        if (PHONE_PATTERN.matcher(phone).matches() == false) {
            throw new WorkshopException("Telefone inválido - telefone deve estar no formato (xx) 9xxxx-xxxx!");
        }

        String stripedPhone = STRIP_PHONE_PATTERN.matcher(phone).replaceAll("");

        this.phone = 0L
                | ((long) (stripedPhone.charAt(0) - '0') << ((9 - 0) << 2))
                | ((long) (stripedPhone.charAt(1) - '0') << ((9 - 1) << 2));

        int account_nine = stripedPhone.length() != 10 ? 1 : 0;
        int end = 9 + account_nine;

        for (int i = 2 + account_nine; i < stripedPhone.length(); i++) {
            this.phone |= (long) (stripedPhone.charAt(i) - '0') << ((end - i) << 2);
        }
    }

    /**
     * Retorna a representação textual do telefone.
     * 
     * @return telefone no formato "(xx) 9xxxx-xxxx".
     */
    @Override
    public String toString() {
        return this.getPhone();
    }
}