package com.jalalelzein.talktosomeone3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShowActivity extends AppCompatActivity {

    TextView sTextView;
    String people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        sTextView = findViewById(R.id.show_text_people);
        people = load();
        String thing = (people == "") ? "There is no one to show :(\nGo back and press 'ADD SOMEONE FOR LATER' to add a new person!" : people;
        thing = thing.replaceAll(AddActivity.SUFFIX, "\n");
        sTextView.setText(thing);
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
}