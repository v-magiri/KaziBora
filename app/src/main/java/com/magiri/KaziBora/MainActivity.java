package com.magiri.KaziBora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Employee.EmployeeLogin;
import Employer.EmployerLogin;

public class MainActivity extends AppCompatActivity {
    private Button EmployerBtn,EmployeeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EmployeeBtn=findViewById(R.id.employeeButton);
        EmployerBtn=findViewById(R.id.employerButton);

        EmployerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EmployerLogin.class));
            }
        });

        EmployeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EmployeeLogin.class));
            }
        });


    }
}