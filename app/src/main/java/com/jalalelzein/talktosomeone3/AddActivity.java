package com.jalalelzein.talktosomeone3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class AddActivity extends AppCompatActivity {

    public static final String SUFFIX = ";";
    EditText aInputName;
    Button aButtonAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        aInputName = findViewById(R.id.add_edittext_name);
        aButtonAdd = findViewById(R.id.add_button_add);

        aButtonAdd.setEnabled(false);

        aInputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                aButtonAdd.setEnabled(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        aButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save to local file
                save();
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
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void save() {
        // get previous text
        String saved = load();
        // get text to save
        String newText = aInputName.getText().toString().trim() + SUFFIX;
        // add new to old to append
        String toSave = saved + newText;
        FileOutputStream fos = null;
        try {
            // open file
            fos = openFileOutput(MainActivity.FILE_NAME, MODE_PRIVATE);
            // write to the file
            fos.write(toSave.getBytes());
            // clear the space to write again
            aInputName.setText("");
            // notify the user that the save is successful
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + MainActivity.FILE_NAME, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close file if opened
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}