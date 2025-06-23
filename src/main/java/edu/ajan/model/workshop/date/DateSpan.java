package edu.ajan.model.workshop.date;

/**
 * Classe que representa um espaço de tempo.
 * 
 * <p> Todo espaço de tempo representado por essa classe é um intervalo imutável
 * {@code [a, b)}, onde {@code a} e {@code b} são inteiro longos ({@code long}),
 * {@code a ≤ b}, que representam um a diferença, em milisegundos, entre o
 * momento atual e 1° de janeiro de 1970, 00:00:00 UTC, similar a um timespamp
 * UNIX e com o mesmo <em>epoch</em>.
 * 
 * <p> Para funcionalides como conversão e formatação, use a classe
 * {@link Dates} deste pacote.
 * 
 * @author Alan Lima
 * 
 * @see edu.ajan.model.workshop.date.Dates
 */
public final class DateSpan implements Comparable<DateSpan> {

    /**
     * Começo do espaço de tempo.
     */
    private final long start;

    /**
     * Fim do espaço de tempo.
     */
    private final long end;

    /**
     * Construtor parametrizado.
     * 
     * @param start o começo do espaço de tempo.
     * @param end   o fim do espaço de tempo.
     * 
     * @throws IllegalArgumentException se o começo for depois do fim.
     */
    public DateSpan(long start, long end) {
        if (start > end) {
            throw new IllegalArgumentException("começo não pode ser depois de fim");
        }

        this.start = start;
        this.end = end;
    }

    /**
     * Retorna o começo do espaço de tempo.
     * 
     * @return começo do espaço de tempo.
     */
    public long start() {
        return start;
    }

    /**
     * Retorna o fim do espaço de tempo.
     * 
     * @return fim do espaço de tempo.
     */
    public long end() {
        return end;
    }

    /**
     * Retorna a duração do espaço de tempo em milissegundos.
     * 
     * @return duração do espaço de tempo.
     */
    public long duration() {
        return end - start;
    }

    /**
     * Retorna a intersecção entre {@code this} e {@code that}.
     * 
     * @param that o intervalo para interseccionar contra {@code this}.
     * @return intersecção entre {@code this} e {@code that}. {@code null} caso a
     *         intersecção seja vazia.
     */
    public DateSpan intersect(DateSpan that) {
        long maxStart = Math.min(this.start, that.start);
        long minEnd = Math.min(this.end, that.end);

        if (minEnd < maxStart) {
            return null;
        }

        return new DateSpan(maxStart, minEnd);
    }

    /**
     * Retorna {@code true} caso a intersecção de {@code this} e {@code that} for
     * não vazia.
     * 
     * @param that o intervalo para interseccionar contra {@code this}.
     * @return {@code this ∩ that != ∅}.
     */
    public boolean intersects(DateSpan that) {
        long maxStart = Math.min(this.start, that.start);
        long minEnd = Math.min(this.end, that.end);

        return maxStart <= minEnd;
    }

    /**
     * Compara {@code this} com o objeto {@code that} fornecido.
     * 
     * @return um valor negativo, zero ou positivo, dependendo da comparação:
     *         <ul>
     *             <li>se {@code this.start < that.start}, retorna um valor negativo;
     *             <li>se {@code this.start > that.start}, retorna um valor positivo;
     *             <li>se {@code this = that}, segue:
     *             <ul>
     *                 <li>se {@code this.end < that.end}, retorna um valor negativo,
     *                 <li>se {@code this.end = that.end}, retorna zero,
     *                 <li>se {@code this.end > that.end}, retorna um valor positivo.
     *             </ul>
     *         </ul>
     */
    @Override
    public int compareTo(DateSpan that) {
        int startDiff = Long.signum(this.start - that.start);
        if (startDiff != 0) {
            return startDiff;
        }

        return Long.signum(this.end - that.end);
    }

    /**
     * Compara o objeto {@code obj} e informa se este é igual a {@code this}.
     * Equivalente a {@code this.compareTo(obj) == 0}.
     * 
     * @return resultado da comparação.
     */
    @Override
    public final boolean equals(Object obj) {

        if (obj instanceof DateSpan that) {
            return true
                    && this.start == that.start
                    && this.end == that.end;
        }

        return false;
    }

    /**
     * Retorna uma representação textual do espaço de tempo seguindo o formato
     * "dd/MM/yyyy HH:mm" para as datas.
     * 
     * @return representação textual do espaço de tempo.
     */
    public String toDateTimeString() {
        return String.format("[%s %s)", Dates.formatAsDateTime(start), Dates.formatAsDateTime(end));
    }

    /**
     * Retorna uma representação textual do espaço de tempo seguindo o formato
     * "dd/MM/yyyy" para as datas.
     * 
     * @return representação textual do espaço de tempo.
     */
    public String toDateString() {
        return String.format("[%s %s)", Dates.formatAsDate(start), Dates.formatAsDate(end));
    }

    /**
     * Retorna uma representação textual do espaço de tempo formatando o timestamps
     * como números.
     * 
     * @return representação textual do espaço de tempo.
     */
    public String toTimeStampString() {
        return String.format("[%d %d)", start, end);
    }

    /**
     * Retorna uma representação textual do espaço de tempo.
     * 
     * @return representação textual do espaço de tempo.
     */
    @Override
    public final String toString() {
        return toDateTimeString();
    }
}