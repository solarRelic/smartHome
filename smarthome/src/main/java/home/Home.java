package home;

import devices.Device;
import devices.equipment.Equipment;
import events.EventManager;
import events.EventPublisher;
import events.EventTarget;
import resident.human.Human;
import resident.pet.Pet;
import sensors.ElectricitySafetyLocks;
import sensors.consumptions.ElectricitySensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a home with multiple floors, rooms, inhabitants, and devices.
 */

public class Home implements EventTarget{
    private final String address;
    private final List<Floor> floorList;
    private final EventManager eventManager;
    private final OutsideWorld outsideWorld;
    private final EventPublisher eventPublisher;
    private ElectricitySafetyLocks electricitySafetyLocks;
    private ElectricitySensor electricitySensor;


    public Home(String address) {
        this.address = address;
        this.outsideWorld = new OutsideWorld();
        floorList = new ArrayList<>();
        eventManager = new EventManager();
        eventPublisher = new EventPublisher(this);
        electricitySafetyLocks = new ElectricitySafetyLocks(this);
    }

    /**
     * Performs the next iteration for various components in the home.
     */
    public void nextIteration() {
        outsideWorld.nextIteration();
        devicesNextIteration();
        inhabitantsNextIteration();
        electricitySensor.nextIteration();
        eventPublisher.nextIteration();
        eventManager.nextIteration();
    }

    /**
     * Iterates over each device in the home and advances them to the next iteration.
     */
    private void devicesNextIteration() {
        getDeviceList().forEach(Device::nextIteration);
    }

    /**
     * Iterates over each inhabitant in the home and advances them to the next iteration.
     */
    private void inhabitantsNextIteration() {
        getHumanList().forEach(Human::nextIteration);
        getPetList().forEach(Pet::nextIteration);
    }

    public void addFloor(Floor floor) {
        floorList.add(floor);
    }

    public List<Floor> getFloorList() {
        return floorList;
    }

    public List<Room> getRoomList() {
        return floorList.stream()
                .flatMap(floor -> floor.getRooms().stream())
                .toList();
    }

    public List<Human> getHumanList() {
        return getRoomList().stream()
                .flatMap(room -> room.getResidents().stream())
                .filter(Human.class::isInstance)
                .map(Human.class::cast)
                .toList();
    }

    public List<Pet> getPetList() {
        return getRoomList().stream()
                .flatMap(room -> room.getResidents().stream())
                .filter(Pet.class::isInstance)
                .map(Pet.class::cast)
                .toList();
    }

    public List<Device> getDeviceList() {
        return floorList.stream()
                .flatMap(floor -> floor.getRooms().stream())
                .filter(InsideRoom.class::isInstance)
                .flatMap(room -> ((InsideRoom) room).getDevices().stream())
                .toList();
    }

    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        for(Room room : getRoomList()){
            if(room instanceof OutsideRoom outsideRoom && (outsideRoom.getCar() != null)) {
                cars.add(outsideRoom.getCar());
            }
        }
        return cars;
    }

    public List<Equipment> getEquipment() {
        List<Equipment> allEquipment = new ArrayList<>();
        for (Floor floor : floorList) {
            List<Room> rooms = floor.getRooms();
            for (Room room : rooms) {
                if (room instanceof OutsideRoom outsideRoom) {
                    allEquipment.addAll(outsideRoom.getEquipments());
                }
            }
        }
        return allEquipment;
    }

    public EventPublisher getEventPublisher() {
        return eventPublisher;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public OutsideWorld getOutsideWorld() {
        return outsideWorld;
    }

    public ElectricitySafetyLocks getElectricitySafetyLocks() {
        return electricitySafetyLocks;
    }

    public void setElectricitySafetyLocks(ElectricitySafetyLocks electricitySafetyLocks) {
        this.electricitySafetyLocks = electricitySafetyLocks;
    }

    public ElectricitySensor getElectricitySensor() {
        return electricitySensor;
    }

    public void setElectricitySensor(ElectricitySensor electricitySensor) {
        this.electricitySensor = electricitySensor;
    }

    @Override
    public String toString() {
        return address;
    }
}
