package sensors;

import events.*;
import home.Home;
import resident.human.Baby;

import java.util.List;

/**
 * Represents a baby monitoring sensor that observes babies and generates alerts if a baby is crying.
 */
public class BabyMonitoring implements Sensor, AlertGenerator {
    private final Home home;
    private final EventPublisher eventPublisher;


    public BabyMonitoring(Home home) {
        this.home = home;
        eventPublisher = home.getEventPublisher();
        attachToBabies();
    }

    /**
     * Attaches the sensor to babies in the home, subscribing to their updates.
     */
    public void attachToBabies() {
        List<Baby> babies = home.getHumanList()
                .stream()
                .filter(Baby.class::isInstance)
                .map(Baby.class::cast)
                .toList();
        for (Baby baby : babies) {
            baby.subscribe(this);
        }
    }


    /**
     * Updates the sensor when a baby's state changes and generates an alert if the baby is crying.
     * @param observable The observable object (baby).
     */
    @Override
    public void update(Observable observable) {
        if(observable instanceof Baby baby && !(baby.isHappy()))
            newAlert(new Alert(AlertType.BABY_CRYING,this, null, null, null));
    }

    @Override
    public void newAlert(Alert alert) {
        eventPublisher.updateFromAlertGenerator(alert);
    }

    @Override
    public String toString() {
        return "Babysitter ";
    }
}
