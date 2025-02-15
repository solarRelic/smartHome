package events;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Manages and processes events in the system.
 */
public class EventManager implements EventTarget{
    private List<AlertHandler> alertHandlersList;
    private Queue<Alert> alertsQ;


    public EventManager(){
        alertHandlersList = new ArrayList<>();
        alertsQ = new LinkedList<>();
    }

    /**
     * Adds an alert to the alerts queue.
     * @param alert The alert to be added.
     */
    public void add2AlertsQ(Alert alert) {
        alertsQ.add(alert);
    }

    /**
     * Adds an alert handler to the manager.
     * @param alertHandler The alert handler to be added.
     */
    public void addAlertHandler(AlertHandler alertHandler) {
        if (!alertHandlersList.contains(alertHandler)) {
            alertHandlersList.add(alertHandler);
        }
    }


    public void nextIteration() {
        boolean handled = true;
        Alert alert = alertsQ.peek();
        while (alert != null && handled) {
            for (AlertHandler handler : alertHandlersList) {
                handled = handler.handleAlert(alert);
            }
            alertsQ.remove();
            alert = alertsQ.peek();
        }
    }

}
