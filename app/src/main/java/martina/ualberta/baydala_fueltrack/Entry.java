package martina.ualberta.baydala_fueltrack;

import android.widget.TextView;
import java.io.Serializable;

/**
 * Created by Martina on 16-01-25.
 */

@SuppressWarnings("serial")
public class Entry implements Serializable {

    //declaration of variables
    protected String entry_number;
    protected String day;
    protected String station;
    protected String odometer;
    protected String fuel_grade;
    protected String fuel_amount;
    protected String unit_cost;

    public Entry() { }

    //constructor
    public Entry(String[] entry) {
        this.day = entry[0];
        this.station = entry[1];
        this.odometer = entry[2];
        this.fuel_grade = entry[3];
        this.fuel_amount = entry[4];
        this.unit_cost = entry[5];
        this.entry_number = entry[6];
    }

    public String getDay() {
        return day;
    }

    public String getStation() {
        return station;
    }

    public String getOdometer() {
        return odometer;
    }

    public String getFuelGrade() {
        return fuel_grade;
    }

    public String getFuelAmount() {
        return fuel_amount;
    }

    public String getUnitCost() {
        return unit_cost;
    }

    public String getEntryNumber() {
        return entry_number;
    }

    public double getFuelCost() {
        return Double.valueOf(unit_cost) * Double.valueOf(fuel_amount) / 100.0;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }

    public void setFuelGrade(String fuel_grade) {
        this.fuel_grade = fuel_grade;
    }

    public void setFuelAmount(String fuel_amount) {
        this.fuel_amount = fuel_amount;
    }

    public void setUnitCost(String unit_cost) {
        this.unit_cost = unit_cost;
    }

    @Override
    public String toString() {
        return "Entry " + entry_number + " | " + day;
    }
}
