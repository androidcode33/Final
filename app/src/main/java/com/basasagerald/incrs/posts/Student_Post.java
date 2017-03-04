package com.basasagerald.incrs.posts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.basasagerald.incrs.R;
import com.basasagerald.incrs.Recyclerview.DividerItemDecoration;
import com.basasagerald.incrs.Recyclerview.RecyclerTouchListener;
import com.basasagerald.incrs.Recyclerview.Student_Model;
import com.basasagerald.incrs.Recyclerview.Student_PostAdapter;
import com.basasagerald.incrs.Student.Student_Post_Details;
import com.github.fabtransitionactivity.SheetLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by basasagerald on 2/28/2017.
 */

public class Student_Post extends Fragment implements SheetLayout.OnFabAnimationEndListener {
    private List<Student_Model> postList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Student_PostAdapter mAdapter;

    FloatingActionButton fab;
    SheetLayout mSheetLayout;
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

        mAdapter = new Student_PostAdapter(postList);
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
               if (post.equals(0)){
                   startActivity(new Intent(getActivity(), Student_Post_Details.class));
               }
            }
            @Override
            public void onLongClick(View view, int position) {
                Student_Model post = postList.get(position);
                Toast.makeText(getActivity(), post.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();

            }
        }));

        preparePostData();
    }
    private void preparePostData() {
       Student_Model post = new Student_Model("Health", "best description");
        postList.add(post);

        post = new Student_Model("understood", "i have not understood that point 1");
        postList.add(post);

        post = new Student_Model("last slide", "what does deny mean?");
        postList.add(post);

        post = new Student_Model("tired", "its some students are tired");
        postList.add(post);

        post = new Student_Model("feeed back", "feed back on our project");
        postList.add(post);

        post = new Student_Model("deadline", "When is the deadline for course work");
        postList.add(post);



        post = new Student_Model("security", "i have not understood that");
        postList.add(post);

        post = new Student_Model("last slide", "how to hack");
        postList.add(post);

        post = new Student_Model("papers", "Our test question papers");
        postList.add(post);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFabAnimationEnd() {
        Intent intent = new Intent(getActivity(), Post_Question.class);
        startActivityForResult(intent, REQUEST_CODE);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            mSheetLayout.contractFab();
        }
    }
}
