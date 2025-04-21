package main.common;

public final class Phone {

    private long phone;

    public Phone() {

    }

    public Phone(String phone) {
        this.setPhone(phone);
    }

    public String getPhone() {
        return String
                .format("%010x", this.phone)
                .replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) 9$2-$3");
    }

    public void setPhone(String phone) {
        if (phone == null) {
            // error
            return;
        }

        if (phone.matches("^(\\(\\d{2}\\)|\\d{2}) ?9?\\d{4}-?\\d{4}$") == false) {
            // error
            return;
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

    @Override
    public String toString() {
        return this.getPhone();
    }
}