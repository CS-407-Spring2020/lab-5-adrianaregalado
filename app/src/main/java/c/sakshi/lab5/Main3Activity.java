package c.sakshi.lab5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity {

    int noteid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // 1. Get EditText view
        EditText myTextField = (EditText) findViewById(R.id.editTextNote);

        // 2. Get Intent
        Intent intent = getIntent();

        // 3. Get the value of integer "noteid" from intent
        int value = intent.getIntExtra("noteid", -1);

        // 4. Initialise class variable "noteid" with the value from intent
        noteid = value;

        if (noteid != -1) {
            // Display content of note by retrieving "notes" in ArrayList in SecondActivity
            Note note = Main2Activity.notes.get(noteid);
            String notecontent = note.getContent();
            // Use editText.setText() to display the contents of this note on screen.
            myTextField.setText(notecontent);
        }
    }

    public void saveMethod(View view) {
        // 1. Get editText view and the content that user entered.
        EditText myTextField = (EditText) findViewById(R.id.editTextNote);
        String content = myTextField.getText().toString();

        // 2. Initialise SQLiteDatabase instance.
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);

        // 3. Initialise DBHelper class.
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        // 4. Set username in the following variable by fetching it from SharedPreferences.
        SharedPreferences sharedPreferences =
                getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        // 5. Save information to database
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (Main2Activity.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNotes(username, title, content, date);
        }

        // 6. Go to second activity using intents.
        Intent intent = new Intent ( this, Main2Activity.class);
        startActivity(intent);
    }
}
