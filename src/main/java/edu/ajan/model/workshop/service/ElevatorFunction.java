package edu.ajan.model.workshop.service;

import java.util.Arrays;

/**
 * Classe [superprojetada] que cumpre uma função de enumeradora de funções para
 * os elevadores.
 * 
 * @author Alan Lima
 */
public final class ElevatorFunction {

    /**
     * Um contador estático que incrementa para gerar os cógidos das funções.
     */
    private static int iota = 0;

    /**
     * Código da função. Estritamente uma potência de dois.
     */
    private final int code;

    /**
     * Nome apresentável da função.
     */
    private final String name;

    /**
     * Vetor para imitar o comportamento da função embutida {@code values()} de
     * tipos enumeradores. <i>Não deve ser inicializado</i>.
     */
    private static ElevatorFunction[] values;

    /**
     * Denota que o elevador tem função de realizar atividades corriqueiras.
     */
    public static final ElevatorFunction GENERAL = new ElevatorFunction("atividades corriqueiras");

    /**
     * Denota que o elevador tem função de alinhamento.
     */
    public static final ElevatorFunction ALIGNING = new ElevatorFunction("alinhamento");

    /**
     * Denota que o elevador tem função de balanceamento.
     */
    public static final ElevatorFunction BALANCING = new ElevatorFunction("balanceamento");

    /**
     * Construtor parametrizado.
     * 
     * @param function nome da função
     * 
     * @throws NullPointerException se o nome da função for nulo.
     */
    private ElevatorFunction(String function) {
        if (function == null) {
            throw new NullPointerException("name");
        }

        this.code = 1 << iota;
        this.name = function;

        append(this);
        iota++;
    }

    /**
     * Retorna o código da função do elevador.
     * 
     * @return código da função do elevador.
     */
    public int code() {
        return code;
    }

    /**
     * Retorna o nome da função do elevador.
     * 
     * @return nome da função do elevador.
     */
    public String name() {
        return name;
    }

    /**
     * Checa se a função passada é de certo tipo.
     * 
     * @param function função a ser atestado.
     * @return função passada é de certo tipo.
     */
    public boolean hasFunction(int function) {
        return (function & this.code) != 0;
    }

    /**
     * Retorna todas as instância desse tipo. Imitação de {@code values()} dos tipo
     * enumerados.
     * 
     * @return todos as instância desse tipo.
     */
    public static ElevatorFunction[] values() {
        return values;
    }

    /**
     * Adiciona uma função à {@values}. <i>Jamais deve ser usado fora do
     * construtor!</i>
     * 
     * @param function função a ser adicionada.
     */
    private static void append(ElevatorFunction function) {
        if (values == null) {
            values = new ElevatorFunction[] { function };
            return;
        }

        if (values.length <= iota) {
            values = Arrays.copyOf(values, iota + 1);
        }

        values[iota] = function;
    }

    /**
     * Retorna os nomes das funções marcadas no parametro {@code function} passado.
     * 
     * @param function campo de bits de funções.
     * @return lista textual de todas as funções do campo de bits.
     */
    public static String toString(int function) {

        // Efetivamente apaga todo bit acima da posição `iota`
        function &= ~(~0 << iota);

        // Caso não haja função especificada
        if (function == 0) {
            return "nenhuma função";
        }

        int bitCount = Integer.bitCount(function);
        int lowestBitPos = Integer.bitCount(Integer.lowestOneBit(function) - 1);

        // Caso não haja apenas uma função especificada
        if (bitCount == 1) {
            return values[lowestBitPos].name;
        }

        // Caso não haja multiplas funções especificadas

        StringBuilder sb = new StringBuilder(values[lowestBitPos].name);
        int i = lowestBitPos + 1;
        function >>>= i;

        // Assim que isso terminar, é guarantido que `function` tenha um 1. Como `i`
        // posições terão sido iteradas até o memento, estaremos na função de código
        // 2^`i`, ou seja, `values[i]`
        for (; function > 1; function >>>= 1, i++) {
            if (values[i].hasFunction(function)) {
                sb.append(", ").append(values[i].name);
            }
        }

        return sb.append(" e ").append(values[i].name).toString();
    }

    /**
     * Protege os objetos dessa classe contra clonagem.
     * 
     * @return (não retorna).
     * @throws CloneNotSupportedException sempre.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("funções de elevador não podem ser clonadas");
    }

    /**
     * Retorna o nome da função.
     * 
     * @return nome da função.
     */
    @Override
    public String toString() {
        return name;
    }
}
