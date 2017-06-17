package com.basasagerald.incrs.Lecturer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.basasagerald.incrs.Message.DataBaseHelper;
import com.basasagerald.incrs.R;

/**
 * Created by basasagerald on 3/1/2017.
 */

public class Questions_Lecturer extends AppCompatActivity implements View.OnClickListener {
    EditText message;
    Button send,sendoption;
    TextView sent;
    int id;
    String s,Question;
    LinearLayout footer,options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_lecturer);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        message=(EditText) findViewById(R.id.answer);
        send=(Button) findViewById(R.id.send);
        sent=(TextView)findViewById(R.id.messagesent);
        sendoption=(Button) findViewById(R.id.sendoption);
        footer=(LinearLayout)findViewById(R.id.footer);
        options=(LinearLayout)findViewById(R.id.options);

        sent.setText("QUESTION  :"+getIntent().getStringExtra("message"));
        id =getIntent().getIntExtra("id",0);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveresponse();
                message.setText("");
            }
        });
        sendoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Questions_Lecturer.this, "This is: " + Question+"-"+s,
                        Toast.LENGTH_LONG).show();

            }
        });

        fetchQuestion();

    }
    public void fetchQuestion(){
        String query = "select  * from message where ID='"+id+"';";

        SQLiteDatabase db = openOrCreateDatabase("MessageDB",MODE_PRIVATE,null);
        try {
            Cursor cursor = db.rawQuery(query,null);
            cursor.moveToFirst();
            do {
                int idz=cursor.getInt(0);
                String q =cursor.getString(1);
                String questiontype=cursor.getString(2);

                if (questiontype=="O"){
                    options.setVisibility(View.INVISIBLE);
                }else {
                    footer.setVisibility(View.INVISIBLE);
                }
                if (q.contains("-")){
                    RadioGroup rgp = (RadioGroup) findViewById(R.id.radio_group);
                    rgp.setOrientation(LinearLayout.VERTICAL);
                    String message="question#option1@option2@option3@option4@option5@option6";
                    String [] optionz=q.split("-");

                    for (int i = 1; i <= optionz.length; i++) {
                        RadioButton rbn = new RadioButton(this);
                        rbn.setId(i + 1000);
                        rbn.setText(optionz [optionz.length-i]);
                        rbn.setOnClickListener(this);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT, 1f);
                        rbn.setLayoutParams(params);
                        rgp.addView(rbn);
                    }

                }else {
                    System.out.println("open ended question");
                }

            }while (cursor.moveToNext());
        }catch (Exception e){
            System.out.println(e);
        }
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

    @Override
    public void onClick(View view) {
        try {
            s = ((RadioButton) view).getText().toString();
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
