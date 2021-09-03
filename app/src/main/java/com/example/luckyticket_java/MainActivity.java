package com.example.luckyticket_java;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView lampImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void CheckIsLucky(View view) {
        lampImage = (ImageView) findViewById(R.id.imgLamp);
        EditText inputNumber = findViewById(R.id.etTicketNumber);
        String sInputNumber = inputNumber.getText().toString().trim();
        if (sInputNumber.length() < getResources().getInteger(R.integer.MAX_NUMBER_OF_DIGITS)) {
            inputNumber.setError(getString(R.string.errorMessage));
        } else {
            int leftPart = sInputNumber.substring(0, 3).chars().sum();
            int rightPart = sInputNumber.substring(3, 6).chars().sum();
            Handler handler = new Handler();
            handler.postDelayed(() -> lampImage.setImageResource(R.drawable.gray_lamp_image), 5000);
            if (leftPart == rightPart) {
                lampImage.setImageResource(R.drawable.green_lamp_image);
                Toast.makeText(this, getString(R.string.toastMessageLucky), Toast.LENGTH_LONG).show();
            } else {
                lampImage.setImageResource(R.drawable.red_lamp_image);
                Toast.makeText(this, getString(R.string.toastMessageNotLucky), Toast.LENGTH_LONG).show();
            }
        }
    }
}