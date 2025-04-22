package main.common;

public class Decimal {
    private long value;

    private static final long BASE = 10;
    private static final long POINT = 2;
    private static final long POWER = (long) Math.pow((double) BASE, (double) POINT);

    public Decimal() {
        this.value = 0;
    }

    public Decimal(long value) {
        this.value = value * POWER;
    }

    public static Decimal add(Decimal v1, Decimal v2) {
        Decimal res = new Decimal();
        res.value = v1.value + v2.value;
        return res;
    }

    public static Decimal sub(Decimal v1, Decimal v2) {
        Decimal res = new Decimal();
        res.value = v1.value - v2.value;
        return res;
    }

    public static Decimal mul(Decimal v1, Decimal v2) {
        Decimal res = new Decimal();
        res.value = v1.value * v2.value / POWER;
        return res;
    }

    public static Decimal div(Decimal v1, Decimal v2) {
        Decimal res = new Decimal();
        res.value = v1.value * POWER / v2.value;
        return res;
    }

    @Override
    public String toString() {
        String num = Long.toString(this.value, (int) BASE);

        switch (num.length()) {
            case 1:
                return String.format("0.0%s", num);

            case 2:
                return String.format("0.%s", num);

            default:
                return num
                        .replaceAll("^([0-9a-z]*)([0-9a-z]{2})$", "$1.$2");
        }
    }

    public static void main(String[] args) {
        Decimal a = new Decimal(134);
        System.out.println(a);

        Decimal b = new Decimal(3);
        System.out.println(b);

        System.out.println(Decimal.add(a, b));
        System.out.println(Decimal.sub(a, b));
        System.out.println(Decimal.mul(a, b));
        System.out.println(Decimal.div(a, b));
    }
}
