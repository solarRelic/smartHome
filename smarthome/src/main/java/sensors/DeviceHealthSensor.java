package sensors;

import devices.Device;
import devices.TypesOfConsumption;
import events.*;
import home.Home;

/**
 * Represents a sensor monitoring the health of devices and generating alerts.
 */
public class DeviceHealthSensor implements Sensor, AlertGenerator {
    private final Home home;
    private final EventPublisher eventPublisher;


    public DeviceHealthSensor(Home home) {
        this.home = home;
        eventPublisher = home.getEventPublisher();
        attachToDevices();
    }

    public void attachToDevices() {
        for (Device device : home.getDeviceList()) {
            device.subscribe(this);

        }
    }

    /**
     * Updates the sensor when a device's state changes and generates an alert or consumption event accordingly.
     * @param observable The observable object.
     */
    @Override
    public void update(Observable observable) {
        if (observable instanceof Device device) {
            if ((device.isBroken())) {
                newAlert(new Alert(AlertType.BROKEN,
                        device,
//                        this,
                        null,
                        (device.getCurrentFloor()),
                        (device.getCurrentRoom())));
            } else {
                newConsumption(new Consumption(TypesOfConsumption.DETERIORATION,
                        (device.getWearDegree()),
                        this,
                        device.getCurrentFloor(),
                        device.getCurrentRoom(),
                        this));
            }
        }
    }

    @Override
    public void newAlert(Alert alert) {
        eventPublisher.updateFromAlertGenerator(alert);
    }

    public void newConsumption(Consumption consumption) {
        eventPublisher.updateFromMeasurer(consumption);
    }

    @Override
    public String toString() {
        return "Babysitter ";
    }
}
