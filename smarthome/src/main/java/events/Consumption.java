package events;

import devices.TypesOfConsumption;
import home.Floor;
import home.Room;

/**
 * Represents a consumption of devices in a home.
 */
public class Consumption extends Event{
    private final TypesOfConsumption type;
    private final double amount;

    public Consumption(TypesOfConsumption type, double amount, EventSource source,
                       Floor floor, Room room, EventTarget target) {
        this.type = type;
        this.amount = amount;
        this.source = source;
        this.sourceFloor = floor;
        this.sourceRoom = room;
        this.target = target;
    }

    public double getConsumption() {
        return amount;
    }

    public TypesOfConsumption getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Consumption{" +
                "consumption=" + amount +
                ", sourceFloor=" + sourceFloor +
                ", sourceRoom=" + sourceRoom +
                ", target=" + target +
                '}';
    }
}
