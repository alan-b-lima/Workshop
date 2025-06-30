package edu.ajan.model.persistence;

import java.util.Stack;

import com.google.gson.reflect.TypeToken;

/**
 * Classe que representa o curador de snapshots.
 * 
 * @author Alan Lima
 */
public class Caretaker {

    /**
     * Tipo de dado para serialização do curador de snapshots.
     */
    private static final TypeToken<Caretaker> CARETAKER_TYPE = new TypeToken<>() {};

    /**
     * Tipo de dado para serialização de snapshots.
     */
    private static final TypeToken<Snapshot> SNAPSHOT_TYPE = new TypeToken<>() {};

    /**
     * Pilha que armazena os identificadores dos snapshots salvos.
     */
    private Stack<Long> history;

    /**
     * Curador de snapshots, responsável por gerenciar o estado da oficina.
     */
    private static Caretaker caretaker;

    /**
     * Construtor padrão.
     */
    private Caretaker() {
        this.history = new Stack<>();
    }

    /**
     * Retorna a instância única do curador de snapshots.
     * 
     * @return instância única do curador de snapshots.
     */
    public static Caretaker caretaker() {
        return caretaker;
    }

    /**
     * Salva um snapshot do estado atual da oficina.
     * 
     * @param snapshot o snapshot a ser salvo.
     */
    public void saveSnapshot(Snapshot snapshot) {
        long id = generateNextId();
        if (JsonHandler.save(snapshot, JsonHandler.getSnapshotFilepath(id), SNAPSHOT_TYPE)) {
            history.push(id);
        }
    }

    /**
     * Carrega o último snapshot salvo, retornando o primeiro snapshot.
     * 
     * @return o último snapshot salvo, ou {@code null} se não houver nenhum
     *         snapshot salvo.
     */
    public Snapshot loadSnapshot() {

        // Retorna até achar uma snapshot válida, só desistindo se não achar nada
        while (hasSnapshots()) {
            long id = history.peek();

            Snapshot snapshot = JsonHandler.load(JsonHandler.getSnapshotFilepath(id), SNAPSHOT_TYPE);
            if (snapshot != null) {
                return snapshot;
            }

            history.pop();
        }

        return null;
    }

    /**
     * Gera um novo identificador para o snapshot.
     * 
     * @return um novo identificador único baseado no tempo atual em milissegundos.
     */
    private long generateNextId() {
        return System.currentTimeMillis();
    }

    /**
     * Retorna uma estrutura iterável com os identificadores dos snapshots salvos.
     * 
     * @return estrutura iterável com os identificadores dos snapshots salvos.
     */
    public Iterable<Long> getHistory() {
        return history;
    }

    /**
     * Verifica se o curador possui algum snapshot salvo.
     * 
     * @return {@code true} se houver pelo menos um snapshot salvo, {@code false}
     *         caso contrário.
     */
    public boolean hasSnapshots() {
        return !history.isEmpty();
    }

    /**
     * Limpa o histórico de snapshots.
     */
    public void clearHistory() {
        history.clear();
    }

    /**
     * Salva o curador de snapshots no arquivo padrão.
     * 
     * @return {@code true} se o curador foi salvo com sucesso, {@code false}
     *         caso contrário.
     */
    public static boolean save() {
        return JsonHandler.save(caretaker, JsonHandler.getCaretakerFilepath(), CARETAKER_TYPE);
    }

    /**
     * Carrega o curador de snapshots do arquivo padrão.
     * Se não houver nenhum curador salvo, retorna um novo curador.
     */
    public static void load() {
        caretaker = JsonHandler.load(JsonHandler.getCaretakerFilepath(), CARETAKER_TYPE);
        if (caretaker == null) {
            caretaker = new Caretaker();
        }
    }
}
