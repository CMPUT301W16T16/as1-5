package martina.ualberta.baydala_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class EditEntryActivity extends AppCompatActivity implements Serializable {

    private Entry entry;

    private EditText edit_day;
    private EditText edit_station;
    private EditText edit_odometer;
    private EditText edit_fuel_grade;
    private EditText edit_fuel_amount;
    private EditText edit_unit_cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);

        Intent intent = getIntent();
        entry = (Entry) intent.getSerializableExtra("entry");

        edit_day = (EditText) findViewById(R.id.edit_day);
        edit_station = (EditText) findViewById(R.id.edit_station);
        edit_odometer = (EditText) findViewById(R.id.edit_odometer);
        edit_fuel_grade = (EditText) findViewById(R.id.edit_fuel_grade);
        edit_fuel_amount = (EditText) findViewById(R.id.edit_fuel_amount);
        edit_unit_cost = (EditText) findViewById(R.id.edit_unit_cost);

        edit_day.setHint(entry.getDay());
        edit_station.setHint(entry.getStation());
        edit_odometer.setHint(entry.getOdometer());
        edit_fuel_grade.setHint(entry.getFuel_grade());
        edit_fuel_amount.setHint(entry.getFuel_amount());
        edit_unit_cost.setHint(entry.getUnit_cost());
    }

    //returns the Entry entry after updating it
    public void completeEditEntry(View view) {
        String edited_day = edit_day.getText().toString();
        String edited_station = edit_station.getText().toString();
        String edited_odometer = edit_odometer.getText().toString();
        String edited_fuel_grade = edit_fuel_grade.getText().toString();
        String edited_fuel_amount = edit_fuel_amount.getText().toString();
        String edited_unit_cost = edit_unit_cost.getText().toString();

        //only update the value if the user entered something for that value
        if (!edited_day.isEmpty()) {
            entry.setDay(edited_day);
        }
        if (!edited_station.isEmpty()) {
            entry.setStation(edited_station);
        }
        if (!edited_odometer.isEmpty()) {
            entry.setOdometer(edited_odometer);
        }
        if (!edited_fuel_grade.isEmpty()) {
            entry.setFuel_grade(edited_fuel_grade);
        }
        if (!edited_fuel_amount.isEmpty()) {
            entry.setFuel_amount(edited_fuel_amount);
        }
        if (!edited_unit_cost.isEmpty()) {
            entry.setUnit_cost(edited_unit_cost);
        }

        //return the updated entry to the ViewEntry Activity
        Intent intent = new Intent(this, EditEntryActivity.class);
        intent.putExtra("Item", entry);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    //returns the entry without updating it
    public void cancelEditEntry(View view) {
        Intent intent = new Intent(this, EditEntryActivity.class);
        intent.putExtra("Item", entry);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }


}
