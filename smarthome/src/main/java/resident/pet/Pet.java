package resident.pet;

import resident.Resident;

public abstract class Pet extends Resident {

    protected Pet(String name) {
        super(name);
    }

    public void nextIteration() {
       petAction();
    }

    abstract void petAction();
}
