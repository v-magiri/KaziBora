package com.magiri.KaziBora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmployeeLogin extends AppCompatActivity {
    private EditText idNoEditTxt,passwordEditTxt;
    private TextView registerTextView;
    private Button signinBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);
        idNoEditTxt=findViewById(R.id.idnoEditTxt);
        passwordEditTxt=findViewById(R.id.passwordEditTxt);
        registerTextView=findViewById(R.id.registerTxt);
        signinBtn=findViewById(R.id.loginButton);
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId=idNoEditTxt.getText().toString().trim();
                String userPass=passwordEditTxt.getText().toString().trim();
                if(userId.isEmpty()){
                    idNoEditTxt.setError("Required");
                }
                else if(userPass.isEmpty()){
                    passwordEditTxt.setError("Required");
                }
                else if(userPass.length()<8){
                    passwordEditTxt.setError("Password should 8 character or more");
                }
                else{
                    startActivity(new Intent(getApplicationContext(),EmployerHome.class));
                }
            }
        });
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EmployerRegistration.class));
            }
        });
    }
}