package sensors.temperature_strategy;

import events.EventTarget;
import sensors.TemperatureSensor;

/**
 * Represents a temperature adjustment strategy used by a TemperatureSensor.
 */
public abstract class Strategy implements EventTarget {
    protected TemperatureSensor sensor;

    protected Strategy(TemperatureSensor sensor) {
        this.sensor = sensor;
    }

    /**
     * Abstract method to be implemented by concrete strategies to change the temperature.
     */
    public abstract void changeTemperature();
}
