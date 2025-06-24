package edu.ajan.model.workshop.staff;

import edu.ajan.model.auth.AccessLevel;
import edu.ajan.model.exception.WorkshopException;
import edu.ajan.model.workshop.common.Person;

/**
 * Classe abstrata que representa um membro da oficina.
 * 
 * @author Alan Lima
 */
public abstract class StaffMember extends Person {

    /**
     * Contador de instâncias.
     */
    private static int instanceCount;

    /**
     * Identificador único do membro.
     */
    private final int id;

    /**
     * Hash da senha.
     */
    private long password;

    /**
     * Construtor padrão.
     */
    public StaffMember() {
        super();
        this.id = generateNextId();
    }

    /**
     * Construtor parametrizado.
     * 
     * @param name     nome do membro.
     * @param phone    número de telefone do membro.
     * @param cpf      CPF do membro.
     * @param password senha do membro.
     * 
     * @throws WorkshopException se algum dos parametros for inválido.
     */
    public StaffMember(String name, String phone, String cpf, String password) {
        super(name, phone, cpf);
        this.id = generateNextId();
        this.setPassword(password);
    }

    /**
     * Construtor de clonagem.
     * 
     * @param member membro a ser clonado.
     */
    protected StaffMember(StaffMember member) {
        super(member);
        this.id = member.id;
        this.password = member.password;
    }

    /**
     * Retorna o identificador do membro.
     * 
     * @return identificador do membro.
     */
    public int id() {
        return id;
    }

    /**
     * Retorna o hash da senha do membro.
     * 
     * @return hash da senha do membro.
     */
    public long getPassword() {
        return password;
    }

    /**
     * Define a senha do membro.
     * 
     * @param password senha do membro.
     * 
     * @throws WorkshopException se a senha for nula.
     */
    public void setPassword(String password) {
        if (password == null) {
            throw new WorkshopException("senha não pode ser nula");
        }

        this.password = hash(password);
    }

    /**
     * Checa se a senha informada contra aquela do atributo {@code password}.
     * 
     * @param password senha para checagem
     * @return senha para checagem.
     */
    public boolean checkPassword(String password) {
        return this.password == hash(password);
    }

    /**
     * Retorna o nível de acesso do membro.
     * 
     * @return nível de acesso do membro.
     */
    public abstract AccessLevel getAccessLevel();

    /**
     * Hashifica uma string não nula. <i>Não é segura, o espaço de
     * respostas é reversível</i>.
     * 
     * @param string string a ser hashificada.
     * @return cógigo hash para a string.
     */
    public static long hash(String string) {

        byte[] bytes = string.getBytes();
        long hash = 0L;

        for (int i = 0; i < bytes.length; i++) {
            hash ^= ((long) bytes[i] & 0xFFL) * 0x0101_0101_0101_0101L;
            hash = hash * 0x50C1EDADE_F0551L + 0xCAFE_D0CE_0F1C1AL;
        }

        return hash;
    }

    /**
     * Retorna o número total de instâncias criadas.
     * 
     * @return número total de instâncias criadas.
     */
    public static int getInstanceCount() {
        return instanceCount;
    }

    /**
     * Incrementa o contador de instâncias.
     */
    private static void incrementInstanceCount() {
        instanceCount++;
    }

    /**
     * Gera o próximo identificador e incrementa o contador de instâncias.
     * 
     * @return próximo identificador único.
     */
    private static int generateNextId() {
        incrementInstanceCount();
        return instanceCount;
    }

    /**
     * Retorna uma representação textual do membro.
     * 
     * @return representação textual do membro.
     */
    @Override
    public String toString() {
        String person = super.toString();
        return String.format("(%s %016x)", person.substring(1, person.length() - 1), password);
    }
}