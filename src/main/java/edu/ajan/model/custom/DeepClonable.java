package edu.ajan.model.custom;

/**
 * Interface para objetos que podem ser clonados profundamente.
 * 
 * @param <T> o tipo do objeto que implementa esta interface.
 * 
 * @author Alan Lima
 */
public interface DeepClonable<T> {

    /**
     * Cria um clone profundo do objeto.
     * 
     * <p> Todo objeto que implementa essa interfaca deve conter um constructor privado
     * de clonagem de forma dada:
     * 
     * <pre>
     * private T(T t) {
     *     this.field_n = t.field_n             // para campos primitivos (e nulos, tecnicamente)
     *     this.field_m = t.field_m.deepClone() // para campos por referência com essa interface implementada
     *     // para campos por referências de outros tipos, não há como lidar de uma maneira geral
     * }
     * </pre>
     * 
     * Para esse método, esse deve ser implementado da seguinte forma:
     * 
     * <pre>
     * return new T(this);
     * </pre>
     * 
     * @return a instânca clonada do objeto.
     */
    T deepClone();
}
