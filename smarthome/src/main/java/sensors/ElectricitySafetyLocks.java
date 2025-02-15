package sensors;

import events.*;
import home.Home;
import sensors.consumptions.ElectricitySensor;

/**
 * Represents a sensor monitoring electricity safety and generating alerts.
 *
 */
public class ElectricitySafetyLocks implements Sensor, AlertGenerator {
    private boolean electricityInterrupted;
    private final EventPublisher eventPublisher;
    private static final  double BREAKAGE_THRESHOLD = 6;

    public ElectricitySafetyLocks(Home home) {
        home.setElectricitySafetyLocks(this);
        eventPublisher = home.getEventPublisher();
    }

    public void reset() {
        electricityInterrupted = false;
    }

    public boolean isElectricityInterrupted() {
        return electricityInterrupted;
    }

    @Override
    public void update(Observable observable) {
        if (observable instanceof ElectricitySensor &&
                ((ElectricitySensor) observable).getElectricityConsumptionOnIteration() > BREAKAGE_THRESHOLD) {
            electricityInterrupted = true;
            newAlert(new Alert(AlertType.NO_ELECTRICITY, this, null, null, null));
        }


    }
    @Override
    public void newAlert(Alert alert) {
        eventPublisher.updateFromAlertGenerator(alert);
    }
}
