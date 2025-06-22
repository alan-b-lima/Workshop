package edu.ajan.model.workshop.stock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.ajan.model.custom.DeepClonable;
import edu.ajan.model.custom.WorkshopObject;
import edu.ajan.model.exception.WorkshopException;

/**
 * Classe que representa um fornecedor.
 * 
 * @author Alan Lima
 */
public class Supplier extends WorkshopObject implements DeepClonable<Supplier> {

    /**
     * Padrão RegEx que tanto casa um superconjunto das formatações válidas de CNPJ,
     * quanto pode dividir os dígitos do CNPJ nos grupos apropriados para formatação.
     */
    private static final Pattern CNPJ_PATTERN = Pattern.compile("(\\d{2})\\.?(\\d{3})\\.?(\\d{3})/?(\\d{4})-?(\\d{2})");

    /**
     * Padrão de substituição para CNPJ para remover dígitos não decimais, o CNPJ
     * deve ter sido agrupado por {@link #CNPJ_PATTERN}.
     */
    private static final String CNPJ_NON_DIGIT_REMOVAL_MASK = "$1$2$3$4$5";

    /**
     * Padrão de substituição para CNPJ padrão que tenha sido agrupado por
     * {@link #CNPJ_PATTERN}.
     */
    private static final String STANDARD_CNPJ_MASK = "$1.$2.$3/$4-$5";

    /**
     * Contador de instâncias de fornecedores.
     */
    private static int instanceCount;

    /**
     * Identificador único do fornecedor.
     */
    private final int id;

    /**
     * Nome fantasia do fornecedor.
     */
    private String tradeName;

    /**
     * CNPJ do fornecedor.
     * 
     * Segue estritamente o formato xx.xxx.xxx/xxxx-xx.
     */
    private String cnpj;

    /**
     * Construtor padrão.
     */
    public Supplier() {
        this.id = generateNextId();
        this.tradeName = "";
        this.cnpj = "00.000.000/0000.00";
    }

    /**
     * Construtor parametrizado.
     * 
     * @param tradeName nome fantasia do fornecedor.
     * @param cnpj      CNPJ do fornecedor.
     * 
     * @throws WorkshopException se algum dos parâmetros for inválido.
     */
    public Supplier(String tradeName, String cnpj) {
        this();
        this.setTradeName(tradeName);
        this.setCnpj(cnpj);
    }

    /**
     * Construtor de clonagem.
     * 
     * @param supplier fornecedor a ser clonado.
     */
    protected Supplier(Supplier supplier) {
        this.id = supplier.id;
        this.tradeName = supplier.tradeName;
        this.cnpj = supplier.cnpj;
    }

    /**
     * Retorna o identificador único do fornecedor.
     * 
     * @return identificador do fornecedor.
     */
    public int id() {
        return id;
    }

    /**
     * Retorna o nome fantasia do fornecedor.
     * 
     * @return nome fantasia do fornecedor.
     */
    public String getTradeName() {
        return tradeName;
    }

    /**
     * Define o nome fantasia do fornecedor.
     * 
     * @param tradeName nome fantasia do fornecedor.
     * 
     * @throws WorkshopException se o nome fantasia for nulo.
     */
    public void setTradeName(String tradeName) {
        if (tradeName == null) {
            throw new WorkshopException("nome fantasia não pode ser nulo");
        }

        this.tradeName = tradeName;
    }

    /**
     * Retorna o CNPJ do fornecedor.
     * 
     * @return CNPJ do fornecedor.
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Define o CNPJ do fornecedor.
     * 
     * <p>
     * Algoritmo de verificação de CNPJ:
     * 
     * <p>
     * Considere o CNPJ {@code AB.CDE.FGH/IJKL-MN}, sendo {@code A}, {@code B},
     * {@code C}, {@code D}, {@code E}, {@code F}, {@code G}, {@code H}, {@code I},
     * {@code J}, {@code K}, {@code L}, {@code M} e {@code N} dígitos de 0 a 9.
     * 
     * <p>
     * {@code x := (5A + 4B + 3C + 9D + 8E + 7F + 6G + 5H + 4I + 3J + 2K) mod 11}
     * <p>
     * {@code y := x < 2 ? 0 : 11 - x}
     * 
     * <p>
     * {@code z := (6A + 5B + 4C + 3D + 2E + 9F + 8G + 7H + 6I + 5J + 4K + 3L) mod 11}
     * <p>
     * {@code y := z < 2 ? 0 : 11 - z}
     * 
     * <p>
     * Para o CNPJ ser válido, é necessário e suficiente que {@code y = M},
     * {@code w = N} e ao menos um dígito ser diferente de outro.
     * 
     * @param cnpj CNPJ do fornecedor.
     * 
     * @throws WorkshopException se o CNPJ for nulo, fora do padrão, ou tiver os
     *                           dígitos verificadores inválidos.
     */
    public void setCnpj(String cnpj) {

        if (cnpj == null) {
            throw new WorkshopException("CNPJ não pode ser nulo");
        }

        Matcher cnpjMatcher = CNPJ_PATTERN.matcher(cnpj);

        if (!cnpjMatcher.matches()) {
            throw new WorkshopException("CNPJ deve seguir o formato xx.xxx.xxx/xxxx-xx");
        }

        String stripedCnpj = cnpjMatcher.replaceAll(CNPJ_NON_DIGIT_REMOVAL_MASK);

        // Os pesos são:
        // - [ 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 ] para o primeiro dígito verificador,
        // e
        // - [ 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 ] para o segundo dígito verificador
        //
        // Note o padrão de repetição, exploraremos isso

        int checkSum1st = 0;
        int checkSum2nd = (int) (stripedCnpj.charAt(0) - '0') * 6;

        // Primeira parte, [ 5, ..., 2 ]
        for (int i = 5; i >= 2; i--) {
            checkSum1st += (int) (stripedCnpj.charAt(5 - i) - '0') * i;
            checkSum2nd += (int) (stripedCnpj.charAt(6 - i) - '0') * i;
        }

        // Segunda parte, [ 9, ..., 2 ]
        for (int i = 9; i >= 2; i--) {
            checkSum1st += (int) (stripedCnpj.charAt(13 - i) - '0') * i;
            checkSum2nd += (int) (stripedCnpj.charAt(14 - i) - '0') * i;
        }

        int remainder1st = checkSum1st % 11;
        int remainder2nd = checkSum2nd % 11;

        int correct1st = remainder1st < 2 ? 0 : 11 - remainder1st;
        int correct2nd = remainder2nd < 2 ? 0 : 11 - remainder2nd;

        // Se o primeiro dígito verificador está incorreto, o segundo dígito verificador
        // obtido não é confiável
        if (correct1st != (int) (stripedCnpj.charAt(12) - '0')) {
            throw new WorkshopException("CNPJ com dígitos verificadores inválidos, começa com %d", correct1st);
        }

        if (correct2nd != (int) (stripedCnpj.charAt(13) - '0')) {
            throw new WorkshopException("CNPJ com dígitos verificadores inválidos, tente %d%d", correct1st, correct2nd);
        }

        String formattedCpf = cnpjMatcher.replaceAll(STANDARD_CNPJ_MASK);
        this.cnpj = formattedCpf;
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
     * Cria um clone profundo do fornecedor.
     * 
     * @return a instância clonada do fornecedor.
     */
    @Override
    public Supplier deepClone() {
        return new Supplier(this);
    }

    /**
     * Retorna uma representação textual do fornecedor.
     * 
     * @return representação textual do fornecedor.
     */
    @Override
    public String toString() {
        return String.format("(%d \"%s\" %s)", id, tradeName, cnpj);
    }
}
