package martina.ualberta.baydala_fueltrack;

import java.io.Serializable;

/**
 * Created by Martina on 16-01-25.
 */

@SuppressWarnings("serial")
public class Entry implements Serializable {

    protected int entry_number;
    protected String day;
    protected String station;
    protected String odometer;
    protected String fuel_grade;
    protected String fuel_amount;
    protected String unit_cost;
    protected double fuel_cost;

    public Entry() { }

    public Entry(int entry_number, String day, String station, String odometer, String fuel_grade, String fuel_amount, String unit_cost) {
        this.entry_number = entry_number;
        this.day = day;
        this.station = station;
        this.odometer = odometer;
        this.fuel_grade = fuel_grade;
        this.fuel_amount = fuel_amount;
        this.unit_cost = unit_cost;
        this.fuel_cost = getFuelCost();
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

    public int getEntryNumber() {
        return entry_number;
    }

    // calculating the fuel cost in cents/L
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

    public void setEntryNumber(int entry_number) {
        this.entry_number = entry_number;
    }

    @Override
    // the log on the main screen will be e.g. Entry 1 | 2016-01-30 | $54.03
    public String toString() {
        return "Entry " + entry_number + " | " + day + " | $" + String.format("%.2f", fuel_cost);
    }
}
