package custom;

/**
 * Interface que define métodos para clonagem profunda de objetos.
 * 
 * @param <T> o tipo do objeto a ser clonado.
 */
public interface DeepClonable<T> {

    /**
     * Clona o objeto de forma profunda, criando uma cópia completa de todos os
     * campos.
     * 
     * @return uma nova instância do objeto clonado profundamente.
     */
    T deepClone();
}