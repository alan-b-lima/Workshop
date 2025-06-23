package edu.ajan.model.workshop.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.ajan.model.custom.WorkshopObject;
import edu.ajan.model.exception.WorkshopException;

/**
 * Classe que representa um veículo.
 * 
 * @author Alan Lima
 */
public class Vehicle extends WorkshopObject {

    /**
     * Padrão RegEx que casa placas no padrão MERCOSUL, "AAA1A11".
     */
    private static final Pattern MERCOSUL_PLATE_PATTERN = Pattern.compile("([A-Z]{3}\\d[A-Z]\\d{2})");

    /**
     * Padrão RegEx que casa placas no padrão anterior não mais emitido, mas válido,
     * "AAA1111".
     */
    private static final Pattern OLD_PLATE_PATTERN = Pattern.compile("([A-Z]{3})[-\\*\\.]?(\\d{4})");

    /**
     * Padrão de substituição para placa no padrão MERCOSUL que tenham sido agrupada
     * por {@link #MERCOSUL_PLATE_PATTERN}.
     */
    private static final String STANDART_MERCOSUL_PLATE_MASK = "$1";

    /**
     * Padrão de substituição para placa no padrão anterior que tenham sido agrupada
     * por {@link #OLD_PLATE_PATTERN}.
     */
    private static final String OLD_STANDART_PLATE_MASK = "$1$2";

    /**
     * Contador de instâncias de Shipment.
     */
    private static int instanceCount;

    /**
     * Identificador único do veículo.
     */
    private final int id;

    /**
     * Modelo do veículo.
     */
    private String model;

    /**
     * Placa do veículo.
     * 
     * Segue estritamento o formato "AAA1111" ou "AAA1A11".
     */
    private String plate;

    /**
     * Ano do veículo.
     */
    private int year;

    /**
     * Construtor padrão.
     */
    public Vehicle() {
        this.id = generateNextId();
        this.model = "";
        this.model = "AAA-1111";
        this.year = 1886;
    }

    /**
     * Construtor que recebe o modelo, placa e ano do veículo.
     * 
     * @param model modelo do veículo.
     * @param plate placa do veículo.
     * @param year  ano do veículo.
     * 
     * @throws WorkshopException se algum dos paramentros for inválido.
     */
    public Vehicle(String model, String plate, int year) {
        this();
        this.setModel(model);
        this.setPlate(plate);
        this.setYear(year);
    }

    /**
     * Construtor de clonagem.
     * 
     * @param vehicle instância a ser clonada.
     */
    protected Vehicle(Vehicle vehicle) {
        this.id = vehicle.id;
        this.model = vehicle.model;
        this.plate = vehicle.plate;
        this.year = vehicle.year;
    }

    /**
     * Retorna o identificador do veículo.
     * 
     * @return identificador do veículo.
     */
    public int id() {
        return id;
    }

    /**
     * Retorna o modelo do veículo.
     * 
     * @return modelo do veículo.
     */
    public String getModel() {
        return model;
    }

    /**
     * Define o modelo do veículo.
     * 
     * @param model modelo do veículo.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Retorna a placa do veículo.
     * 
     * @return placa do veículo.
     */
    public String getPlate() {
        return plate;
    }

    /**
     * Define a placa do veículo.
     * 
     * @param plate placa do veículo.
     * 
     * @throws WorkshopException se a placa for nula ou estaja num formato inválido.
     */
    public void setPlate(String plate) {

        if (plate == null) {
            throw new WorkshopException("placa não pode ser nula");
        }

        Matcher mercosulPlateMatcher = MERCOSUL_PLATE_PATTERN.matcher(plate);

        if (mercosulPlateMatcher.matches()) {
            this.plate = mercosulPlateMatcher.replaceAll(STANDART_MERCOSUL_PLATE_MASK);
            return;
        }

        Matcher oldPlateMatcher = OLD_PLATE_PATTERN.matcher(plate);

        if (oldPlateMatcher.matches()) {
            this.plate = oldPlateMatcher.replaceAll(OLD_STANDART_PLATE_MASK);
            return;
        }

        throw new WorkshopException("placa deve ser no formato \"AAA1111\" ou \"AAA1A11\"");
    }

    /**
     * Retorna o ano do veículo.
     * 
     * @return ano do veículo.
     */
    public int getYear() {
        return year;
    }

    /**
     * Define o ano do veículo.
     * 
     * @param year ano do veículo.
     */
    public void setYear(int year) {
        this.year = year;
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
     * Cria um clone profundo do elevador.
     * 
     * @return a instânca clonada do elevador.
     */
    @Override
    public WorkshopObject deepClone() {
        return new Vehicle(this);
    }

    /**
     * Retorna uma representação textual do veículo.
     * 
     * @return representação textual do veículo.
     */
    @Override
    public String toString() {
        return String.format("(\"%s\" %s %d)", model, plate, year);
    }
}
