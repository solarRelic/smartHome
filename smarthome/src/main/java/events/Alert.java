package events;

import home.Floor;
import home.Room;

/**
 * Represents an alert event in a home automation system.
 */
public class Alert extends Event{
    private final AlertType type;

    /**
     * Constructs an Alert object.
     * @param alertType The type of the alert.
     * @param src The source of the alert.
     * @param trg The target of the alert.
     * @param floor The floor where the alert originated.
     * @param room The room where the alert originated.
     */
    public Alert(AlertType alertType, EventSource src, EventTarget trg,
                 Floor floor, Room room) {
        this.type = alertType;
        this.source = src;
        this.target = trg;
        this.sourceFloor = floor;
        this.sourceRoom = room;
    }

    public AlertType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "type=" + type +
                ", source=" + source.toString() +
                ", target=" + target.toString() +
                '}';
    }
}
