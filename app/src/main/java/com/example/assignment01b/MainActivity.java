package com.example.assignment01b;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText tempEntered;
    TextView conversionResults;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tempEntered = findViewById(R.id.enterTemp);
        conversionResults = findViewById(R.id.conversionResults);
        radioGroup = findViewById(R.id.radioGroup);

        findViewById(R.id.resetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempEntered.setText("");
                conversionResults.setText("");
                radioGroup.clearCheck();
            }
        });

        findViewById(R.id.calculateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredTemp = tempEntered.getText().toString();

                try {
                    double tempNum = Double.valueOf(enteredTemp);
                    double convertedTemp = 0.0;
                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    if (checkedId == R.id.fToCButton) {
                        convertedTemp = (tempNum - 32.0) * ((double) 5 /9);
                    } else if (checkedId == R.id.cToFButton) {
                        convertedTemp = (tempNum *((double) 9 /5)) + 32;
                    }
                    String convertTempString = convertedTemp + "";
                    conversionResults.setText(convertTempString);
                } catch (NumberFormatException exception) {
                    Log.d("error", "invalid number entered");
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
    }
}