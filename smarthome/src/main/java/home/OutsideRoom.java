package home;

import devices.equipment.Equipment;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room located outside a home, extending the functionality of the base Room class.
 * This class specifically includes equipment and a car associated with the room.
 */
public class OutsideRoom extends Room{
    private final List<Equipment> equipments;
    private Car car;

    public OutsideRoom(String name, Floor floor, int numberOfWindows, int numberOfDoors) {
        super(name, floor, numberOfWindows, numberOfDoors);
        equipments = new ArrayList<>();
    }

    public void addCar(Car car) {
        this.car = car;
    }
    public Car getCar() {
        return car;
    }

    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
    }


    public List<Equipment> getEquipments() {
        return equipments;
    }

    @Override
    public String toString() {
        return name;
    }
}
