package martina.ualberta.baydala_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class ViewEntryActivity extends AppCompatActivity implements Serializable {

    private static final int LOG_EDIT = 1;

    private Entry entry;
    private Entry updated_entry;

    private TextView view_day;
    private TextView view_station;
    private TextView view_odometer;
    private TextView view_fuel_grade;
    private TextView view_fuel_amount;
    private TextView view_unit_cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entry);

        Intent intent = getIntent();
        entry = (Entry) intent.getSerializableExtra("Entry");

        // so the same old entry will be returned if no updates are made
        updated_entry = entry;

        view_day = (TextView) findViewById(R.id.view_day);
        view_station = (TextView) findViewById(R.id.view_station);
        view_odometer = (TextView) findViewById(R.id.view_odometer);
        view_fuel_grade = (TextView) findViewById(R.id.view_fuel_grade);
        view_fuel_amount = (TextView) findViewById(R.id.view_fuel_amount);
        view_unit_cost = (TextView) findViewById(R.id.view_unit_cost);

        updateEntryText(entry);
    }

    // send the entry to the EditEntryActivity to be edited
    public void editButtonClicked(View view) {
        Intent intent = new Intent(this, EditEntryActivity.class);
        intent.putExtra("entry", entry);
        startActivityForResult(intent, LOG_EDIT);
    }

    //r eturn the entry (possibly updated) to the FuelTrackActivity Activity
    public void doneButtonClicked(View view) {
        Intent intent = new Intent(this, EditEntryActivity.class);
        intent.putExtra("updated_entry", updated_entry);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    // taken Jan-25-2016 from http://stackoverflow.com/questions/1124548/how-to-pass-the-values-from-one-activity-to-previous-activity
    // receive the (possibly) edited entry back from the EditEntryActivity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (LOG_EDIT) : {
                if (resultCode == Activity.RESULT_OK) {
                    updated_entry = (Entry) data.getSerializableExtra("Item");
                    updateEntryText(updated_entry);
                }
                break;
            }
        }
    }

    // updates the values on the screen to the values of the entry
    public void updateEntryText(Entry new_entry) {
        view_day.setText(String.format("%s", new_entry.getDay()));
        view_station.setText(String.format("%s", new_entry.getStation()));
        view_odometer.setText(String.format("%s km", new_entry.getOdometer()));
        view_fuel_grade.setText(String.format("%s", new_entry.getFuelGrade()));
        view_fuel_amount.setText(String.format("%s L", new_entry.getFuelAmount()));
        view_unit_cost.setText(String.format("%s Cents/L", new_entry.getUnitCost()));
    }

    //TODO change the title values not to include units
    //maybe add another view that will hold the units off to the right of the value?

}