package Employer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.magiri.KaziBora.Employer;
import com.magiri.KaziBora.R;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.HashMap;


public class EmployerHome extends AppCompatActivity {
    private Button createButton,signOutBtn;
    private EditText titleEditTxt,descEditTxt;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_home);
        createButton=findViewById(R.id.createBtn);
        titleEditTxt=findViewById(R.id.taskTitle);
        descEditTxt=findViewById(R.id.editTxtDesc);
        signOutBtn=findViewById(R.id.buttonLogout);


        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),EmployerLogin.class));
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task=titleEditTxt.getText().toString().trim();
                String taskDesc=descEditTxt.getText().toString().trim();
                if(TextUtils.isEmpty(task)){
                    titleEditTxt.setError("required");
                }
                else if(TextUtils.isEmpty(taskDesc)){
                    descEditTxt.setError("required");
                }
                else{
                    uploadTask(task,taskDesc);
                }
            }
        });


    }

    private void uploadTask(String task, String taskDesc) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("Title",task);
        map.put("Task_Description",taskDesc);
        FirebaseDatabase.getInstance().getReference().child("Task").push().updateChildren(map);
        FancyToast.makeText(getApplicationContext(),"Task Created!",FancyToast.LENGTH_SHORT,FancyToast.DEFAULT,true).show();
        titleEditTxt.setText("");
        descEditTxt.setText("");
    }
}