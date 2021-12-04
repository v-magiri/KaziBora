package Employer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.magiri.KaziBora.Employer;
import com.magiri.KaziBora.R;

public class EmployerLogin extends AppCompatActivity {
    private EditText idNoEditTxt,passwordEditTxt;
    private TextView registerTextView;
    private Button signinBtn;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_login);
        idNoEditTxt=findViewById(R.id.idnoEditTxt);
        passwordEditTxt=findViewById(R.id.passwordEditTxt);
        registerTextView=findViewById(R.id.registerTxt);
        mAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        signinBtn=findViewById(R.id.loginButton);

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EmployerRegistration.class));
            }
        });
    }

    private void loginUser() {
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
            progressDialog.setTitle("Please Wait ....");
            progressDialog.setMessage("Signing as Employer");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(userId,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Successfully logged",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),EmployerHome.class));
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Error Ocurred",Toast.LENGTH_LONG).show();
                    registerTextView.setError(e.toString());
                }
            });
        }
    }
}