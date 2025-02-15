package home;

/**
 * Represents a window within a room.
 */
public class Window {
    private final Room room;

    public Window(int amount, Room room) {
        this.room = room;
        Blinds blinds = new Blinds(this);
    }

    public Room getRoom() {
        return room;
    }
}
