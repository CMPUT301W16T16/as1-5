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

public class EditEntryActivity extends AppCompatActivity implements Serializable{

    private TextView edit_item_title;
    private EditText edit_item;
    private Entry entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);

        //TODO find a way to make it possible to pass an object from the Entry class instead of an array of strings
        Intent intent = getIntent();
        entry = (Entry) intent.getSerializableExtra("entry");

        //edit_item_title.setHint(old_value);
    }

    public void completeEditEntry(View view) {
        String edited_value = edit_item.getText().toString();
        Bundle bundle = new Bundle();
        //String[] info_return = {category, edited_value};
        //bundle.putStringArray("Item", info_return);
        Intent intent = new Intent(this, AddEntryActivity.class);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }


}
