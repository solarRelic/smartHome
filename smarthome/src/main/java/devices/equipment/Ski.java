package devices.equipment;

import home.OutsideRoom;
import resident.human.Human;
import resident.human.Usable;

import java.util.Optional;

/**
 * Represents a ski as a type of equipment.
 */
public class Ski extends Equipment{
    private static final int TRIP_DURATION = 4;
    private int curDuration = 0;
    public Ski(String name, OutsideRoom location) {
        super(name, location);
    }

    /**
     * Simulates the usage of the ski by a human.
     *
     * @param human The human using the ski.
     * @return The ski if the trip is ongoing; otherwise, null.
     */
    @Override
    public Optional<Usable> use(Human human) {
        isAvailable = false;
        if(curDuration < TRIP_DURATION) {
            curDuration++;
            return Optional.of(this);
        }
        curDuration = 0;
        isAvailable = true;
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "Ski{" +
                "name='" + name + '\'' +
                ", location=" + location +
                '}';
    }

}
