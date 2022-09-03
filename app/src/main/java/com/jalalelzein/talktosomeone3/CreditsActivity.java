package com.jalalelzein.talktosomeone3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class CreditsActivity extends AppCompatActivity {

    TextView cTextCredits;
    TextView cTextLink;
    ImageView cImageMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        cTextCredits = findViewById(R.id.credits_text_credits);
        cTextLink = findViewById(R.id.credits_text_link);
        cImageMe = findViewById(R.id.credits_image_me);

        cTextLink.setMovementMethod(LinkMovementMethod.getInstance() );
    }
}