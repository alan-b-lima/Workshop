package edu.ajan.model;

import edu.ajan.model.exception.WorkshopException;
import edu.ajan.model.persistence.Caretaker;
import edu.ajan.model.persistence.InstanceCountState;
import edu.ajan.model.persistence.Snapshot;
import edu.ajan.model.workshop.Workshop;

/** * Classe que representa o motor central do sistema, responsável por gerenciar o
 * estado do workshop e os snapshots.
 * 
 * @author Alan Lima
 */
public class CoreEngine {

    private Caretaker caretaker;

    private Workshop workshop;

    /**
     * Construtor padrão
     */
    public CoreEngine() {
        loadState();
    }

    /**
     * Retorna o cuidador de snapshots.
     * 
     * @return o cuidador de snapshots.
     */
    public Caretaker caretaker() {
        return caretaker;
    }

    /**
     * Retorna o workshop atual.
     * 
     * @return o workshop atual.
     */
    public Workshop workshop() {
        return workshop;
    }

    /**
     * Carrega o estado do workshop a partir do cuidador de snapshots.
     * Se não houver nenhum cuidador salvo, cria um novo cuidador vazio e restaura
     * o workshop para um estado vazio.
     */
    public void loadState() {
        this.caretaker = Caretaker.load();
        restoreSnapshot();
    }

    /**
     * Salva o estado atual do workshop e captura um snapshot.
     */
    public void saveState() {
        captureSnapshot();
        Caretaker.save(this.caretaker);
    }

    /**
     * Captura um snapshot do estado atual do workshop e salva no cuidador de
     * snapshots.
     */
    public void captureSnapshot() {
        if (!caretaker.saveSnapshot(workshop, InstanceCountState.capture())) {
            throw new WorkshopException("não foi possível capturar o estado");
        }
    }

    /**
     * Restaura o estado do workshop a partir do último snapshot salvo.
     * Se não houver nenhum snapshot salvo, cria um novo workshop vazio.
     */
    public void restoreSnapshot() {
        Snapshot snapshot = caretaker.loadSnapshot();
        if (snapshot == null) {
            this.workshop = new Workshop();
            InstanceCountState.restore(null);
            return;
        }

        this.workshop = snapshot.workshop();
        InstanceCountState.restore(snapshot.instanceCountState());
    }
}
