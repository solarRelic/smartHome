package devices.device_state;

/**
 * Represents the OFF state of a device.
 */
public class StateOFF extends State{

    public StateOFF(Context context) {
        super(context);
        this.state = DeviceState.OFF;
    }

}
