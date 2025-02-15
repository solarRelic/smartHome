package home;

/**
 * Represents a door within a room.
 */
public class Door {
    private final int amount;
    private final Room room;


    public Door(int amount, Room room) {
        this.amount = amount;
        this.room = room;
    }
}
