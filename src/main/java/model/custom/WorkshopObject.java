package model.custom;

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
     * {@link DeepClonable#deepClone()}
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
}
