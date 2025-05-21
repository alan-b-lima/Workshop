package model.workshop.common;

import model.workshop.exception.WorkshopException;

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
     * Construtor que recebe o telefone no formato "(xx) 9xxxx-xxxx".
     * 
     * @param phone telefone no formato "(xx) 9xxxx-xxxx".
     * 
     * @throws WorkshopException caso o telefone seja inválido.
     */
    public Phone(String phone) throws WorkshopException {
        this.setPhone(phone);
    }

    /**
     * Retorna o telefone no formato "(xx) 9xxxx-xxxx".
     * 
     * @return telefone no formato "(xx) 9xxxx-xxxx".
     */
    public String getPhone() {
        return String
                .format("%010x", this.phone)
                .replaceAll("^(\\d{2})(\\d{4})(\\d{4})$", "($1) 9$2-$3");
    }

    /**
     * Define o telefone a partir de uma string.
     * 
     * @param phone telefone no formato.
     * 
     * @throws WorkshopException caso o telefone seja inválido.
     */
    public void setPhone(String phone) throws WorkshopException {
        if (phone == null) {
            throw new WorkshopException("Telefone inválido - telefone não pode ser nulo!");
        }

        if (phone.matches("^(\\(\\d{2}\\)|\\d{2}) ?9?\\d{4}[- ]?\\d{4}$") == false) {
            throw new WorkshopException("Telefone inválido - telefone deve estar no formato (xx) 9xxxx-xxxx!");
        }

        String stripedPhone = phone.replaceAll("[^\\d]", "");

        this.phone = 0L
                | ((long) (stripedPhone.charAt(0) - '0') << ((9 - 0) << 2))
                | ((long) (stripedPhone.charAt(1) - '0') << ((9 - 1) << 2));

        if (stripedPhone.length() == 10) {
            for (int i = 2; i < stripedPhone.length(); i++) {
                this.phone |= (long) (stripedPhone.charAt(i) - '0') << ((9 - i) << 2);
            }
        } else {
            for (int i = 3; i < stripedPhone.length(); i++) {
                this.phone |= (long) (stripedPhone.charAt(i) - '0') << ((10 - i) << 2);
            }
        }
    }

    /**
     * Retorna a representação textual do telefone no formato "(xx) 9xxxx-xxxx".
     * 
     * @return telefone no formato "(xx) 9xxxx-xxxx".
     */
    @Override
    public String toString() {
        return this.getPhone();
    }
}