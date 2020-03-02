package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernameKey, "").equals("")) {
            // Get name of user.
            String user = sharedPreferences.getString(usernameKey, "");

            // Start second activity.
            Intent intent = new Intent ( this, Main2Activity.class);
            intent.putExtra("name",  user + "");
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_main);
        }
    }

    public void onButtonClick(View view) {
        //1. Get username and password via EditText view.
        EditText myTextField = (EditText) findViewById(R.id.editText);
        String str = myTextField.getText().toString();

        //2. Add username to SharedPreferences object.
        SharedPreferences sharedPreferences =
                getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", str ).apply();

        //3. Start second activity.
        Intent intent = new Intent ( this, Main2Activity.class);
        startActivity(intent);
    }
}
