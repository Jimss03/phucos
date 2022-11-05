package com.Destura.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.CircularPropagation;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Destura.notesapp.homeActivity.home;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class WonActivity extends AppCompatActivity {

    CircularProgressBar circularProgressBar;
    TextView resultText;
    int correct,wrong;
    LinearLayout btnshare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);
        correct=getIntent().getIntExtra("correct",0);
        wrong=getIntent().getIntExtra("Wrong",0);

        circularProgressBar= findViewById(R.id.circularProgressBar);
        resultText=findViewById(R.id.resultText);
        btnshare= findViewById(R.id.btnshare);

        circularProgressBar.setProgress(correct);
        resultText.setText(correct+"/20");

        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WonActivity.this, home.class);
            }
        });

    }
}