package com.example.demo.ui;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.demo.R;

/**
 * Created by ChenTeacher on 2017/12/14.
 */

public class ProgressView extends LinearLayout {


    private ImageView imagiv;

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.dialog_custom_progress, this);
        imagiv = (ImageView)findViewById(R.id.iv_loading);
    }
    public void startprogress(){
        ((AnimationDrawable)imagiv.getDrawable()).start();
    }
    public void stopprogress(){
        ((AnimationDrawable)imagiv.getDrawable()).stop();
    }

}