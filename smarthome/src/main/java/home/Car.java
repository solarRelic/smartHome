package home;

import events.*;
import resident.human.*;

import java.util.Optional;

/**
 * Represents a car that can be used and tracked for events.
 */
public class Car implements Usable, EventTarget {
    private final String naming;
    private final OutsideRoom location;
    private final EventPublisher eventPublisher;
    private boolean isAvailable = true;
    private static final  int OVERALL_USAGE_TIME = 7;
    private int curUsageTime = 0;
    private static final int CAR_TOTAL_PROVISIONS_CAPACITY = 45;
    private int carCurProvisionsSize= 0;


    public Car(String naming, OutsideRoom location) {
        this.naming = naming;
        this.location = location;
        eventPublisher = location.getFloor().getHome().getEventPublisher();
        location.addCar(this);
    }

    /**
     * Drives the car to Kaufland, recording the event.
     * @param human The human driving the car.
     */
    public void driveToKaufland(Human human) {
        eventPublisher.recordEvent(new ReportInfo(EventType.GOING_TO_KAUFLAND, human, this, location.getFloor(), location));
    }

    /**
     * Drives the car to Tesco, recording the event.
     * @param human The human driving the car.
     */
    public void driveToTesco(Human human) {
        eventPublisher.recordEvent(new ReportInfo(EventType.GOING_TO_TESCO, human, this, location.getFloor(), location));
    }

    @Override
    public Optional<Usable> use(Human human) {
        if (curUsageTime < OVERALL_USAGE_TIME) {
            curUsageTime++;
            if (human instanceof Mother) {
                driveToKaufland(human);
            } else if (human instanceof Father) {
                driveToTesco(human);
            }
            return Optional.of(this);
        } else {
            setAvailable(true);
            carCurProvisionsSize = CAR_TOTAL_PROVISIONS_CAPACITY;
            curUsageTime = 0;
            return Optional.empty();
        }
    }

    public int getCarCurProvisionsSize() {
        return carCurProvisionsSize;
    }

    public void trunkReset() {
        carCurProvisionsSize= 0;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isInGarage() {
        return isAvailable;
    }

    @Override
    public String toString() {
        return "Car{" +
                "naming='" + naming + '\'' +
                '}';
    }
}
