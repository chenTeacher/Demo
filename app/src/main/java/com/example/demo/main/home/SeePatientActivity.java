package com.example.demo.main.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.demo.R;

/**
 * Created by ChenTeacher on 2017/12/4.
 */

public class SeePatientActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_patient);
    }
    private void closeActivty(){
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 0:
                break;
        }
    }
}
