package edu.ajan.model.persistence;

import java.util.Stack;

import com.google.gson.reflect.TypeToken;

import edu.ajan.model.workshop.Workshop;

/**
 * Classe que representa o cuidador de snapshots.
 * 
 * @author Alan Lima
 */
public class Caretaker {

    /**
     * Tipo de dado para serialização do cuidador de snapshots.
     */
    private static final TypeToken<Caretaker> CARETAKER_TYPE = new TypeToken<>() {
    };

    /**
     * Tipo de dado para serialização de snapshots.
     */
    private static final TypeToken<Snapshot> SNAPSHOT_TYPE = new TypeToken<>() {
    };

    /**
     * Pilha que armazena os identificadores dos snapshots salvos.
     */
    private Stack<Long> history;

    /**
     * Construtor padrão.
     */
    private Caretaker() {
        this.history = new Stack<>();
    }

    /**
     * Salva um snapshot do estado atual do workshop.
     * 
     * @param workshop o workshop do qual o snapshot será salvo.
     * @param state o estado atual do workshop, representando a contagem de instâncias.
     * @return {@code true} se o snapshot foi salvo com sucesso, {@code false}
     *         caso contrário.
     */
    public boolean saveSnapshot(Workshop workshop, InstanceCountState state) {
        long id = generateNextId();
        Snapshot snapshot = new Snapshot(workshop, state);

        if (JsonHandler.save(snapshot, JsonHandler.getSnapshotFilepath(id), SNAPSHOT_TYPE)) {
            history.push(id);
            return true;
        }

        return false;
    }

    /**
     * Carrega o último snapshot salvo, retornando o primeiro snapshot
     * @return o último snapshot salvo, ou {@code null} se não houver nenhum snapshot
     *         salvo.
     */
    public Snapshot loadSnapshot() {

        // Retorna até achar uma snapshot válida, só desistindo se não achar nada
        while (!history.isEmpty()) {
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
     * Verifica se o cuidador possui algum snapshot salvo.
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
     * Salva o cuidador de snapshots no arquivo padrão.
     * @param caretaker o cuidador a ser salvo.
     * @return {@code true} se o cuidador foi salvo com sucesso, {@code false}
     *         caso contrário.
     */
    public static boolean save(Caretaker caretaker) {
        return JsonHandler.save(caretaker, JsonHandler.getCaretakerFilepath(), CARETAKER_TYPE);
    }

    /**
     * Carrega o cuidador de snapshots do arquivo padrão.
     * Se não houver nenhum cuidador salvo, retorna um novo cuidador vazio.
     * 
     * @return o cuidador carregado, ou um novo cuidador vazio se não houver
     *         nenhum salvo.
     */
    public static Caretaker load() {
        Caretaker caretaker = JsonHandler.load(JsonHandler.getCaretakerFilepath(), CARETAKER_TYPE);
        if (caretaker == null) {
            return new Caretaker();
        }

        return caretaker;
    }
}
