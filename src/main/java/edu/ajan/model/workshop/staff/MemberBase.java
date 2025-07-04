package edu.ajan.model.workshop.staff;

import java.util.HashMap;

/**
 * Classe que representa a base de membros de uma oficina mecânica.
 * 
 * @author Alan Lima
 */
public class MemberBase {
    
    private HashMap<Integer, StaffMember> members;

    /**
     * Construtor padrão.
     */
    public MemberBase() {
        this.members = new HashMap<>();
    }

    /**
     * Retorna uma estrutura iterável de membros.
     * 
     * @return estrutura iterável de membros.
     */
    public Iterable<StaffMember> getMembers() {
        return members.values();
    }

    /**
     * Retorna um membro a partir do seu identificador.
     * 
     * @param memberId identificador de membro.
     * @return membro de identificador passado, ou {@code null} se esse membro não
     *         existir.
     */
    public StaffMember getMember(int memberId) {
        return members.get(memberId);
    }

    /**
     * Asserta a existência de um membro.
     * 
     * @param memberId identificador de membro.
     * @return {@code true} se, e somente se o membro existir.
     */
    public boolean hasMember(int memberId) {
        return members.containsKey(memberId);
    }

    /**
     * Adiciona um membro à base.
     * 
     * @param member membro a ser adicionado.
     */
    public void addMember(StaffMember member) {
        members.put(member.id(), member);
    }

    /**
     * Remove um membro da base.
     * 
     * @param memberId identificador do membro a ser removido.
     */
    public void removeMember(int memberId) {
        members.remove(memberId);
    }
}
