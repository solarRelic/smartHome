package events;

import java.util.ArrayList;
import java.util.List;

import home.Home;


/**
 * Publishes and manages events in the system.
 */
public class EventPublisher {
    private List<Event> events = new ArrayList<>();
    private static int iteration;
    private final EventManager eventManager;


    public EventPublisher(Home home) {
        this.eventManager = home.getEventManager();
    }



    public void nextIteration() {
        iteration++;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public void newEvent(Event event) {
        event.setIteration(iteration);
        events.add(event);
    }

    /**
     * Updates an existing event in the publisher.
     * @param event The event to be updated.
     */
    public void updateEvent(Event event) {
        if (events.contains(event)) {
            events.set(events.indexOf(event), event);
        }
    }

    public void updateFromAlertGenerator(Alert alert){
        alert.setTarget(eventManager);
        eventManager.add2AlertsQ(alert);
        if(!events.contains(alert))
            newEvent(alert);
        else
            updateEvent(alert);
    }

    public void recordEvent(ReportInfo reportInfo) {
        newEvent(reportInfo);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void updateFromResident(ReportInfo reportInfo) {
        newEvent(reportInfo);
    }

    public void updateFromMeasurer(Consumption consumption){
        newEvent(consumption);
    }
}
