package edu.ajan.model.persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Classe de utilidade que serializa e salva, e carrega e deserializa arquivos
 * no formato Json.
 * 
 * Essa classe faz uso da biblioteca
 * <a href=
 * "https://javadoc.io/doc/com.google.code.gson/gson/latest/index.html">Gson</a>
 * para manipulação de Json.
 * 
 * @author Alan Lima
 */
public final class JsonHandler {

    /**
     * Formato padrão de data utilizado para nomear os arquivos de snapshot.
     */
    private static final SimpleDateFormat STANDARD_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd-HHmmss-SSS");

    /**
     * Caminho para o diretório raiz da persistência.
     */
    private static final Path ROOT_FILEPATH = Path.of(".workshop");

    /**
     * Caminho para o arquivo do curador.
     */
    private static final Path CARETAKER_FILEPATH = ROOT_FILEPATH.resolve("caretaker.json");

    /**
     * Caminho para o diretório que contém as snapshots.
     */
    private static final Path SNAPSHOT_FILEPATH = ROOT_FILEPATH.resolve("snapshot");

    /**
     * Serializador e deserializador de Json.
     */
    private static final Gson GSON = new Gson();

    /**
     * Construtor privado.
     */
    private JsonHandler() {

    }

    /**
     * Retorna o caminho para o arquivo do curador.
     * 
     * @return caminho para o arquivo do curador.
     */
    public static Path getCaretakerFilepath() {
        return CARETAKER_FILEPATH;
    }

    /**
     * Retorna o caminho para o arquivo que contém uma certa snapshot.
     * 
     * @param id identificador da snapshot.
     * @return caminho para o diretório que contém as snapshots.
     */
    public static Path getSnapshotFilepath(long id) {
        return SNAPSHOT_FILEPATH.resolve(String.format("workshop-%s.json", STANDARD_DATE_FORMAT.format(new Date(id))));
    }

    /**
     * Salva um objeto no formato Json em um arquivo.
     * 
     * @param <T>      tipo do objeto a ser salvo.
     * @param object   objeto a ser salvo.
     * @param filename caminho do arquivo onde o objeto será salvo.
     * @param type     tipo do objeto a ser salvo, utilizado para serialização.
     * @return {@code true} se o objeto foi salvo com sucesso, {@code false} caso
     *         contrário.
     */
    public static <T> boolean save(T object, Path filename, TypeToken<T> type) {
        try {

            File file = filename.toFile();
            file.getParentFile().mkdirs();
            file.createNewFile();

            FileWriter writer = new FileWriter(file);
            GSON.toJson(object, type.getType(), writer);

            writer.flush();
            writer.close();

        } catch (IOException err) {
            return true;
        }

        return true;
    }

    /**
     * Carrega um objeto do formato Json de um arquivo.
     * 
     * @param <T>      tipo do objeto a ser carregado.
     * @param filename caminho do arquivo de onde o objeto será carregado.
     * @param type     tipo do objeto a ser carregado, utilizado para
     *                 deserialização.
     * @return objeto carregado, ou {@code null} se não foi possível carregar.
     */
    public static <T> T load(Path filename, TypeToken<T> type) {
        try {

            FileReader reader = new FileReader(filename.toFile());
            T object = GSON.fromJson(reader, type.getType());
            
            reader.close();
            return object;

        } catch (IOException err) {
            return null;
        }
    }

    /**
     * Retorna uma representação textual do manipulador de Json.
     * 
     * @return representação textual do manipulador de Json.
     */
    @Override
    public String toString() {
        return "()";
    }
}