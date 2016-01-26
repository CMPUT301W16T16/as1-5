package martina.ualberta.baydala_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class FuelTrack extends AppCompatActivity {

    private static final int LOG_ENTRY = 1;
    private ListView previousEntries;

    private ArrayList<Entry> entries = new ArrayList<Entry>();
    private ArrayAdapter<Entry> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_fuel_track);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        previousEntries = (ListView) findViewById(R.id.previousEntries);

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

    //http://stackoverflow.com/questions/1124548/how-to-pass-the-values-from-one-activity-to-previous-activity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (LOG_ENTRY) : {
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = this.getIntent().getExtras();
                    String[] returned_entry = (bundle.getStringArray("key"));
                    Entry new_entry = new Entry(returned_entry);
                    entries.add(new_entry);
                    adapter.notifyDataSetChanged();

                }
                break;
            }
        }
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        //String[] tweets = loadFromFile();
        adapter = new ArrayAdapter<Entry>(this,
                R.layout.list_items, entries);
        previousEntries.setAdapter(adapter);
    }

    //http://stackoverflow.com/questions/1124548/how-to-pass-the-values-from-one-activity-to-previous-activity
    //http://stackoverflow.com/questions/4429036/passing-string-array-between-android-activities
    public void addEntry (View view) {
        Intent intent = new Intent(this, AddEntryActivity.class);
        startActivityForResult(intent, LOG_ENTRY);

    }


}
