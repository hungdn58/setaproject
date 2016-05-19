package com.example.hoang.datingproject.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hoang.datingproject.R;
import com.example.hoang.datingproject.Utilities.FontManager;

public class SettingsActivity extends AppCompatActivity {

    private TextView exit_button, right_arrow_button, right_arrow_button1, right_arrow_button2, right_arrow_button3;
    private RelativeLayout personalInfo, feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getControls();
    }

    private void getControls() {
        exit_button = (TextView) findViewById(R.id.exit_button);
        right_arrow_button = (TextView) findViewById(R.id.right_arrow_button);
        right_arrow_button1 = (TextView) findViewById(R.id.right_arrow_button1);
        right_arrow_button2 = (TextView) findViewById(R.id.right_arrow_button2);
        right_arrow_button3 = (TextView) findViewById(R.id.right_arrow_button3);

        personalInfo = (RelativeLayout) findViewById(R.id.personalInfo);
        feedback = (RelativeLayout) findViewById(R.id.feedback);

        Typeface font = FontManager.getTypeface(SettingsActivity.this, FontManager.FONTAWESOME);

        exit_button.setTypeface(font);
        right_arrow_button.setTypeface(font);
        right_arrow_button1.setTypeface(font);
        right_arrow_button2.setTypeface(font);
        right_arrow_button3.setTypeface(font);

        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        personalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, PersonalInfoActivity.class);
                startActivity(intent);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, FeedbackActivity.class);
                startActivity(intent);
            }
        });
    }

}
