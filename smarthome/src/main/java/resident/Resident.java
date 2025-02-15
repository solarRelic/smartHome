package resident;

import events.EventPublisher;
import events.EventSource;
import events.EventTarget;
import events.ReportInfo;
import resident.human.Usable;
import home.Floor;
import home.Home;
import home.Room;

import java.util.Optional;
import java.util.Random;

/**
 * Abstract class representing a resident in a home.
 */
public abstract class Resident implements EventSource, EventTarget{
    protected String name;
    protected Room currentLocation;
    protected Home home;
    protected EventPublisher eventPublisher;
    protected boolean isAvailable = true;
    protected Optional<Usable> usedBy;


    protected Resident(String name) {
        this.name = name;
    }

    /**
     * Performs actions during the next iteration.
     */
    public abstract void nextIteration();

    /**
     * Moves the resident to the specified room.
     * @param room The room to move to.
     */
    public void moveToRoom(Room room) {
        currentLocation.removeResident(this);
        currentLocation = room;
        currentLocation.addResident(this);
    }

    /**
     * Moves the resident to a random room in the home.
     * @param home The home to move into.
     */
    public void moveInHome(Home home){
        this.home = home;
        currentLocation = home.getRoomList().get(new Random().nextInt(home.getRoomList().size()));
        currentLocation.addResident(this);
        setEventPublisher(home.getEventPublisher());
    }

    /**
     *  Records an event with the associated event publisher.
     * @param reportInfo Information about the event.
     */
    public void recordEvent(ReportInfo reportInfo) {
        eventPublisher.updateFromResident(reportInfo);
    }

    /**
     * Sets the event publisher for the resident.
     * @param eventPublisher The event publisher to set.
     */
    public void setEventPublisher(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public Floor getFloor() {
        return currentLocation.getFloor();
    }
}
