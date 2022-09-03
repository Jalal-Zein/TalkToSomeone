package com.jalalelzein.talktosomeone3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class GetActivity extends AppCompatActivity {

    String[] people;
    TextView gTextName;
    Button gButtonGet;
    Button gButtonGetAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        people = null;

        gTextName = findViewById(R.id.get_textview_name);
        gButtonGet = findViewById(R.id.get_button_get);
        gButtonGetAll = findViewById(R.id.get_button_all);

        gButtonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // read from local file if not already read
                // separate string to array
                if (people == null) {
                    people = load().split(AddActivity.SUFFIX);
                }
                // choose a random index
                Random rndm = new Random();
                // change textview text to item in array at random index
//                String persun = people[randomIndex].trim();
//                if ((people.length == 0) || (people.length == 1 && people[0] == "")) {
//                    createEmptyAlertDialog("Error: Empty List of People", "There is no one to show :(\nGo back and press 'ADD SOMEONE FOR LATER' to add a new person!");
//                } else if (persun == "") {
//
//                } else {
//                    gTextName.setText(persun);
//                }
//                gTextName.setText(persun);

                if ((people.length == 0) || (people.length == 1 && people[0] == "")) {
                    createEmptyAlertDialog("Error: Empty List of People", "There is no one to show :(\nGo back and press 'ADD SOMEONE FOR LATER' to add a new person!");
                } else {
                    int randomIndex = rndm.nextInt(people.length - 1);
                    String persun = people[randomIndex].trim();
                    if (persun == "") {

                    } else {
                        gTextName.setText(persun);
                    }
                }
            }
        });

        gButtonGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showAllActivity = new Intent(GetActivity.this, ShowActivity.class);
                startActivity(showAllActivity);
            }
        });
    }

    public String load() {
        // read from local file if not already read
        FileInputStream fis = null;
        try {
            fis = openFileInput(MainActivity.FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String name;
            while ((name = br.readLine()) != null) {
                sb.append(name).append("\n");
            }

            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
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