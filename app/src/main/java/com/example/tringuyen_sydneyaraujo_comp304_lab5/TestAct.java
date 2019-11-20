package com.example.tringuyen_sydneyaraujo_comp304_lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TestAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent = getIntent();

        String yourString  =intent.getStringExtra("value");
        TextView textView= findViewById(R.id.testv);
        textView.setText(yourString);

    }
}
