package events;

import home.Floor;
import home.Room;


/**
 * Represents a report event with additional information about the event type,
 */
public class ReportInfo extends Event {
    private final EventType type;

    public ReportInfo(EventType type, EventSource eventSrc, EventTarget eventTarget,
                     Floor floor, Room room) {
        this.type = type;

        this.source = eventSrc;
        this.target = eventTarget;
        this.sourceFloor = floor;
        this.sourceRoom = room;
    }

    public EventType getType() {
        return type;
    }

    @Override
    public EventSource getSource() {
        return super.getSource();
    }

    public String toString() {
        return "Event type: " + type.toString() + " | source: " + source.toString() + " , target  " + target.toString();
    }
}
