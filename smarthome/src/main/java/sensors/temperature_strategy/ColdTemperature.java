    package sensors.temperature_strategy;

    import sensors.TemperatureSensor;

    public class ColdTemperature extends Strategy{
        private static final int WARM_UP = 5;

        public ColdTemperature(TemperatureSensor sensor) {
            super(sensor);
            changeTemperature();
        }

        @Override
        public void changeTemperature() {
            this.sensor.setTemperature(this.sensor.getTemperature() + WARM_UP);
        }

        @Override
        public String toString() {
            return "Cold temperature.";
        }
    }
