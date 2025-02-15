package home;

/**
 * Factory for creating OutsideRoom instances.
 */
public class OutsideRoomFactory implements RoomFactory{
    @Override
    public OutsideRoom createRoom(String name, Floor floor, int numberOfWindows, int numberOfDoors) {
        return new OutsideRoom(name, floor, numberOfWindows, numberOfDoors);
    }
}
