package devices;

import home.Floor;
import home.Home;
import home.InsideRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeMakerTest {
    private CoffeeMaker coffeeMaker;
    private static final int MAX_CAPACITY = 100;

    @BeforeEach
    public void setUp() {
        coffeeMaker = new CoffeeMaker("TestCoffeeMaker",
                new InsideRoom("TestRoom", new Floor("Test floor", new Home("TestHome")),
                        2, 2), TypesOfConsumption.ELECTRICITY,
                new double[] {7, 2, 0.001});

    }

    @Test
    public void testDrinkCoffee() {
        int initialCoffeeLevel = coffeeMaker.getCoffeeLevel();
        coffeeMaker.drinkCoffee(50);
        assertEquals(initialCoffeeLevel - 50, coffeeMaker.getCoffeeLevel(),
                "The coffee level must be decreased after drinking.");
    }

    @Test
    public void testRecoverCoffee() {
        int initialCoffeeLevel = coffeeMaker.getCoffeeLevel();
        coffeeMaker.recoverCoffee(20);
        int recoverAmount = coffeeMaker.getCoffeeLevel() - initialCoffeeLevel;
        assertTrue(recoverAmount >= 0 && recoverAmount <= MAX_CAPACITY);
    }

    @Test
    public void testDecreaseDurability() {
        assertEquals(0, coffeeMaker.getWearDegree(), "Initial level must be 0");
        coffeeMaker.decreaseDurability();
        assertEquals(100, coffeeMaker.getWearDegree(), "Wear degree must be fully increase");
    }


}