package devices.equipment;

import events.EventTarget;
import home.OutsideRoom;
import resident.human.Human;
import resident.human.Usable;

import java.util.Optional;

/**
 * An abstract class representing sport equipment in an outside room.
 */
public abstract class Equipment implements Usable, EventTarget {
    protected String name;
    protected boolean isAvailable = true;
    protected OutsideRoom location;

    protected Equipment(String name, OutsideRoom location) {
        this.name = name;
        this.location = location;
        location.addEquipment(this);
    }


    public abstract Optional<Usable> use(Human human);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public String toString() {
        return name;
    }
}
