package edu.ajan.model.persistence;

import edu.ajan.model.workshop.Workshop;

public class Snapshot {

    private final InstanceCountState instanceCountState;

    private final Workshop workshop;

    private final long timestamp;

    public Snapshot(Workshop workshop, InstanceCountState state) {
        this.workshop = workshop;
        this.instanceCountState = state;
        this.timestamp = System.currentTimeMillis();
    }

    public Workshop workshop() {
        return workshop;
    }
    
    public InstanceCountState instanceCountState() {
        return instanceCountState;
    }

    public long timestamp() {
        return timestamp;
    }
}
