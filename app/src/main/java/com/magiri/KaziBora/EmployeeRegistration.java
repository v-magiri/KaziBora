package com.magiri.KaziBora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmployeeRegistration extends AppCompatActivity {
    private EditText nameEditTxt,passwordEditTxt,repeatEditTxt,idNoEditTxt,emailEditTxt,phoneEditTxt;
    private TextView signTextView;
    private Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration);
        nameEditTxt=findViewById(R.id.nameEditTxt);
        passwordEditTxt=findViewById(R.id.passwordEditTxt);
        repeatEditTxt=findViewById(R.id.repeatPassEditTxt);
        idNoEditTxt=findViewById(R.id.idnoEditTxt);
        emailEditTxt=findViewById(R.id.emailEditTxt);
        signTextView=findViewById(R.id.signUri);
        phoneEditTxt=findViewById(R.id.phoneEditTxt);
        registerBtn=findViewById(R.id.registerBtn);

        signTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EmployerLogin.class));
                finish();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=nameEditTxt.getText().toString().trim();
                String password=passwordEditTxt.getText().toString().trim();
                String repeatPass=repeatEditTxt.getText().toString().trim();
                String email=emailEditTxt.getText().toString().trim();
                String phoneNumber=phoneEditTxt.getText().toString().trim();
                String userId=idNoEditTxt.getText().toString().trim();
                if(Name.isEmpty()){
                    nameEditTxt.setError("Required");
                }
                else if(password.isEmpty()){
                    passwordEditTxt.setError("Required");
                }
                else if(repeatPass.isEmpty()){
                    repeatEditTxt.setError("Required");
                }
                else if(email.isEmpty()){
                    emailEditTxt.setError("Required");
                }
                else if(phoneNumber.isEmpty()){
                    phoneEditTxt.setError("Required");
                }
                else if(userId.isEmpty()){
                    idNoEditTxt.setError("Required");
                }
                else if(password.length()<8){
                    Toast.makeText(getApplicationContext(),"Password Should be 8 character or more",Toast.LENGTH_LONG).show();
                }
                else if(password != repeatPass){
                    Toast.makeText(getApplicationContext(),"Passwords do not Match",Toast.LENGTH_LONG).show();
                }
                else{
                    startActivity(new Intent(getBaseContext(),EmployeeHome.class));
                }
            }
        });
    }
}