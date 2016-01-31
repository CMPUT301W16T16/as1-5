//used some techniques from "Android Programming by Big Nerd Ranch, Volume 2

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FuelTrack extends AppCompatActivity {

    private static final int LOG_ENTRY = 1;
    private static final int LOG_VIEW = 2;
    private static final String FILENAME = "file.sav";
    private ArrayList<Entry> entries = new ArrayList<Entry>();
    private ArrayAdapter<Entry> adapter;
    private ListView previousEntries;
    private int entryNumber = 1;
    private TextView fuel_cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_fuel_track);

        adapter = new ArrayAdapter<Entry>(this,
                R.layout.list_items, entries);

        fuel_cost = (TextView) findViewById(R.id.fuel_cost);
        previousEntries = (ListView) findViewById(R.id.previousEntries);
        previousEntries.setAdapter(adapter);
        //updateCost();

        //allows the user the select any log entry to see more details about it, and to edit it
        //http://stackoverflow.com/questions/20922036/android-cant-call-setonitemclicklistener-from-a-listview
        previousEntries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Entry entry = entries.get(position);
                Intent intent = new Intent(FuelTrack.this, ViewEntryActivity.class);
                intent.putExtra("Entry", entry);
                startActivityForResult(intent, LOG_VIEW);
            }
        });
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
        loadFromFile();
        adapter = new ArrayAdapter<Entry>(this,
                R.layout.list_items, entries);
        previousEntries.setAdapter(adapter);
        //updateCost();
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 01-19 2016
            Type listType = new TypeToken<ArrayList<Entry>>() {
            }.getType();
            Type intType = new TypeToken<Integer>() { }.getType();
            entries = gson.fromJson(in, listType);
            entryNumber = entries.size() + 1;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            entries = new ArrayList<Entry>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(entries, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }


    //http://stackoverflow.com/questions/1124548/how-to-pass-the-values-from-one-activity-to-previous-activity
    //http://stackoverflow.com/questions/4429036/passing-string-array-between-android-activities
    //moves to the AddEntryActivity so a new entry can be made with the appropriate entry number
    public void addEntry (View view) {
        Intent intent = new Intent(this, AddEntryActivity.class);
        intent.putExtra("Entry Number", entryNumber);
        entryNumber += 1;
        startActivityForResult(intent, LOG_ENTRY);
    }

    //http://stackoverflow.com/questions/1124548/how-to-pass-the-values-from-one-activity-to-previous-activity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            //receives the information from the AddEntryActivity,
            //converts it into an entry and adds it to the list of entries
            case (LOG_ENTRY) : {
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String[] new_entry = bundle.getStringArray("new_entry");
                    Entry entry = new Entry(new_entry);
                    entries.add(entry);
                    adapter.notifyDataSetChanged();
                    saveInFile();
                    updateCost();
                }
                break;
            }
            //receives the entry back after it has been displayed and possibly edited.
            //if it has been edited, the corresponding entry in the list of entries is updated
            case(LOG_VIEW) : {
                if (resultCode == Activity.RESULT_OK) {
                    Entry result_entry = (Entry) data.getSerializableExtra("updated_entry");
                    updateEntry(entries, result_entry);
                    adapter.notifyDataSetChanged();
                    saveInFile();
                    updateCost();
                }
                break;
            }
        }
    }

    //updates an entry in the list of entries (entries)
    //with it's edited entry returned from ViewEntryActivity
    public void updateEntry(ArrayList<Entry> entry_list, Entry new_entry) {
        //the position in the entries list is entry_number - 1 because entry_number
        //started with 1instead of 0
        int position = Integer.valueOf(new_entry.getEntry_number()) - 1;
        entry_list.set(position, new_entry);
        updateCost();
    }

    public void updateCost() {
        double total_cost = 0.0;
        for (int i = 0; i < entries.size(); i++) {
            total_cost += entries.get(i).getFuel_cost();
        }
        //http://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
        fuel_cost.setText(String.format("$ %.2f", total_cost));
    }

    //TODO add error catching for when not all fields are entered or fields are entered incorrectly
    //TODO add tests

}
