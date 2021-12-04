package Employee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.magiri.KaziBora.R;

import java.util.ArrayList;

public class EmployeeHome extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_home);
        listView=findViewById(R.id.list_view);
        ArrayList<String> taskTitle=new ArrayList<>();
        ArrayList<String> taskDesc=new ArrayList<>();
        DatabaseReference mRef= FirebaseDatabase.getInstance().getReference();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren()){
                    taskTitle.add(data.child("Title").getValue().toString());
                    taskDesc.add(data.child("Task_Description").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,android.R.id.text1,);
        listView.setAdapter(arrayAdapter);
//        ListAdapter listAdapter=new ListAdapter(this,taskTitle,taskDesc,)

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
//class ListAdapter extends ArrayAdapter<String>{
//        private Activity context;
//        private ArrayList<String> TaskTitle;
//        private ArrayList<String> TaskDesc;
//
//        //constructor
//    public ListAdapter(Activity context,ArrayList<String> TaskTitle,ArrayList<String> TaskDesc) {
//        super(context, R.layout.list_item, TaskTitle);
//        this.context = context;
//        this.TaskTitle = TaskTitle;
//        this.TaskDesc=TaskDesc;
//    }
//
//}
