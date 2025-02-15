package sensors;

import events.*;
import home.Home;
import home.Room;

/**
 * Represents a smoke sensor monitoring rooms for potential fires and generating alerts.
 */
public class SmokeSensor implements Sensor, AlertGenerator {
    private final Home home;
    private final EventPublisher eventPublisher;

    public SmokeSensor(Home home) {
        this.home = home;
        eventPublisher = home.getEventPublisher();

        putInRooms();
    }

    /**
     * Attaches the sensor to all rooms in the home for monitoring.
     */
    public void putInRooms() {
        for (Room room : home.getRoomList()) {
            room.subscribe(this);
        }
    }

    @Override
    public void update(Observable observable) {
        if (observable instanceof Room room && room.isBurning()) {
            newAlert(new Alert(AlertType.FIRE, this, null, null, null));
        }
    }

    @Override
    public void newAlert(Alert alert) {
        eventPublisher.updateFromAlertGenerator(alert);
    }

    public String toString() {
        return "FireSensor ";
    }
}
