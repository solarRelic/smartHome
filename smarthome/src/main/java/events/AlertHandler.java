package events;

/**
 * An interface for classes that handle alerts in a home automation system.
 */
public interface AlertHandler {

    /**
     *  Handles an alert received by the implementing class.
     * @param alert The alert to be handled.
     * @return True if the alert is successfully handled, false otherwise.
     */
    boolean handleAlert(Alert alert);

    /**
     * Adds the implementing class as an alert handler to the provided event manager.
     * @param eventManager The event manager to which the handler is added.
     */
    void addHandler2EventManager(EventManager eventManager);
}
