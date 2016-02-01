package martina.ualberta.baydala_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;


public class AddEntryActivity extends AppCompatActivity implements Serializable {

    private int entry_number;

    private EditText entered_day_text;
    private EditText entered_station_text;
    private EditText entered_odometer_text;
    private EditText entered_fuel_grade_text;
    private EditText entered_fuel_amount_text;
    private EditText entered_unit_cost_text;

    private String entered_day;
    private String entered_station;
    private String entered_odometer;
    private String entered_fuel_grade;
    private String entered_fuel_amount;
    private String entered_unit_cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_entry);

        Intent intent = getIntent();
        entry_number = intent.getIntExtra("Entry Number", -1);

        entered_day_text = (EditText) findViewById(R.id.enter_day);
        entered_station_text = (EditText) findViewById(R.id.enter_station);
        entered_odometer_text = (EditText) findViewById(R.id.enter_odometer);
        entered_fuel_grade_text = (EditText) findViewById(R.id.enter_fuel_grade);
        entered_fuel_amount_text = (EditText) findViewById(R.id.enter_fuel_amount);
        entered_unit_cost_text = (EditText) findViewById(R.id.enter_unit_cost);

        Button done_button = (Button) findViewById(R.id.done);
        done_button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                convertInputToString();
                // allows the user to enter values into the empty fields
                if (entered_day.isEmpty() || entered_station.isEmpty() || entered_odometer.isEmpty() ||
                        entered_fuel_grade.isEmpty() || entered_fuel_amount.isEmpty() || entered_unit_cost.isEmpty()) {
                    Toast.makeText(AddEntryActivity.this, R.string.empty_field, Toast.LENGTH_SHORT).show();
                // returns the new entry back to FuelTrackActivity
                } else {
                    // returning the inputted Strings to the FuelTrackActivity class to be displayed
                    // Taken Jan-25-2016 from http://stackoverflow.com/questions/1124548/how-to-pass-the-values-from-one-activity-to-previous-activity
                    Entry new_entry = new Entry(entry_number, entered_day, entered_station, Double.valueOf(entered_odometer), entered_fuel_grade, Double.valueOf(entered_fuel_amount), Double.valueOf(entered_unit_cost));
                    Intent intent = new Intent(AddEntryActivity.this, AddEntryActivity.class);
                    intent.putExtra("NEW", new_entry);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    // leave this activity without saving anything
    public void cancelEntry(View view) {
        finish();
    }

    // get the inputted values in usable form
    public void convertInputToString() {
        entered_day = entered_day_text.getText().toString();
        entered_station = entered_station_text.getText().toString();
        entered_odometer = entered_odometer_text.getText().toString();
        entered_fuel_grade = entered_fuel_grade_text.getText().toString();
        entered_fuel_amount = entered_fuel_amount_text.getText().toString();
        entered_unit_cost = entered_unit_cost_text.getText().toString();
    }
}

