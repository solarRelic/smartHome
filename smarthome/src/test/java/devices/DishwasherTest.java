package devices;

import home.Floor;
import home.Home;
import home.InsideRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishwasherTest {
    private Dishwasher dishwasher;
    private static final int MAX_CAPACITY = 100;

    @BeforeEach
    public void setUp() {
        dishwasher = new Dishwasher("TestDishWasher",
                new InsideRoom("TestRoom", new Floor("Test floor", new Home("TestHome")),
                        2, 2), TypesOfConsumption.WATER,
                new double[]{12, 1, 0});
    }

    @Test
    public void testRecoverDetergent() {
        int initialDetergentLevel = dishwasher.getDetergentLevel();
        dishwasher.recoverDetergent(20);
        int recoverAmount = dishwasher.getDetergentLevel() - initialDetergentLevel;
        assertTrue(recoverAmount >= 0 && recoverAmount <= MAX_CAPACITY);
    }

    @Test
    public void testDecreaseDurability() {
        assertEquals(0, dishwasher.getWearDegree(), "Initial level must be 0");
        dishwasher.decreaseDurability();
        assertEquals(100, dishwasher.getWearDegree(), "Wear degree must be fully increase");
    }
}
