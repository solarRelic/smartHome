package devices.device_state;

/**
 *  Represents the IDLE state of a device.
 */
public class StateIDLE extends State {
    public StateIDLE(Context context) {
        super(context);
        this.state = DeviceState.IDLE;
    }

}
