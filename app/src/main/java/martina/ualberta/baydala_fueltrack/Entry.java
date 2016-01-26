package martina.ualberta.baydala_fueltrack;

/**
 * Created by Martina on 16-01-25.
 */
public class Entry {
    protected String day;
    protected String station;
    protected String odometer;
    protected String fuel_grade;
    protected String fuel_amount;
    protected String unit_cost;

    public Entry(String[] entry) {
        this.day = entry[0];
        this.station = entry[1];
        this.odometer = entry[2];
        this.fuel_grade = entry[3];
        this.fuel_amount = entry[4];
        this.unit_cost = entry[5];
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

    public String getFuel_grade() {
        return fuel_grade;
    }

    public String getFuel_amount() {
        return fuel_amount;
    }

    public String getUnit_cost() {
        return unit_cost;
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

    public void setFuel_grade(String fuel_grade) {
        this.fuel_grade = fuel_grade;
    }

    public void setFuel_amount(String fuel_amount) {
        this.fuel_amount = fuel_amount;
    }

    public void setUnit_cost(String unit_cost) {
        this.unit_cost = unit_cost;
    }
}
