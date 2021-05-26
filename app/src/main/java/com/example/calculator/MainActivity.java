package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button keyMC = findViewById(R.id.MC);
        Button keyMR = findViewById(R.id.MR);
        Button keyMAsc = findViewById(R.id.MAsc);
        Button keyMDesc = findViewById(R.id.MDesc);
        Button keyMS = findViewById(R.id.MS);

        Button key_percent = findViewById(R.id.key_percent);
        Button key_CE = findViewById(R.id.key_CE);
        Button key_C = findViewById(R.id.key_C);
        Button key_delete = findViewById(R.id.key_delete);

        Button key0 = findViewById(R.id.key_0);
        Button key1 = findViewById(R.id.key_1);
        Button key2 = findViewById(R.id.key_2);
        Button key3 = findViewById(R.id.key_3);
        Button key4 = findViewById(R.id.key_4);
        Button key5 = findViewById(R.id.key_5);
        Button key6 = findViewById(R.id.key_6);
        Button key7 = findViewById(R.id.key_7);
        Button key8 = findViewById(R.id.key_8);
        Button key9 = findViewById(R.id.key_9);

        Button key_subtraction = findViewById(R.id.key_subtraction);
        Button key_addition = findViewById(R.id.key_addition);
        Button key_inverting = findViewById(R.id.key_inverting);
        Button key_equally = findViewById(R.id.key_equally);

    }
}