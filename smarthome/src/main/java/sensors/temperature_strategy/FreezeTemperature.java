package sensors.temperature_strategy;

import sensors.TemperatureSensor;

public class FreezeTemperature extends Strategy{
    private static final int NORMAL_TEMPERATURE = 20;

    public FreezeTemperature(TemperatureSensor sensor) {
        super(sensor);
        changeTemperature();
    }

    @Override
    public String toString() {
        return "Freeze temperature.";
    }

    @Override
    public void changeTemperature() {
        this.sensor.setTemperature(NORMAL_TEMPERATURE);

    }
}
