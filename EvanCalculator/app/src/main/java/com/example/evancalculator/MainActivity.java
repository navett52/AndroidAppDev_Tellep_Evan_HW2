package com.example.evancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display_text);

        ArrayList<View> allButtons;
        allButtons = (findViewById(R.id.calculator)).getTouchables();

        for (View view : allButtons) {
            Button button = (Button) view;

            button.setOnClickListener(genericButtonClick);
        }
    }

    private View.OnClickListener genericButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;

            String action = null;
            String operator = null;

            if (button.getText().toString().matches("([0-9]+)")) {
                action = "num";
            }

            if (button.getText().toString().matches("([\\+\\-\\*\\/])")) {
                action = "operator";
            }

            if (button.getText().toString().equals("=")) {
                action = "=";
            }

            switch(action) {
                case "num":
                    display.append(button.getText());
                    break;
                case "operator":
                    if (operator == null) {
                        display.append(button.getText());
                        operator = button.getText().toString();
                    }
                    break;
                case "=":
                    getOpperator(display.getText().toString());
            }
        }
    };

    private float solve(String operation) {
        float result = 0;

        // float num[] = operation.split('')

        return result;
    }

    private char getOpperator(String operation) {
        char operator;

        if (operation.contains("+")) {
            String components[] = operation.split("((?<=\\+)|(?=\\+))");

            for (String piece : components) {
                Log.d(this.getClass().getSimpleName(), piece);
            }
        }
        return '~';
    }

}
