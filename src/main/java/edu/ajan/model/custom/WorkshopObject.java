package edu.ajan.model.custom;

import java.text.NumberFormat;

/**
 * Classe base para objetos do workshop.
 * 
 * Esta classe serve como uma superclasse para todos os objetos que pertencem ao
 * modelo (classes de dados) do workshop.
 * 
 * @author Alan Lima
 */
public abstract class WorkshopObject extends Object {

    /**
     * Cria um clone profundo do objeto.
     * 
     * @return a instânca clonada do objeto.
     */
    public abstract WorkshopObject deepClone();

    /**
     * Remove o objeto do semanticamente workshop e facilita para o coletor de lixo.
     * 
     * @return {@code null}
     */
    public WorkshopObject dispose() {
        return null;
    }

    /**
     * Retorna uma representação textual do objeto.
     * 
     * @return representação textual do objeto.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Retorna uma representação textual do objeto com formatação especial.
     * 
     * @param args argumentos para formatar a representação textual.
     * @return representação textual formatada do objeto.
     */
    @Deprecated
    public static String toString(Object... args) {
        StringBuilder sb = new StringBuilder("(");

        sb.append(toString(args[0]));
        for (int i = 1; i < args.length; i++) {
            sb.append(" ").append(toString(args[i]));
        }

        return sb.append(")").toString();
    }

    /**
     * Retorna uma representação textual do objeto com formatação especial.
     * 
     * @param obj o objeto a ser formatado.
     * @return representação textual formatada do objeto.
     */
    @Deprecated
    private static String toString(Object obj) {

        return switch (obj) {
            case null -> "\033[38;2;86;156;214mnull\033[m";
            case String o -> String.format("\033[38;2;219;145;102m\"%s\"\033[m", o);
            case Double o -> NumberFormat.getCurrencyInstance().format(o).toString();
            default -> obj.toString();
        };
    }
}
