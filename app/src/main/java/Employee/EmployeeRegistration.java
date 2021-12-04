package Employee;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import Employer.EmployerLogin;
import com.magiri.KaziBora.R;

public class EmployeeRegistration extends AppCompatActivity {
    private EditText nameEditTxt,passwordEditTxt,repeatEditTxt,idNoEditTxt,emailEditTxt,phoneEditTxt;
    private TextView signTextView;
    private Button registerBtn;
    private FirebaseAuth mAuth;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration);
        nameEditTxt = findViewById(R.id.nameEditTxt);
        passwordEditTxt = findViewById(R.id.passwordEditTxt);
        repeatEditTxt = findViewById(R.id.repeatPassEditTxt);
        idNoEditTxt = findViewById(R.id.idnoEditTxt);
        emailEditTxt = findViewById(R.id.emailEditTxt);
        signTextView = findViewById(R.id.signUri);
        phoneEditTxt = findViewById(R.id.phoneEditTxt);
        registerBtn = findViewById(R.id.registerBtn);
        progress=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();

        signTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EmployerLogin.class));
                finish();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerEmployee();

            }
        });
    }

    private void registerEmployee() {
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
            else if(!password.equals(repeatPass)){
                Toast.makeText(getApplicationContext(),"Passwords do not Match",Toast.LENGTH_LONG).show();
            }
            else{
                progress.setTitle("Please Wait...");
                progress.setMessage("Registering user");
                progress.setCanceledOnTouchOutside(false);
                progress.show();
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference("Employee");
                            String uid=mAuth.getUid();
                            Employee employee=new Employee(Name,userId,email,phoneNumber);
                            if(uid!=null){
                                dbRef.child(uid).setValue(employee).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progress.dismiss();
                                        startActivity(new Intent(getApplicationContext(),EmployeeHome.class));
                                        finish();
                                    }
                                });
                            }

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(),"Error Occurred",Toast.LENGTH_LONG).show();
                        signTextView.setError(e.toString());
                        signTextView.requestFocus();
                    }
                });


            }
        }
}