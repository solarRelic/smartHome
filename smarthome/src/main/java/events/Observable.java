package events;

/**
 *An interface representing an observable entity that allows observers to subscribe, unsubscribe,
 * and be notified of changes.
 */
public interface Observable extends EventTarget {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void broadcast();
}
