package resident.human;

import devices.*;
import events.Alert;
import events.EventManager;
import events.EventType;
import events.ReportInfo;
import home.Car;
import home.Home;
import home.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a father in a home.
 */
public class  Father extends Human implements Adult{
    private static final int FOODS_CONSUMPTION = 10;
    private static final int STOP_CRYING_PROBABILITY = 66;

    private final List<Baby> babies = new ArrayList<>();


    public Father(String name) {
        super(name);
    }

    /**
     * Handles different types of alerts received by the father.
     * @param alert The alert to handle.
     * @return True if the alert was handled successfully, otherwise false.
     */
    @Override
    public boolean handleAlert(Alert alert) {
        boolean ret = false;
        if (isAvailable) {
            isAvailable = false;
            switch (alert.getType()) {
                case FIRE -> {
                    List<Room> roomsOnFire = home
                            .getRoomList()
                            .stream()
                            .filter(Room::isBurning)
                            .toList();
                    if (!roomsOnFire.isEmpty()) {
                        int rand = new Random().nextInt(roomsOnFire.size());
                        Room roomToPutOut = roomsOnFire.get(rand);
                        putOutFire(roomToPutOut);
                    }
                    ret = true;
                }
                case BABY_CRYING -> {
                    List<Baby> cryingBabies = babies
                            .stream()
                            .filter(baby -> !baby.isHappy())
                            .toList();
                    if (!cryingBabies.isEmpty()) {
                        int rand = new Random().nextInt(cryingBabies.size());
                        Baby babyToCalmDown = cryingBabies.get(rand);
                        calmDownBaby(babyToCalmDown);
                    }
                    ret = true;
                }
                case NO_PROVISIONS -> {
                    List<Car> carsAtHome = home.getCars()
                            .stream()
                            .filter(Car::isInGarage)
                            .toList();
                    for (Car car : carsAtHome) {
                        if (car.getCarCurProvisionsSize() != 0) {
                            if (alert.getSource() instanceof Fridge emptyFridge) {
                                emptyFridge.recoverFood(car.getCarCurProvisionsSize());
                            } else if (alert.getSource() instanceof WashingMachine emptyWashMachine) {
                                emptyWashMachine.recoverPowder(car.getCarCurProvisionsSize());
                            } else if (alert.getSource() instanceof Dishwasher emptyDishWasher) {
                                emptyDishWasher.recoverDetergent(car.getCarCurProvisionsSize());
                            } else if (alert.getSource() instanceof CoffeeMaker emptyCoffeeMaker) {
                                emptyCoffeeMaker.recoverCoffee(car.getCarCurProvisionsSize());
                            }
                            car.trunkReset();
                        }
                    }
                    if (!carsAtHome.isEmpty()) {
                        int rand = new Random().nextInt(carsAtHome.size());
                        Car carToDrive = carsAtHome.get(rand);
                        driveCar(carToDrive);
                    }
                    ret = true;
                }
                case BROKEN -> {
                    List<Device> brokenDevices = home
                            .getDeviceList()
                            .stream()
                            .filter(Device::isBroken)
                            .toList();
                    if (!brokenDevices.isEmpty()) {
                        int rand = new Random().nextInt(brokenDevices.size());
                        Device deviceToRepair = brokenDevices.get(rand);
                        repair(deviceToRepair);
                    }
                    ret = true;

                }
                case NO_ELECTRICITY -> {
                    if (home.getElectricitySafetyLocks().isElectricityInterrupted()) {
                        home.getElectricitySafetyLocks().reset();
                    }
                    ret = true;
                }
            }
        }
        isAvailable = true;
        return ret;
    }

    /**
     * Calms down a crying baby by stopping their crying with a given probability.
     * @param baby The baby to calm down.
     */
    public void calmDownBaby(Baby baby) {
        recordEvent(new ReportInfo(EventType.CALMING_DOWN_BABY_FATHER, this, baby, getFloor(), currentLocation));
        baby.stopCrying(STOP_CRYING_PROBABILITY);
    }

    /**
     * Drives the specified car, making it unavailable for other residents temporarily.
     * @param car The car to drive.
     */
    @Override
    public void driveCar(Car car) {
        car.setAvailable(false);
        usedBy = car.use(this);
    }

    /**
     * Puts out a fire in the specified room. Moves to the room if not already present.
     * @param room The room with a fire to put out.
     */
    public void putOutFire(Room room) {
        if (currentLocation != room)
            moveToRoom(room);
        recordEvent(new ReportInfo(EventType.PUT_OUT_FIRE, this, room, getFloor(), currentLocation));
        room.putOutFire();
    }

    /**
     * Repairs the specified device by recording an event and initiating the repair process.
     * @param device The device to repair.
     */
    public void repair(Device device) {
        recordEvent(new ReportInfo(EventType.REPAIR_DEVICE, this, device, getFloor(), currentLocation));
        device.findDeviceManual();
        device.repairDevice();
    }


    public void addBaby(Baby baby){
        if(!babies.contains(baby))
            babies.add(baby);
    }


    @Override
    public void moveInHome(Home home) {
        super.moveInHome(home);
        addHandler2EventManager(home.getEventManager());
    }
    @Override
    public void addHandler2EventManager(EventManager eventManager) {
        eventManager.addAlertHandler(this);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
