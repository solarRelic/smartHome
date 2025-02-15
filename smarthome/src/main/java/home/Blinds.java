package home;

import events.*;

/**
 *Represents blinds associated with a window, providing control and handling events.
 */
public class Blinds implements AlertHandler, EventSource, EventTarget {
    private boolean closed = false;
    private final EventPublisher eventPublisher;
    private final Window window;

    public Blinds(Window window) {
        this.window = window;
        eventPublisher = window.getRoom().getFloor().getHome().getEventPublisher();
        addHandler2EventManager(window.getRoom().getFloor().getHome().getEventManager());
    }

    /**
     * Pulls the blinds, changes their state, and records the event.
     */
    public void pullBlinds() {
        eventPublisher.recordEvent(new ReportInfo(EventType.PULLING_BLINDS, this, this,window.getRoom().getFloor() , window.getRoom()));
        this.closed = !closed;
    }

    /**
     * Handles alerts, specifically responding to wind alerts by adjusting the blinds.
     * @param alert The alert to be handled.
     * @return {@code true} if the blinds were adjusted, {@code false} otherwise.
     */
    @Override
    public boolean handleAlert(Alert alert) {
        boolean ret = false;
        if (!closed && alert.getType() == AlertType.WIND) {
            pullBlinds();
            ret = true;
        } else if (closed && alert.getType() != AlertType.WIND) {
            pullBlinds();
            ret = true;
        }
        return ret;
    }

    /**
     * Adds the blinds as an alert handler to the event manager.
     * @param eventManager The event manager to which the blinds are added as an alert handler.
     */
    @Override
    public void addHandler2EventManager(EventManager eventManager) {
        eventManager.addAlertHandler(this);
    }

    @Override
    public String toString() {
        return "Blinds";
    }
}

