package devices;


/**
 * Represents the manual for a device.
 */
public class Manual {
    private static Manual manual;
    private final String description;

    private Manual() {
        description = "Manual desc...";
    }

    public static Manual findManual() {
        if (manual == null) {
            manual = new Manual();
        }
        return manual;
    }
}
