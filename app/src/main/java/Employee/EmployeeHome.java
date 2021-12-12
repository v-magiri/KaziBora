package Employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.magiri.KaziBora.HelperAdapter;
import com.magiri.KaziBora.R;
import com.magiri.KaziBora.Task;

import java.util.ArrayList;
import java.util.HashMap;

import Employer.EmployerLogin;

public class EmployeeHome extends AppCompatActivity {
    private RecyclerView recyclerView;
    HelperAdapter helperAdapter;
    private Button signOutBtn,taskBtn;
    ArrayList<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_home);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasks = new ArrayList<>();
        taskBtn=findViewById(R.id.taskButton);
        signOutBtn=findViewById(R.id.buttonLogout);
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Task");
        helperAdapter = new HelperAdapter(this, tasks);
        recyclerView.setAdapter(helperAdapter);

        //signingout the user
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), EmployerLogin.class));
                finish();
            }
        });

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String title = dataSnapshot.child("Title").getValue().toString();
                    String desc = dataSnapshot.child("Task_Description").getValue().toString();
                    Task task= new Task(title,desc);
                    tasks.add(task);
                    helperAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
