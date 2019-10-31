/*
* Evan Tellep
*/
package com.example.evancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView display; // Grab the text view that will display the operations

    private boolean solved = false; // Whether the = button has been pressed or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display_text); // Initialize the display variable with our text view

        // Get all the buttons in an array list
        ArrayList<View> allButtons;
        allButtons = (findViewById(R.id.calculator)).getTouchables();

        // For each button add the same event listener
        for (View view : allButtons) {
            Button button = (Button) view;

            button.setOnClickListener(genericButtonClick);
        }
    }

    /**
     * Create an event listener to handle all the button clicks.
     * If it's numbers, operators, or the decimal point, just write it to the text view.
     * If it's the = button, solve the equation and display the answer.
     * If it's the C button, clear the display.
     */
    private View.OnClickListener genericButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Safely cast our views to Buttons
            Button button = null;
            if (v instanceof Button) {
                button = (Button) v; // Casting our view to a button since we know that's what it is.
            }

            String action = null; // Initialize a variable to hold our high level action the user wants to perform.

            // See if the button is one that will simply get written to the display
            if (button.getText().toString().matches("([0-9]+)|([\\+\\-\\*\\/])|(\\.)")) {
                action = "writable";
            }

            // Check if the user wants to solve the equation
            if (button.getText().toString().equals("=")) {
                action = "solve";
            }

            // Check if the user wants to clear the screen
            if (button.getText().toString().equals("C")) {
                action = "clear";
            }

            // If the user has already solved an equation, but decides to type some more go ahead and clear the display
            if (solved == true) {
                display.setText("");
                solved = false;
            }

            // Look at what action the user wants to perform and respond accordingly.
            switch(action) {
                case "writable": // User pressed a button that simply gets written to the screen.
                    display.append(button.getText());
                    break;
                case "solve": // User wishes to solve the equation.
                    if (!display.getText().toString().isEmpty()) {
                        display.setText(solve(display.getText().toString()));
                        solved = true;
                    }
                    break;
                case "clear": // User wants to clear the display.
                    display.setText("");
                    break;
            }
        }
    };

    /**
     * Based on the operator, perform the desired operation.
     * @param operation The full equation that's been put in the display.
     * @return The result of the math operation.
     */
    private String solve(String operation) {
        float result = 0;

        // Grab the components of the equation
        String components[] = getComponents(operation);

        // Since the calculator is a simple one, and only designed to handle one operation at a time the operator will always be index 1
        switch (components[1]) {
            case "+":
                result = Float.parseFloat(components[0]) + Float.parseFloat(components[2]);
                break;
            case "-":
                result = Float.parseFloat(components[0]) - Float.parseFloat(components[2]);
                break;
            case "*":
                result = Float.parseFloat(components[0]) * Float.parseFloat(components[2]);
                break;
            case "/":
                result = Float.parseFloat(components[0]) / Float.parseFloat(components[2]);
                break;
        }

        return String.valueOf(result);
    }

    /**
     * Split the equation string into it's various components.
     * @param operation The equation string within the display of the calculator.
     * @return An array of the 3 components that make up the string. [left hand argument][operator][right hand argument]
     */
    private String[] getComponents(String operation) {
        String components[] = null;

        // The regex in this simply also gives you back the delimiter, which in this case is the operator.
        if (operation.contains("+")) {
            components = operation.split("((?<=\\+)|(?=\\+))"); // Split on +
        }
        else if (operation.contains("-")) {
            components = operation.split("((?<=\\-)|(?=\\-))"); // Split on -
        }
        else if (operation.contains("*")) {
            components = operation.split("((?<=\\*)|(?=\\*))"); // Split on *
        }
        else if (operation.contains("/")) {
            components = operation.split("((?<=\\/)|(?=\\/))"); // Split on /
        }

        return components;
    }

}
