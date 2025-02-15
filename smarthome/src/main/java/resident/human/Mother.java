package resident.human;

import events.*;
import home.Car;
import home.Home;
import home.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Mother extends Human implements Adult{
    private static final int FOOD_CONSUMPTION = 8;
    private static final int STOP_CRYING_PROBABILITY = 90;

    private final List<Baby> babies = new ArrayList<>();


    public Mother(String name) {
        super(name);
    }

    public void addBaby(Baby baby) {
        if(!babies.contains(baby))
            babies.add(baby);
    }
    @Override
    public boolean handleAlert(Alert alert) {
        boolean ret = false;
        if (isAvailable) {
            isAvailable = false;
            switch (alert.getType()) {
                case BABY_CRYING -> {
                    List<Baby> cryingRabies = babies.stream()
                            .filter(baby -> !baby.isHappy())
                            .toList();
                    if (!cryingRabies.isEmpty()) {
                        int rand = new Random().nextInt(cryingRabies.size());
                        Baby randBabyToCalmDown = cryingRabies.get(rand);
                        calmDownBaby(randBabyToCalmDown);
                    }
                    ret = true;
                }
                case FIRE -> {
                    List<Room> roomsOnFire = home.getRoomList()
                            .stream()
                            .filter(Room::isBurning)
                            .toList();
                    if (!roomsOnFire.isEmpty()) {
                        int rand = new Random().nextInt(roomsOnFire.size());
                        callFirefighters(roomsOnFire.get(rand));
                    }
                    ret = true;
                }
                case NO_PROVISIONS -> {
                    List<Car> carsAtHome = home.getCars()
                            .stream()
                            .filter(Car::isInGarage)
                            .toList();
                    if (!carsAtHome.isEmpty()) {
                        int rand = new Random().nextInt(carsAtHome.size());
                        Car carToDrive = carsAtHome.get(rand);
                        driveCar(carToDrive);
                    }
                    ret = true;
                }
            }
        }
        isAvailable = true;
        return ret;
    }

    public void calmDownBaby(Baby baby) {
        recordEvent(new ReportInfo(EventType.CALMING_DOWN_BABY_MOTHER, this, baby, getFloor(), currentLocation));
        baby.stopCrying(STOP_CRYING_PROBABILITY);
    }

    public void callFirefighters(Room room) {
        recordEvent(new ReportInfo(EventType.CALLING_FIRE_FIGHTERS, this, room, getFloor(), currentLocation));
        room.putOutFire();
    }

    public void driveCar(Car car) {
        isAvailable = false;
        car.setAvailable(false);
        usedBy = car.use(this);
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
