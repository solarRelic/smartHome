package home;

/**
 * Factory for creating InsideRoom instances.
 */
public class InsideRoomFactory implements RoomFactory{
    @Override
    public InsideRoom createRoom(String name, Floor floor, int numberOfWindows, int numberOfDoors) {
        return new InsideRoom(name, floor, numberOfWindows, numberOfDoors);
    }
}
