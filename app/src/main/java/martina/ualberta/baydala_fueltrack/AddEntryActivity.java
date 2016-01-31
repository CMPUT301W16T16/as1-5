package martina.ualberta.baydala_fueltrack;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddEntryActivity extends AppCompatActivity {

    private int entryNumber;
    //taking in all of the inputted information
    EditText entered_day_text;
    EditText entered_station_text;
    EditText entered_odometer_text;
    EditText entered_fuel_grade_text;
    EditText entered_fuel_amount_text;
    EditText entered_unit_cost_text;

    String entered_day;
    String entered_station;
    String entered_odometer;
    String entered_fuel_grade;
    String entered_fuel_amount;
    String entered_unit_cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_entry);

        Intent intent = getIntent();
        entryNumber = intent.getIntExtra("Entry Number", -1);

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
                if (entered_day.isEmpty() || entered_station.isEmpty() || entered_odometer.isEmpty() ||
                        entered_fuel_grade.isEmpty() || entered_fuel_amount.isEmpty() || entered_unit_cost.isEmpty()) {
                    Toast.makeText(AddEntryActivity.this, R.string.empty_field, Toast.LENGTH_SHORT).show();
                } else {
                    //returning the inputted Strings to the FuelTrack class to be displayed
                    //http://stackoverflow.com/questions/1124548/how-to-pass-the-values-from-one-activity-to-previous-activity
                    //http://stackoverflow.com/questions/4429036/passing-string-array-between-android-activities
                    String[] new_entry = {entered_day, entered_station, entered_odometer,
                            entered_fuel_grade, entered_fuel_amount,
                            entered_unit_cost, Integer.toString(entryNumber)};
                    Bundle bundle = new Bundle();
                    bundle.putStringArray("new_entry", new_entry);
                    Intent intent = new Intent(AddEntryActivity.this, AddEntryActivity.class);
                    intent.putExtras(bundle);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
    /*
    public void completeEntry(View view) {
        convertInputToString();
        if (entered_day.isEmpty() || entered_station.isEmpty() || entered_odometer.isEmpty() ||
                entered_fuel_grade.isEmpty() || entered_fuel_amount.isEmpty() || entered_unit_cost.isEmpty()) {
            Toast.makeText(this, R.string.empty_field, Toast.LENGTH_SHORT).show();
            onCreate();
        }

        //returning the inputted Strings to the FuelTrack class to be displayed
        //http://stackoverflow.com/questions/1124548/how-to-pass-the-values-from-one-activity-to-previous-activity
        //http://stackoverflow.com/questions/4429036/passing-string-array-between-android-activities
        String[] new_entry = {entered_day, entered_station, entered_odometer,
                entered_fuel_grade, entered_fuel_amount,
                entered_unit_cost, Integer.toString(entryNumber)};
        Bundle bundle = new Bundle();
        bundle.putStringArray("new_entry", new_entry);
        Intent intent = new Intent(this, AddEntryActivity.class);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
    */

    public void cancelEntry(View view) {
        finish();
    }

    public void convertInputToString() {
        entered_day = entered_day_text.getText().toString();
        entered_station = entered_station_text.getText().toString();
        entered_odometer = entered_odometer_text.getText().toString();
        entered_fuel_grade = entered_fuel_grade_text.getText().toString();
        entered_fuel_amount = entered_fuel_amount_text.getText().toString();
        entered_unit_cost = entered_unit_cost_text.getText().toString();
    }
}

