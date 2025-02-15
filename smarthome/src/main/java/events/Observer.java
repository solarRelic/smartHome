package events;

/**
 * An interface representing an observer that can receive updates from observables.
 *
 */
public interface Observer {

    /**
     * Called by an observable to notify the observer about changes.
     * @param observable The observable that triggered the update.
     */
    void update(Observable observable);
}
