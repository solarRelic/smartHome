package resident.human;

import events.AlertHandler;
import events.EventSource;
import home.Car;

/**
 *  Interface representing an adult resident in a home.
 */
public interface Adult extends AlertHandler, EventSource {
    /**
     *
     * @param baby
     */
    void addBaby(Baby baby);

    /**
     * Represents event to stop crying baby accordingly to persons probability to calm down.
     * @param baby
     */
    void calmDownBaby(Baby baby);
    void driveCar(Car car);

}
