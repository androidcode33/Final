package com.basasagerald.incrs.posts;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.basasagerald.incrs.Message.DataBaseHelper;
import com.basasagerald.incrs.NDS.NSDHelper;
import com.basasagerald.incrs.R;
import com.basasagerald.incrs.Recyclerview.DividerItemDecoration;
import com.basasagerald.incrs.Recyclerview.RecyclerTouchListener;
import com.basasagerald.incrs.Recyclerview.Student_Model;
import com.basasagerald.incrs.Recyclerview.Student_PostAdapter;
import com.basasagerald.incrs.Student.Student_Post_Details;
import com.github.fabtransitionactivity.SheetLayout;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by basasagerald on 2/28/2017.
 */

public class Student_Post extends Fragment implements SheetLayout.OnFabAnimationEndListener {
    private List<Student_Model> postList;
    private RecyclerView recyclerView;
    private Student_PostAdapter mAdapter;
    private SQLiteDatabase storeData;
    FloatingActionButton fab;
    SheetLayout mSheetLayout;
    int port;
    int check=0;
    InetAddress host;
    //ProgressDialog dialog=null;
    MyAsyncTask asyc;
    private static final int REQUEST_CODE = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.student_posts, container, false);
        fab=(FloatingActionButton)view.findViewById(R.id.fab);
        mSheetLayout=(SheetLayout)view.findViewById(R.id.bottom_sheet);

        mSheetLayout.setFab(fab);
        mSheetLayout.setFabAnimationEndListener(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSheetLayout.expandFab();
            }
        });

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView)view.findViewById(R.id.student_posts);
        postList = new ArrayList<Student_Model>();
        mAdapter = new Student_PostAdapter(postList);
        //dialog = new ProgressDialog(getContext());
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Student_Model post = postList.get(position);

                Intent intent=new Intent(getActivity(), Student_Post_Details.class);
                intent.putExtra("message", post.getQuestion());
                intent.putExtra("id",post.getId());
                startActivity(intent);

            }
            @Override
            public void onLongClick(View view, int position) {
                Student_Model post = postList.get(position);
            }
        }));
        //fetch();
        getDataFromDB1();

        DataBaseHelper dataBaseHelper=new DataBaseHelper(getContext());
        dataBaseHelper.fetch();
        threadconnection();

    }
    public void threadconnection(){
        check=1;
        ConnectivityManager connManager = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
       // mWifi.isConnected()
        if (check==1) {
          t.run();
//            asyc= new MyAsyncTask();
//            asyc.execute();

        }
        else{
            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            startActivity(intent);
        }

    }
    final Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 0){
                System.out.print("yeeeeeeeeeeeeeeeeeeeeeeeees");
            }else{
                System.out.print("errooooo");
            }
        }
    };

    Thread t = new Thread() {
        @Override
        public void run(){
            NSDHelper intialised=new NSDHelper(getContext());
            intialised.intialised();
            boolean succeed=false;
            if(succeed){
                //we can't update the UI from here so we'll signal our handler and it will do it for us.
                h.sendEmptyMessage(0);
            }else{
                h.sendEmptyMessage(1);
            }
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        getDataFromDB1();
        //threadconnection();
    }
    @Override
    public void onPause() {
        super.onPause();
//        getDataFromDB1();
//        threadconnection();
    }
    @Override
    public void onFabAnimationEnd() {
        Intent intent = new Intent(getActivity(), Post_Question.class);
        startActivityForResult(intent, REQUEST_CODE);

    }
    public void getDataFromDB1(){
        postList.clear();
        String sender ="student";
        String query = "select ID, messages from message where Sender='"+sender+"';";
        String count=("SELECT COUNT(ID) from responses");
        storeData = getContext().openOrCreateDatabase("MessageDB",MODE_PRIVATE,null);
        try {
            storeData.execSQL("CREATE TABLE IF NOT EXISTS message(ID INTEGER PRIMARY KEY AUTOINCREMENT, messages TEXT,  Type TEXT CHECK(Type IN('O','C')) NOT NULL DEFAULT 'O', Sender TEXT CHECK(Sender IN('student','lecturer')) NOT NULL DEFAULT 'student');");
            storeData.execSQL("CREATE TABLE IF NOT EXISTS responses(  ID INTEGER PRIMARY KEY AUTOINCREMENT,MessageID INTEGER, Responses TEXT);");
            Cursor cursor = storeData.rawQuery(query, null);
            Cursor counter = storeData.rawQuery(count, null);
            cursor.moveToFirst();
            do {
                Student_Model model = new Student_Model();
                model.setId(cursor.getInt(0));
                model.setQuestion(cursor.getString(1));

                postList.add(model);
            } while (cursor.moveToNext());

        }catch (Exception e){
            System.out.println(e);

        }
        mAdapter.notifyDataSetChanged();
//         if (counter.moveToFirst()){
//             do {
//             System.out.println("The number is zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz :"+counter.getInt(0));
//             }while (counter.moveToNext());
//         }
        Log.d("message data", postList.toString());

    }
    public void fetch(){
        String query = "select * from message";
        SQLiteDatabase db = getContext().openOrCreateDatabase("MessageDB",MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        do {
            System.out.println("The message is :"+cursor.getString(3));
        }while (cursor.moveToNext());


        db.close();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            mSheetLayout.contractFab();
        }
    }

    class MyAsyncTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {


            return null;
        }
        @Override
        protected void onPreExecute() {
            System.out.print("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        }

        @Override
        protected void onPostExecute(String result) {
            {
                System.out.print("ppppppppppppppppppppppppppppppppppppppppppppppppppp");
                fab.setClickable(true);
                //dialog.dismiss();
            }
        }
    }
}
