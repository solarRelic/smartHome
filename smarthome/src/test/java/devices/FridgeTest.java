package devices;

import home.Floor;
import home.Home;
import home.InsideRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reports.ConsumptionReport;
import reports.EventReport;
import resident.human.Father;
import sensors.consumptions.ElectricitySensor;

import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class FridgeTest {
    private Fridge fridge;
    private static final int MAX_CAPACITY = 100;

    @BeforeEach
    public void setUp() {
        fridge = new Fridge("Fridge",
                new InsideRoom("TestRoom", new Floor("Test floor", new Home("TestHome")),
                        2, 2), TypesOfConsumption.ELECTRICITY,
                new double[] {7, 2, 0.001});

    }

    @Test
    void decreaseDurability() {
        assertEquals(0, fridge.getWearDegree(), "Initial level must be 0");
        fridge.decreaseDurability();
        assertEquals(100, fridge.getWearDegree(), "Wear degree must be fully increase");

    }

    @Test
    void use() {
    }




    @Test
    void recoverFood() {
    }
}