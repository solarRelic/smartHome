package sensors;

import events.*;
import home.Home;
import home.OutsideWorld;

/**
 * Represents a wind sensor monitoring the outside world and generating wind-related alerts.
 *
 */
public class WindSensor implements Sensor, AlertGenerator {
    EventPublisher eventPublisher;


    public WindSensor(Home home){
        home.getOutsideWorld().subscribe(this);
        eventPublisher = home.getEventPublisher();
    }

    public void update(Observable observable){
        if(observable instanceof OutsideWorld outsideWorld) {
            if(outsideWorld.isWindy()) {
                newAlert(new Alert(AlertType.WIND, this, null, null, null));
            }
        }
    }
    @Override
    public void newAlert(Alert alert) {
        eventPublisher.updateFromAlertGenerator(alert);
    }

}
