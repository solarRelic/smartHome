package home;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a floor in a home, containing multiple rooms.
 */
public class Floor {
    private final String name;
    private final Home home;
    private final List<Room> rooms;

    /**
     * Constructs a floor with a given name and associated home.
     * @param name The name of the floor.
     * @param home The home to which the floor belongs.
     */

    public Floor(String name, Home home) {
        this.name = name;
        this.home = home;
        rooms = new ArrayList<>();
        home.addFloor(this);
    }

    public Home getHome() {
        return home;
    }

    public List<Room> getRooms() {
        return rooms;
    }
    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public String toString() {
        return name;
    }
}
