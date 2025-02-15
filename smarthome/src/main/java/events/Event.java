package events;

import home.Floor;
import home.Room;

/**
 * Represents an abstract event in a home automation system.
 */
public abstract class Event {
    private int iteration;
    Floor sourceFloor;
    Room sourceRoom;
    EventSource source;
    EventTarget target;

    protected Event(){}

    public int getIteration() {
        return iteration;
    }

    public EventSource getSource() {
        return source;
    }

    public EventTarget getTarget() {
        return target;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public void setTarget(EventTarget target) {
        this.target = target;
    }
}
