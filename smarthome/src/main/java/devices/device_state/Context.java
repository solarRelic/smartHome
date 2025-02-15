package devices.device_state;

/**
 * An interface representing the context in the state pattern.
 */
public interface Context {
    void setState(State state);
}
