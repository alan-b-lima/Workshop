package edu.ajan.model.workshop.staff;

import edu.ajan.model.auth.AccessLevel;

/**
 * Classe que representa um administrador da oficina.
 * 
 * @author Juan Pablo
 */
public class Administrator {

    /**
     * Contador de instâncias de administradores.
     */
    private double proLabore;

    /**
     * Nível de acesso do administrador.
     */
    private AccessLevel accessLevel;

    /**
     * Construtor padrão.
     */
    public Administrator() {

    }

    /**
     * Construtor parametrizado.
     */
    public Administrator(String, String, String, String, double, String, AccessLevel) {

    }

    /**
     * Construtor de clonagem.
     * 
     * @param administrator administrador a ser clonado.
     */
    protected Administrator(Administrator) {

    }

    /**
     * Retorna o identificador único do administrador.
     * 
     * @return identificador único do administrador.
     */
    public double getProlabore() {
        return proLabore;
    }

    /**
     * Define o valor do pro labore do administrador.
     * 
     * @param proLabore
     */
    public void setProlabore(double proLabore) {

    }

    /**
     * Retorna o nível de acesso do administrador.
     * 
     * @return nível de acesso do administrador.
     */
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    /**
     * Define o nível de acesso do administrador.
     * 
     * @param accessLevel
     */
    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    /**
     * Realiza uma cópia profunda do administrador.
     */
    public Administrator deepClone() {
    }

    /**
     * Retorna uma representação em string do administrador.
     * 
     * @return representação em string do administrador.
     */
    public String toString() {
        return "";
    }
}
