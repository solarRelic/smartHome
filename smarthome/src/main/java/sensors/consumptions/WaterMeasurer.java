package sensors.consumptions;

import devices.Device;
import devices.TypesOfConsumption;
import events.Consumption;
import events.EventPublisher;
import events.Observable;
import home.Home;
import sensors.Sensor;

/**
 * Represents a sensor for monitoring water consumption in a home.
 */
public class WaterMeasurer implements Sensor {
    private final Home home;
    private final EventPublisher eventPublisher;


    public WaterMeasurer(Home home) {
        this.home = home;
        eventPublisher = home.getEventPublisher();
        subscribeWaterDevices();
    }

    public void subscribeWaterDevices() {
        for (Device device : home.getDeviceList()) {
            if (device.getTypeOfConsumption() == TypesOfConsumption.WATER) {
                device.subscribe(this);
            }
        }
    }

    @Override
    public void update(Observable observable) {
        if (observable instanceof Device device) {
            newConsumption(new Consumption(TypesOfConsumption.WATER,
                    device.getConsumption(),
                    device,
                    device.getCurrentFloor(),
                    device.getCurrentRoom(),
                    this));
        }
    }

    public void newConsumption(Consumption consumption) {
        eventPublisher.updateFromMeasurer(consumption);
    }
}
