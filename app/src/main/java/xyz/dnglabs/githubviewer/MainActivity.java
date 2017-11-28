package xyz.dnglabs.githubviewer;

import android.support.v7.app.AppCompatActivity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView gotoSearch = (TextView) findViewById(R.id.doSearch);
        gotoSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText inputTxt = (EditText) findViewById(R.id.userName);
                String nameSearch = inputTxt.getText().toString();

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("MyPref", ("https://api.github.com/users/" + nameSearch));
                editor.putString("MyPref2", nameSearch);
                editor.apply();


                String urlSearch = pref.getString("MyPref", "");


                Intent searchIntent = new Intent(MainActivity.this, resultSearch.class);
                startActivity(searchIntent);
            }
        });
        }
    }



