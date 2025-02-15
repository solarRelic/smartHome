package sensors;

import events.EventSource;
import events.EventTarget;
import events.Observer;

/**
 * An interface representing a sensor that can observe, be a target for events, and be a source of events.
 */
public interface Sensor extends Observer, EventTarget, EventSource {
}
