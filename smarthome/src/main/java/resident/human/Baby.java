package resident.human;

import events.Observable;
import events.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a baby, extending the Human class and implementing the Observable interface.
 */
public class Baby extends Human implements Observable {

    private final List<Observer> observers = new ArrayList<>();
    private boolean isHappy;
    private static final int FOOD_CONSUMPTION = 3;
    private static final int CRY_PST = 15;


    public Baby(String name) {
        super(name);
    }

    /**
     * Checks if the baby is unhappy and starts crying with a certain probability.
     */
    private void startCrying() {
        if (isHappy && new Random().nextInt(100) < CRY_PST)
            isHappy = false;
    }

    /**
     * Attempts to stop the baby from crying with a certain probability.
     * @param stopCryingPst The probability of the baby stopping crying.
     */
    public void stopCrying(int stopCryingPst) {
        if (!isHappy() && new Random().nextInt(100) < stopCryingPst)
            isHappy = true;
    }

    /**
     * Performs the baby's actions, including crying and acting if happy.
     */
    @Override
    public void act() {
        startCrying();
        if (isHappy) {
            super.act();
        } else {
            broadcast();
        }
    }


    @Override
    public int getRandomNumberBetween(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public void subscribe(Observer observer) {
        if(!observers.contains(observer))
            observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void broadcast() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public boolean isHappy() {
        return isHappy;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
