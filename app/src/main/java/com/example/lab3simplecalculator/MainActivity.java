package com.example.lab3simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView resultEdit;
    private double valueOne = Double.NaN;
    private double valueTwo;
    private char currentOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set up the window insets handling
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the TextView for displaying results
        resultEdit = findViewById(R.id.resultEdit);
    }

    // Generalized method for number buttons
    public void onNumberClick(View view) {
        Button button = (Button) view;
        resultEdit.append(button.getText().toString());
    }

    // Generalized method for operator buttons
    public void onOperatorClick(View view) {
        Button button = (Button) view;
        String operator = button.getText().toString();

        if (!Double.isNaN(valueOne)) {
            valueTwo = Double.parseDouble(resultEdit.getText().toString());

            switch (currentOperation) {
                case '+':
                    valueOne += valueTwo;
                    break;
                case '-':
                    valueOne -= valueTwo;
                    break;
                case '*':
                    valueOne *= valueTwo;
                    break;
                case '/':
                    if (valueTwo != 0) {
                        valueOne /= valueTwo;
                    } else {
                        resultEdit.setText("Error");
                        return;
                    }
                    break;
            }
        } else {
            valueOne = Double.parseDouble(resultEdit.getText().toString());
        }

        currentOperation = operator.charAt(0);
        resultEdit.setText("");
    }

    // Method for equals button
    public void btnEqualsClick(View view) {
        if (!Double.isNaN(valueOne)) {
            valueTwo = Double.parseDouble(resultEdit.getText().toString());

            switch (currentOperation) {
                case '+':
                    valueOne += valueTwo;
                    break;
                case '-':
                    valueOne -= valueTwo;
                    break;
                case '*':
                    valueOne *= valueTwo;
                    break;
                case '/':
                    if (valueTwo != 0) {
                        valueOne /= valueTwo;
                    } else {
                        resultEdit.setText("Error");
                        return;
                    }
                    break;
            }

            resultEdit.setText(String.valueOf(valueOne));
            valueOne = Double.NaN; // Reset after operation
        }
    }

    // Method for the CE (Clear Entry) button
    public void btnCeClick(View view) {
        resultEdit.setText("");
        valueOne = Double.NaN;
        valueTwo = Double.NaN;
        currentOperation = ' ';
    }

    public void btnDotClick(View view) {
    String currentText = resultEdit.getText().toString();
    if (!currentText.contains(".")) {
        resultEdit.append(".");
    }
  }
}