package com.example.evancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

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

            String action = button.getText().toString();

            if (button.getText().toString().matches("[0-9]")) {
                action = "num";
            }

            switch(action) {
                case "num":
                    display.append(button.getText());
                    break;
                case "=":

            }
        }
    };

    //private float

}
