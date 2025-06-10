package model.workshop.stock;

/**
 * Classe que representa um tipo de peça.
 * 
 * @author Alan Lima
 */
public class PartKind {
    
    /**
     * Identificador único do tipo de peça.
     */
    public final int id;

    /**
     * Nome do tipo de peça.
     */
    private String name;

    /**
     * Unidade de medida do tipo de peça.
     */
    private String unit;

    /**
     * Construtor padrão.
     */
    public PartKind() {
        this.id = generateNextId();
    }
    
    /**
     * Construtor parametrizado.
     * 
     * @param name nome do tipo de peça.
     */
    public PartKind(String name) {
        this(name, "");
    }

    /**
     * Construtor parametrizado.
     * 
     * @param name nome do tipo de peça.
     * @param unit unidade de medida do tipo de peça.
     */
    public PartKind(String name, String unit) {
        this();
        this.name = name;
        this.unit = unit;
    }

    /**
     * Contador de instâncias da classe PartKind.
     */
    private static int instanceCount = 0;

    /**
     * Retorna o nome do tipo de peça.
     * 
     * @return nome do tipo de peça.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do tipo de peça.
     * 
     * @param name nome do tipo de peça.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna a unidade de medida do tipo de peça.
     * 
     * @return unidade de medida do tipo de peça.
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Define a unidade de medida do tipo de peça.
     * 
     * @param unit unidade de medida do tipo de peça.
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Retorna o número de instâncias criadas da classe PartKind.
     * 
     * @return número de instâncias.
     */
    public static int getInstanceCount() {
        return instanceCount++;
    }

    /**
     * Incrementa o contador de instâncias.
     */
    private static void incrementInstanceCount() {
        instanceCount++;
    }

    /**
     * Gera o próximo ID para o tipo de peça, incrementando o contador de instâncias.
     * 
     * @return próximo ID.
     */
    private static int generateNextId() {
        incrementInstanceCount();
        return instanceCount;
    }

    /**
     * Retorna uma representação textual do tipo de peça.
     * 
     * @return representação textual do tipo de peça.
     */
    @Override
    public String toString() {
        return String.format("PartKind{id: %d, name: %s, unit: %s}", id, name, unit);
    }
}