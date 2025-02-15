package home;

import events.EventSource;
import events.EventTarget;
import events.Observable;
import events.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OutsideWorld implements Observable, EventSource, EventTarget{

    private final List<Observer> observerList;

    private boolean isWindy;

    private static int temperature;
    private static final int WIND_PROBABILITY = 25;
    private static final int THE_LOWEST_TEMPERATURE = -10;
    private static final int THE_HIGHEST_TEMPERATURE = 30;




    public OutsideWorld(){
        observerList = new ArrayList<>();
    }

    public void nextIteration(){
        isWindy = new Random().nextInt(100) < WIND_PROBABILITY;
        temperature = getRandomNumber(-THE_LOWEST_TEMPERATURE, THE_HIGHEST_TEMPERATURE);
        broadcast();
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public void subscribe(Observer observer) {
        if(! observerList.contains(observer))
            observerList.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void broadcast() {
        for (Observer observer: observerList) {
            observer.update(this);
        }
    }

    public boolean isWindy() {
        return isWindy;
    }

    public int getTemperature() {
        return temperature;
    }
}
