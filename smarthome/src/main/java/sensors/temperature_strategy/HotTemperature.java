package sensors.temperature_strategy;

import sensors.TemperatureSensor;

public class HotTemperature extends Strategy{
    private static final int NORMAL_TEMPERATURE = 20;

    public HotTemperature(TemperatureSensor sensor) {
        super(sensor);
        changeTemperature();
    }

    @Override
    public String toString() {
        return "Hot temperature.";
    }

    @Override
    public void changeTemperature() {
        sensor.setTemperature(NORMAL_TEMPERATURE);
    }
}
