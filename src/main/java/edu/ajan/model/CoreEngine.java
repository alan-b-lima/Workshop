package edu.ajan.model;

import edu.ajan.model.exception.WorkshopException;
import edu.ajan.model.persistence.Caretaker;
import edu.ajan.model.persistence.InstanceCountState;
import edu.ajan.model.persistence.Snapshot;
import edu.ajan.model.workshop.Workshop;

public class CoreEngine {

    private Caretaker caretaker;

    private Workshop workshop;

    public CoreEngine() {
        loadState();
    }

    public Caretaker caretaker() {
        return caretaker;
    }

    public Workshop workshop() {
        return workshop;
    }

    public void loadState() {
        this.caretaker = Caretaker.load();
        restoreSnapshot();
    }

    public void saveState() {
        captureSnapshot();
        Caretaker.save(this.caretaker);
    }

    public void captureSnapshot() {
        if (!caretaker.saveSnapshot(workshop, InstanceCountState.capture())) {
            throw new WorkshopException("não foi possível capturar o estado");
        }
    }

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
