package sensors.consumptions;

import devices.Device;
import devices.TypesOfConsumption;
import events.Consumption;
import events.EventPublisher;
import events.Observable;
import events.Observer;
import home.Home;
import sensors.Sensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sensor for monitoring electricity consumption in a home.
 */
public class ElectricitySensor implements Sensor, Observable{
    private final Home home;
    private final List<Observer> observerList;
    private final EventPublisher eventPublisher;
    private double electricityConsumptionOnIteration;


    public ElectricitySensor(Home home) {
        this.home = home;
        observerList = new ArrayList<>();
        eventPublisher = home.getEventPublisher();
        home.setElectricitySensor(this);
        if (home.getElectricitySafetyLocks() != null) {
            subscribe(home.getElectricitySafetyLocks());
        }
        subscribeToElectricDevices();
    }

    public void subscribeToElectricDevices() {
        for (Device device : home.getDeviceList()) {
            if (device.getTypeOfConsumption() == TypesOfConsumption.ELECTRICITY) {
                device.subscribe(this);
            }
        }
    }

    @Override
    public void update(Observable observable) {
        if (observable instanceof Device device) {
            eventPublisher.updateFromMeasurer(new Consumption(TypesOfConsumption.ELECTRICITY,
                    device.getConsumption(),
                    device,
                    device.getCurrentFloor(),
                    device.getCurrentRoom(),
                    this));

            electricityConsumptionOnIteration += ((Device) observable).getConsumption();
            broadcast();
        }
    }

    @Override
    public void subscribe(Observer observer) {
        if(!observerList.contains(observer))
            observerList.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void broadcast() {
        for (Observer observer : observerList) {
            observer.update(this);
        }
    }


    public double getElectricityConsumptionOnIteration() {
        return electricityConsumptionOnIteration;
    }

    public void nextIteration() {
        electricityConsumptionOnIteration = 0;
    }

    public String toString() {
        return "Electricity sensor ";
    }
}
