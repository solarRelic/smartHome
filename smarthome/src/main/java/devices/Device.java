package devices;

import devices.device_state.*;
import events.*;
import home.Floor;
import home.InsideRoom;
import home.Room;
import resident.human.Human;
import resident.human.Usable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * An abstract class representing a device in a home.
 */
public abstract class Device implements AlertHandler, Observable, Context, EventSource, Usable {
    String name;
    protected Room currentRoom;
    private Floor currentFloor;
    private final TypesOfConsumption typeOfConsumption;
    private boolean isAvailable;
    protected boolean isBroken;
    protected double[] consumption;
    protected int wearDegree;
    private final List<Observer> observersList = new ArrayList<>();
    private State state = new StateOFF(this);
    protected EventPublisher eventPublisher;
    private static final int PROBABILITY = 5;


    protected Device(String name, InsideRoom room, TypesOfConsumption typeOfConsumption, double[] consumption) {
        this.name = name;
        this.currentRoom = room;
        this.typeOfConsumption = typeOfConsumption;
        this.consumption = consumption;

        isAvailable = true;
        room.addDevice(this);

        addHandler2EventManager(room.getFloor().getHome().getEventManager());
        eventPublisher = room.getFloor().getHome().getEventPublisher();
    }


    public int getWearDegree() {
        return wearDegree;
    }

    public void breakDown(){
        isBroken = true;
    }

    public boolean isBroken(){
        return isBroken;
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    /**
     *  Handles an alert received by the device.
     * @param alert The alert to be handled.
     * @return True if the alert is successfully handled, false otherwise.
     */
    public boolean handleAlert(Alert alert) {
        boolean ret = false;
        if(alert.getType() == AlertType.NO_ELECTRICITY){
            recordEvent(new ReportInfo(EventType.UNEXPECTED_TURNING_OFF_DEVICE,this, this, currentFloor , currentRoom));
            turnOFF();
            broadcast();
            ret = true;
        }
        return ret;
    }

    /**
     * Records an event related to the device.
     * @param reportInfo The information about the event to be recorded.
     */
    public void recordEvent(ReportInfo reportInfo) {
        eventPublisher.recordEvent(reportInfo);
    }

    public abstract void decreaseDurability();

    /**
     * Performs the usage of the device by a human.
     *
     * @param human The human using the device.
     * @return The result of the usage operation.
     */
    public abstract Optional<Usable> use(Human human);

    /**
     *  Sets the device and consequently the room on fire with the pst of 5%.
     */
    public void nextIteration(){
        if(new Random().nextInt(100) < PROBABILITY)
            setOnFire();
    }

    /**
     *  Sets the device's current room on fire.
     */
    public void setOnFire() {
        currentRoom.setOnFire();
    }

    public DeviceState getDeviceState() {
        return state.getState();
    }

    /**
     * Turns on the device, records event and changes state.
     */
    public void turnON(){
        eventPublisher.recordEvent(new ReportInfo(EventType.TURNING_ON_DEVICE, this, this, currentFloor, currentRoom));
        setState(new StateON(this));
    }

    /**
     * Turns the device into an idle state, records event and changes state.
     */
    public void turnIdle(){
        eventPublisher.recordEvent(new ReportInfo(EventType.TURNING_ON_IDLE_DEVICE, this, this, currentFloor, currentRoom));
        setState(new StateIDLE(this));
    }

    /**
     * Turns off the device, records event and changes state.
     */
    public void turnOFF(){
        eventPublisher.recordEvent(new ReportInfo(EventType.TURNING_OF_DEVICE, this, this, currentFloor, currentRoom));
        setState(new StateOFF(this));
    }

    public void setState(State state) {
        this.state = state;
    }

    /**
     * Subscribes an observer to the device.
     * @param observer The observer to subscribe.
     */
    public void subscribe(Observer observer){
        if(!observersList.contains(observer))
            observersList.add(observer);
    }

    /**
     * Unsubscribes an observer from the device.
     * @param observer The observer to unsubscribe.
     */
    public void unsubscribe(Observer observer){
        observersList.remove(observer);
    }

    /**
     * Adds the device as an alert handler to the event manager.
     * @param eventManager The event manager to add the alert handler to.
     */
    public void addHandler2EventManager(EventManager eventManager) {
        eventManager.addAlertHandler(this);
    }

    /**
     * Broadcasts an update to all subscribed observers.
     */
    public void broadcast(){
        for (Observer observer : observersList) {
            observer.update(this);
        }
    }

    /**
     * Gets the energy consumption of the device based on its current state.
     * @return The energy consumption value.
     */
    public double getConsumption() {
        if(this.state.getState() == DeviceState.ON){
            return this.consumption[0];
        } else if (this.state.getState() == DeviceState.IDLE){
            return this.consumption[1];
        } else if (this.state.getState() == DeviceState.OFF){
            return this.consumption[2];
        } else {
            return 0;
        }
    }

    public void setConsumption(double[] consumption) {
        this.consumption = consumption;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public TypesOfConsumption getTypeOfConsumption() {
        return typeOfConsumption;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Manual findDeviceManual() {
        return Manual.findManual();
    }

    public void repairDevice() {
        wearDegree = 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
