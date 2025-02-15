package resident.pet;

import events.EventType;
import events.ReportInfo;

/**
 * Represents a cat as a pet.
 */

public class Cat extends Pet{

    public Cat(String name) {
        super(name);
    }

    @Override
    public void petAction() {
        recordEvent(new ReportInfo(EventType.CAT_SOUND, this, this, getFloor(), currentLocation));
    }

    @Override
    public String toString() {
        return name;
    }
}
