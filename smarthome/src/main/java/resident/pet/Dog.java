package resident.pet;

import events.EventType;
import events.ReportInfo;

/**
 * Represents a cat as a pet.
 */

public class Dog extends Pet{
    public Dog(String name) {
        super(name);
    }

    @Override
    void petAction() {
        recordEvent(new ReportInfo(EventType.DOG_SOUND, this, this, getFloor(), currentLocation));
    }

    @Override
    public String toString() {
        return name;
    }
}
