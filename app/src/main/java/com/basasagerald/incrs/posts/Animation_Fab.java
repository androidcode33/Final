package com.basasagerald.incrs.posts;

import android.support.v7.app.AppCompatActivity;

import com.basasagerald.incrs.R;

/**
 * Created by basasagerald on 3/1/2017.
 */

public class Animation_Fab extends AppCompatActivity {

    protected void setUpToolbarWithTitle(String title, boolean hasBackButton){

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayShowHomeEnabled(hasBackButton);
            getSupportActionBar().setDisplayHomeAsUpEnabled(hasBackButton);
        }
    }

    protected void enterFromBottomAnimation(){
        overridePendingTransition(R.anim.activity_open_translate_from_bottom, R.anim.activity_no_animation);
    }

    protected void exitToBottomAnimation(){
        overridePendingTransition(R.anim.activity_no_animation, R.anim.activity_close_translate_to_bottom);
    }
}
