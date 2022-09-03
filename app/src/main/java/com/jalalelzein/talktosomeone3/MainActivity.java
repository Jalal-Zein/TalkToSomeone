package com.jalalelzein.talktosomeone3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String FILE_NAME = "people.txt";

    Button mButtonGet;
    Button mButtonAdd;
    Button mButtonRemove;
    Button mButtonCredits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonGet = findViewById(R.id.main_button_get);
        mButtonAdd = findViewById(R.id.main_button_add);
        mButtonRemove = findViewById(R.id.main_button_remove);
        mButtonCredits = findViewById(R.id.main_button_credits);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // load get activity
                Intent addActivity = new Intent(MainActivity.this, AddActivity.class);
                startActivity(addActivity);
            }
        });

        mButtonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // load add activity
                Intent addActivity = new Intent(MainActivity.this, GetActivity.class);
                startActivity(addActivity);
            }
        });

        mButtonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // load remove activity
                Intent removeActivity = new Intent(MainActivity.this, RemoveActivity.class);
                startActivity(removeActivity);
            }
        });

        mButtonCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent creditsActivity = new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(creditsActivity);
            }
        });
    }

    private void createEmptyAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }
}