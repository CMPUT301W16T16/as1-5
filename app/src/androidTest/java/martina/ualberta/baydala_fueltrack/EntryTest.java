package martina.ualberta.baydala_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Martina on 16-01-30.
 */
public class EntryTest extends ActivityInstrumentationTestCase2 {
    public EntryTest() {
        super(FuelTrack.class);
    }

    public void testGetFuelAmount() {
        Entry entry = new Entry();
        entry.setFuel_amount("50");
        assertEquals(50.0, Double.valueOf(entry.getFuel_amount()));
    }

    public void testGetUnitCost() {
        Entry entry = new Entry();
        entry.setUnit_cost("50");
        assertEquals(50.0, Double.valueOf(entry.getUnit_cost()));
    }

    public void testGetFuelCost() {
        Entry entry = new Entry();
        entry.setFuel_amount("50");
        entry.setUnit_cost("70.6");
        double expected_fuel_cost = 50 * 70.6 / 100.0;
        assertEquals(entry.getFuel_cost(), expected_fuel_cost);

        entry.setFuel_amount("0.0");
        entry.setUnit_cost("0.0");
        expected_fuel_cost = 0 * 0 / 100.0;
        assertEquals(entry.getFuel_cost(), expected_fuel_cost);
    }

    public void testToString() {
                        // Date         Odometer Station Grade Amount Cost/L  Entry Number
        String[] values = {"2016-01-01", "1000", "Husky", "87", "60", "70.5", "5"};
        Entry entry = new Entry(values);

        assertEquals(entry.toString(), "Entry 5 | 2016-01-01");
    }
}


