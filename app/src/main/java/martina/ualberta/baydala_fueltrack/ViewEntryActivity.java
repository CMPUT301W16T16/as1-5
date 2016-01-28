package martina.ualberta.baydala_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.io.Serializable;
import java.util.ArrayList;

public class ViewEntryActivity extends AppCompatActivity implements Serializable {

    private static final int LOG_EDIT = 1;
    private Entry entry;
    private ViewSwitcher switcher;
    private ArrayList<String> edits;

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


        view_day = (TextView) findViewById(R.id.view_day);
        view_station = (TextView) findViewById(R.id.view_station);
        view_odometer = (TextView) findViewById(R.id.view_odometer);
        view_fuel_grade = (TextView) findViewById(R.id.view_fuel_grade);
        view_fuel_amount = (TextView) findViewById(R.id.view_fuel_amount);
        view_unit_cost = (TextView) findViewById(R.id.view_unit_cost);

        view_day.setText(entry.getDay());
        view_station.setText(entry.getStation());
        view_odometer.setText(entry.getOdometer());
        view_fuel_grade.setText(entry.getFuel_grade());
        view_fuel_amount.setText(entry.getFuel_amount());
        view_unit_cost.setText(entry.getUnit_cost());

    }

    public void viewDayClicked(View view) {
        //TODO find a way to make it possible to edit these values in a decent way
        entry.setEdit_day(true);
        Intent intent = new Intent(this, EditEntryActivity.class);
        intent.putExtra("entry", entry);
        startActivityForResult(intent, LOG_EDIT);

    }

    //http://stackoverflow.com/questions/1124548/how-to-pass-the-values-from-one-activity-to-previous-activity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (LOG_EDIT) : {
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String[] info_return = bundle.getStringArray("Item");

                }
                break;
            }
        }
    }

}