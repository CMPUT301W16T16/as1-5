package martina.ualberta.baydala_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Martina on 16-01-30.
 */
public class EntryTest extends ActivityInstrumentationTestCase2 {
    public EntryTest() {
        super(FuelTrackActivity.class);
    }

    public void testGetFuelAmount() {
        Entry entry = new Entry();
        entry.setFuelAmount(50);
        assertEquals(50.0, entry.getFuelAmount());
    }

    public void testGetUnitCost() {
        Entry entry = new Entry();
        entry.setUnitCost(50);
        assertEquals(50.0, entry.getUnitCost());
    }

    public void testGetFuelCost() {
        Entry entry = new Entry();
        entry.setFuelAmount(50);
        entry.setUnitCost(70.6);
        double expected_fuel_cost = 50 * 70.6 / 100.0;
        assertEquals(entry.getFuelCost(), expected_fuel_cost);

        entry.setFuelAmount(0.0);
        entry.setUnitCost(0.0);
        expected_fuel_cost = 0 * 0 / 100.0;
        assertEquals(entry.getFuelCost(), expected_fuel_cost);
    }

    public void testToString() {
        Entry entry = new Entry(5, "2016-01-01", "Husky", 1000, "87", 60, 70.5);

        assertEquals(entry.toString(), "Entry 5 | 2016-01-01 | $42.30");
    }
}


