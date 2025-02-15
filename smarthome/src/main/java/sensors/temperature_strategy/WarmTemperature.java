package sensors.temperature_strategy;

import sensors.TemperatureSensor;

public class WarmTemperature extends Strategy{
    private static final int COOLING = 5;

    public WarmTemperature(TemperatureSensor sensor) {
        super(sensor);
        changeTemperature();
    }

    @Override
    public String toString() {
        return "Decreasing the temperature on 5 degrees...";
    }

    @Override
    public void changeTemperature() {
        this.sensor.setTemperature(this.sensor.getTemperature() - COOLING);
    }
}
