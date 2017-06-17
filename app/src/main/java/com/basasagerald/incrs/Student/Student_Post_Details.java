package com.basasagerald.incrs.Student;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.basasagerald.incrs.Message.DataBaseHelper;
import com.basasagerald.incrs.R;

/**
 * Created by basasagerald on 3/2/2017.
 */

public class Student_Post_Details extends AppCompatActivity {
    EditText message;
    Button send;
    TextView sent;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_post_details);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        message=(EditText) findViewById(R.id.answer);
        send=(Button) findViewById(R.id.send);
        sent=(TextView)findViewById(R.id.messagesent);

        sent.setText("QUESTION  :"+getIntent().getStringExtra("message"));
        id =getIntent().getIntExtra("id",0);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveresponse();
                message.setText("");
            }
        });
    }
    public void saveresponse(){
        String messageresponse=message.getText().toString().trim();
        DataBaseHelper dataBaseHelper=new DataBaseHelper(this);
        dataBaseHelper.insertresponseIntoDB(id,messageresponse);

    }
    @Override
    public void onBackPressed() {
        // Write your code here

        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
