package com.jalalelzein.talktosomeone3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class RemoveActivity extends AppCompatActivity {

    String[] people;
    EditText rInputName;
    Button rButtonOne;
    Button rButtonAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        // define all the views
        rButtonAll = findViewById(R.id.remove_button_all);
        rButtonOne = findViewById(R.id.remove_button_one);
        rInputName = findViewById(R.id.remove_edittext_name);

        // initialize people
        people = null;

        rButtonOne.setEnabled(false);

        rInputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                rButtonOne.setEnabled(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        rButtonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RemoveActivity.this);

                builder.setTitle("Are you sure?")
                        .setMessage("This will erase all the people you have saved permanently")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // write an empty string to the file
                                save("", "All people have been removed from the list");
                                Toast.makeText(RemoveActivity.this, "All People Have Been Removed", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .create()
                        .show();
            }
        });

        rButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if file not loaded already
                if (people == null) {
                    // load from the local file
                    people = load().split(AddActivity.SUFFIX);
                }

                // get person's name from the edittext
                String person = rInputName.getText().toString().trim();

                // look for the item that matches
                ArrayList<String> arrlist = new ArrayList<String>();
                boolean found = copyArray2ArrayListUnless(people, arrlist, person);

                // if we find a matching item
                if (found) {
                    // replace people
                    people = trnsfrmArrayList2Array(arrlist);
                    // write to file and send a toast that we found
                    save(String.join(AddActivity.SUFFIX, people), "Person Has Been Removed!");
                }
                // if we don't find a matching item
                else {
                    // send an error toast
                    createEmptyAlertDialog("Error: Person Not Found", "The requested person was not found.\nTry again and look out for typos!");
                }
                // clear input field
                rInputName.setText("");

                // if we just removed the last item, make the array empty
                if ((people.length == 1) && (people[0] == " " || people[0] == "" || people[0] == "  ")) {
                    people = new String[0];
                }
            }
        });
    }

    private boolean copyArray2ArrayListUnless (String arr[], ArrayList<String> arrlist, String unless) {
        boolean found = false;
        for (int q = 0; q < arr.length; q++) {
            if ((arr[q].trim()).equals(unless)) {
                found = true;
            } else {
                arrlist.add(arr[q].trim());
            }
        }
        return found;
    }

    private String[] trnsfrmArrayList2Array (ArrayList<String> arrlist) {
        String newarr[] = new String[arrlist.size()];
        for (int e = 0; e < arrlist.size(); e++) {
            newarr[e] = arrlist.get(e).trim();
        }
        return newarr;
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

    private void save(String toSave, String toastMessage) {
        FileOutputStream fos = null;
        try {
            // open file
            fos = openFileOutput(MainActivity.FILE_NAME, MODE_PRIVATE);
            // write to file
            fos.write(toSave.getBytes());
            // notify user
            Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ee) {
            ee.printStackTrace();
        } finally {
            // close the file is the change was successful
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String load() {
        FileInputStream fis = null;
        try {
            fis = openFileInput(MainActivity.FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String everything;
            while ((everything = br.readLine()) != null) {
                sb.append(everything).append("\n");
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

    private void printArray(String arr[]) {
        for (String s : arr) {
            System.out.println(s);
        }
    }

    private void printArray(ArrayList<String> arrlist) {
        for (int r = 0; r < arrlist.size(); r++) {
            System.out.println(arrlist.get(r));
        }
    }
}