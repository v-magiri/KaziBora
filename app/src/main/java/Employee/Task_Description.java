package Employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.magiri.KaziBora.R;

public class Task_Description extends AppCompatActivity {
    private Button bindBtn;
    private TextView titleTxt;
    private EditText descEditTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_description);
        bindBtn=findViewById(R.id.bindBtn);
        titleTxt=findViewById(R.id.titleTxt);
        descEditTxt=findViewById(R.id.desc_editTxt);
        Bundle extra=getIntent().getExtras();
//        Intent intent=getIntent();
        titleTxt.setText(extra.getString("Task_Title"));
        descEditTxt.setText(extra.getString("Task_Description"));
    }
}