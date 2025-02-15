package events;

/**
 * An interface for classes that generate alerts in a home automation system.
 */
public interface AlertGenerator {
    void newAlert(Alert alert);
}
