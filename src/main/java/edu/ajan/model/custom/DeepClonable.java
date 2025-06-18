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
     * @return a instânca clonada do objeto.
     */
    T deepClone();
}
