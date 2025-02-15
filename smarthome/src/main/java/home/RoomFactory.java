package home;

/**
 * Interface for creating instances of the Room class.
 */
public interface RoomFactory {
    Room createRoom(String name, Floor floor, int numberOfWindows, int numberOfDoors);
}
