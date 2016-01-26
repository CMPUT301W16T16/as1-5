package martina.ualberta.baydala_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

// TODO make the app design prettier

public class FuelTrack extends AppCompatActivity {

    private static final int LOG_ENTRY = 1;
    private ListView previousEntries;
    private ArrayList<Entry> entries = new ArrayList<Entry>();
    private ArrayAdapter<Entry> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_fuel_track);


        //TODO make it possible to load from file String[] entries = loadFromFile();
        adapter = new ArrayAdapter<Entry>(this,
                R.layout.list_items, entries);

        previousEntries = (ListView) findViewById(R.id.previousEntries);
        previousEntries.setAdapter(adapter);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fuel_track, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onStart() {
        super.onStart();

    }

    //http://stackoverflow.com/questions/1124548/how-to-pass-the-values-from-one-activity-to-previous-activity
    //http://stackoverflow.com/questions/4429036/passing-string-array-between-android-activities
    public void addEntry (View view) {
        Intent intent = new Intent(this, AddEntryActivity.class);
        startActivityForResult(intent, LOG_ENTRY);

    }
    //http://stackoverflow.com/questions/1124548/how-to-pass-the-values-from-one-activity-to-previous-activity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (LOG_ENTRY) : {
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String[] new_entry = bundle.getStringArray("new_entry");
                    Entry entry = new Entry(new_entry);
                    entries.add(entry);
                    adapter.notifyDataSetChanged();

                }
                break;
            }
        }
    }

    // TODO add the ability to save from and fetch from a file so the data is not lost at restart
    // TODO add Json so the saving and fetching just cause



}
