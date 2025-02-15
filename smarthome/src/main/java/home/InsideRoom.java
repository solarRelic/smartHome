package home;

import devices.Device;
import sensors.Sensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room inside a home, extending the functionality of the base Room class.
 * This class specifically includes devices and sensors associated with the room.
 */
public class InsideRoom extends Room{
    private final List<Device> devices;
    private final List<Sensor> sensors;

    public InsideRoom(String name, Floor floor, int numberOfWindows, int numberOfDoors) {
        super(name, floor, numberOfWindows, numberOfDoors);
        devices = new ArrayList<>();
        sensors = new ArrayList<>();
    }

    public void addDevice(Device device) {
            devices.add(device);
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }


    public List<Device> getDevices() {
        return devices;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    @Override
    public String toString() {
        return name;
    }
}
