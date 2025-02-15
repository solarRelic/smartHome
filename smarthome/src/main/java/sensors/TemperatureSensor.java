package sensors;

import events.*;
import home.Home;
import home.OutsideWorld;
import sensors.temperature_strategy.*;

/**
 * Represents a temperature sensor monitoring the outside world and generating temperature-related events.
 */
public class TemperatureSensor implements Sensor{
    private String name;
    private int temperature;
    private final Home home;
    private static final int COLD_TEMPERATURE = 16;
    private static final int FREEZE = 10;
    private static final int WARM_TEMPERATURE = 22;

    private final EventPublisher eventPublisher;


    public TemperatureSensor(Home home) {
        this.home = home;
        setTemperature(home.getOutsideWorld().getTemperature());
        home.getOutsideWorld().subscribe(this);
        eventPublisher = home.getEventPublisher();
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    /**
     * Starts the temperature strategy based on the given temperature.
     * @param temperature The temperature to determine the strategy.
     * @return The selected temperature strategy.
     */
    public Strategy startStrategy(int temperature) {
        if (temperature < FREEZE) {
            return new FreezeTemperature(this);
        } else if (temperature < COLD_TEMPERATURE) {
            return new ColdTemperature(this);
        } else if (temperature < WARM_TEMPERATURE) {
            return new WarmTemperature(this);
        } else
            return new HotTemperature(this);

    }

    @Override
    public void update(Observable observable) {
        if(observable instanceof OutsideWorld outsideWorld){
            Strategy strategy = startStrategy(outsideWorld.getTemperature());
            eventPublisher.recordEvent(
                    new ReportInfo(EventType.TEMPERATURE, this, strategy, null, null));
        }
    }

    @Override
    public String toString() {
        return "TemperatureSensor{" +
                ", temperature=" + temperature +
                '}';
    }
}