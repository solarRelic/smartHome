package home;

import events.EventTarget;
import events.Observer;
import events.Observable;
import resident.Resident;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room in a home.
 * This is an abstract class that provides common functionalities for different types of rooms.
 */

public abstract class Room implements EventTarget, Observable {
    protected String name;
    private final Floor floor;
    private List<Resident> residents;
    private List<Window> windows;
    private List<Door> doors;
    private final List<Observer> observers;
    private boolean isBurning;


    protected Room(String name, Floor floor, int numberOfWindows, int numberOfDoors) {
        this.name = name;
        this.floor = floor;
        floor.addRoom(this);
        makeWindows(numberOfWindows);
        makeDoors(numberOfDoors);
        residents = new ArrayList<>();
        observers = new ArrayList<>();
    }

    protected void makeWindows(int numberOfWindows) {
        windows = new ArrayList<>();
        for (int i = 1; i <= numberOfWindows; i++) {
            windows.add(new Window(i, this));
        }
    }

    protected void makeDoors(int numberOfDoors) {
        doors = new ArrayList<>();
        for (int i = 1; i <= numberOfDoors; i++) {
            doors.add(new Door(i, this));
        }
    }

    public List<Window> getWindows() {
        return windows;
    }


    public List<Door> getDoors() {
        return doors;
    }

    /**
     * Sets the room on fire and notifies observers.
     */
    public void setOnFire() {
        this.isBurning = true;
        broadcast();
    }

    public boolean isBurning() {
        return isBurning;
    }

    public void putOutFire(){
        this.isBurning = false;
    }

    public void addResident(Resident resident) {
        residents.add(resident);
    }

    public void removeResident(Resident resident) {
        residents.remove(resident);
    }

    public List<Resident> getResidents() {
        if (residents == null) {
            residents = new ArrayList<>();
        }
        return residents;
    }

    /**
     * Subscribes an observer to receive updates from the room.
     * @param observer The observer to be subscribed.
     */
    public void subscribe(Observer observer) {
        if(!observers.contains(observer))
            observers.add(observer);
    }

    /**
     * Unsubscribes an observer from receiving updates from the room.
     * @param observer The observer to be unsubscribed.
     */
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    /**
     *  Broadcasts a notification to all subscribed observers.
     */
    public void broadcast() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public Floor getFloor() {
        return floor;
    }

    public String toString() {
        return name;
    }
}
