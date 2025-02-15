package devices.device_state;

/**
 * Represents the ON state of a device.
 */
public class StateON extends State{
    public StateON(Context context) {
        super(context);
        this.state = DeviceState.ON;
    }

}
