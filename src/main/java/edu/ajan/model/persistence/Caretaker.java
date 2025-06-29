package edu.ajan.model.persistence;

import java.util.Stack;

import com.google.gson.reflect.TypeToken;

import edu.ajan.model.workshop.Workshop;

public class Caretaker {

    private static final TypeToken<Caretaker> CARETAKER_TYPE = new TypeToken<>() {
    };

    private static final TypeToken<Snapshot> SNAPSHOT_TYPE = new TypeToken<>() {
    };

    private Stack<Long> history;

    private Caretaker() {
        this.history = new Stack<>();
    }

    public boolean saveSnapshot(Workshop workshop, InstanceCountState state) {
        long id = generateNextId();
        Snapshot snapshot = new Snapshot(workshop, state);

        if (JsonHandler.save(snapshot, JsonHandler.getSnapshotFilepath(id), SNAPSHOT_TYPE)) {
            history.push(id);
            return true;
        }

        return false;
    }

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

    private long generateNextId() {
        return System.currentTimeMillis();
    }

    public Iterable<Long> getHistory() {
        return history;
    }

    public static boolean save(Caretaker caretaker) {
        return JsonHandler.save(caretaker, JsonHandler.getCaretakerFilepath(), CARETAKER_TYPE);
    }

    public static Caretaker load() {
        Caretaker caretaker = JsonHandler.load(JsonHandler.getCaretakerFilepath(), CARETAKER_TYPE);
        if (caretaker == null) {
            return new Caretaker();
        }

        return caretaker;
    }
}
