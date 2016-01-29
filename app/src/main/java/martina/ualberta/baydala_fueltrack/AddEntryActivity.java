package martina.ualberta.baydala_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class AddEntryActivity extends AppCompatActivity {

    private int entryNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_entry);

        Intent intent = getIntent();
        entryNumber = intent.getIntExtra("Entry Number", -1);

    }

    public void completeEntry(View view) {

        //taking in all of the inputted information
        EditText entered_day_text = (EditText) findViewById(R.id.enter_day);
        EditText entered_station_text = (EditText) findViewById(R.id.enter_station);
        EditText entered_odometer_text = (EditText) findViewById(R.id.enter_odometer);
        EditText entered_fuel_grade_text = (EditText) findViewById(R.id.enter_fuel_grade);
        EditText entered_fuel_amount_text = (EditText) findViewById(R.id.enter_fuel_amount);
        EditText entered_unit_cost_text = (EditText) findViewById(R.id.enter_unit_cost);

        //converting the inputted information into Strings
        String entered_day = entered_day_text.getText().toString();
        String entered_station = entered_station_text.getText().toString();
        String entered_odometer = entered_odometer_text.getText().toString();
        String entered_fuel_grade = entered_fuel_grade_text.getText().toString();
        String entered_fuel_amount = entered_fuel_amount_text.getText().toString();
        String entered_unit_cost = entered_unit_cost_text.getText().toString();

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

    public void cancelEntry(View view) {
        finish();
    }

    //TODO add a cancel button (and set defaults -OR- a warning if the user doesn't enter anything)
}

