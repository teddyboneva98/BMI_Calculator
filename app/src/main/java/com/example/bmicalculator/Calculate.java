package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Calculate extends AppCompatActivity {

    EditText weight, height;
    TextView resulttext;
    String calculation, BMIresult, calculatedResult;
    Button btnResult, btnAdd;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        resulttext = findViewById(R.id.result);
        btnResult = findViewById(R.id.btnResult);
        btnAdd = findViewById(R.id.btnAdd);
        myDB = new DatabaseHelper(this);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String S1 = weight.getText().toString();
                String S2 = height.getText().toString();

                //Съобщение за грешка
                if(S1.equals("")){
                    weight.setError("Please Enter Your Weight");
                    weight.requestFocus();
                    return;
                }
                else if (S2.equals("")) {
                    height.setError("Please Enter Your Height");
                    height.requestFocus();
                    return;
                }//При изчислен резултат, се появява бутонът за добавяне в история
                else {
                    btnAdd.setVisibility(View.VISIBLE);
                }

                float weightValue = Float.parseFloat(S1);
                float heightValue = Float.parseFloat(S2) / 100;
                //Формула за изчисление
                float bmi = weightValue / (heightValue * heightValue);

                //Изчислява BMI
                if(bmi < 16) {
                    BMIresult = "Severely Underweight";
                } else if (bmi < 18.5) {
                    BMIresult = "Underweight";
                } else if (bmi >= 18.5 && bmi <= 24.9) {
                    BMIresult = "Normal (healthy weight)";
                } else if (bmi >= 25 && bmi <= 29.9) {
                    BMIresult = "Overweight";
                } else {
                    BMIresult = "Obese";
                }
                //Връща изчисления резултат и BMI категорията
                calculation = bmi + "\n" + BMIresult;
                calculatedResult = String.valueOf(bmi);

                resulttext.setText(calculation);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightValue = weight.getText().toString();
                String heightValue = height.getText().toString();

                if (weight.length() != 0 && height.length() != 0 ){
                    AddData(weightValue, heightValue, calculatedResult);
                    weight.setText("");
                    height.setText("");

                } else {
                    Toast.makeText(Calculate.this,"You must enter your weight and height in the fields!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //Добавя ги в БД
    public void AddData(String weight, String height, String calculatedResult) {
        boolean insertData = myDB.addData(weight, height, calculatedResult);

        if(insertData == true){
            Toast.makeText(Calculate.this,"Successfully added!",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(Calculate.this,"Something went wrong!",Toast.LENGTH_LONG).show();
        }
    }
}
