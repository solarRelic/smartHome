package devices.device_state;

/**
 * An abstract class representing the state in a state pattern.
 */
public abstract class State {
    protected Context context;
    protected DeviceState state;

    protected State(Context context) {
        this.context = context;
    }


    public DeviceState getState() {
        return state;
    }

}
