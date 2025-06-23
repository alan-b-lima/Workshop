package edu.ajan.model.custom;

/**
 * Classe base para objetos do workshop.
 * 
 * <p> Esta classe serve como uma superclasse para todos os objetos que
 * pertencem ao modelo (classes de dados) da oficina.
 * 
 * @author Alan Lima
 */
public abstract class WorkshopObject extends Object {

    /**
     * Construtor padrão.
     */
    public WorkshopObject() {

    }

    /**
     * Cria um clone profundo do objeto.
     * 
     * @return a instânca clonada do objeto.
     */
    public abstract WorkshopObject deepClone();
}
