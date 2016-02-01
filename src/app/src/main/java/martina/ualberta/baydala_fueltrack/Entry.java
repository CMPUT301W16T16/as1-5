package martina.ualberta.baydala_fueltrack;

import java.io.Serializable;

/**
 * Created by Martina on 16-01-25.
 */

@SuppressWarnings("serial")
public class Entry implements Serializable {

    private int entry_number;
    private String day;
    private String station;
    private double odometer;
    private String fuel_grade;
    private double fuel_amount;
    private double unit_cost;


    public Entry() { }

    public Entry(int entry_number, String day, String station,double odometer, String fuel_grade, double fuel_amount, double unit_cost) {
        this.entry_number = entry_number;
        this.day = day;
        this.station = station;
        this.odometer = odometer;
        this.fuel_grade = fuel_grade;
        this.fuel_amount = fuel_amount;
        this.unit_cost = unit_cost;
    }

    public String getDay() {
        return day;
    }

    public String getStation() {
        return station;
    }

    public double getOdometer() {
        return Double.valueOf(String.format("%.1f", odometer));
    }

    public String getFuelGrade() {
        return fuel_grade;
    }

    public double getFuelAmount() {
        return Double.valueOf(String.format("%.3f", fuel_amount));
    }

    public double getUnitCost() {
        return Double.valueOf(String.format("%.1f", unit_cost));
    }

    public int getEntryNumber() {
        return entry_number;
    }

    // calculating the fuel cost in dollars
    public double getFuelCost() {
        return Double.valueOf(String.format("%.2f", (unit_cost * fuel_amount / 100.0)));
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setOdometer(double odometer) {
        this.odometer = odometer;
    }

    public void setFuelGrade(String fuel_grade) {
        this.fuel_grade = fuel_grade;
    }

    public void setFuelAmount(double fuel_amount) {
        this.fuel_amount = fuel_amount;
    }

    public void setUnitCost(double unit_cost) {
        this.unit_cost = unit_cost;
    }

    public void setEntryNumber(int entry_number) {
        this.entry_number = entry_number;
    }

    @Override
    // the log on the main screen will be e.g. Entry 1 | 2016-01-30 | $54.03
    public String toString() {
        return "Entry " + entry_number + " | " + day + " | $" + (String.format("%.2f", (unit_cost * fuel_amount / 100.0)));
    }
}
