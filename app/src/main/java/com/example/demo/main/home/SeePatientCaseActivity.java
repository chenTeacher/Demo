package com.example.demo.main.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.demo.R;

/**
 * Created by ChenTeacher on 2017/12/4.
 */

public class SeePatientCaseActivity extends Activity implements View.OnClickListener{
    private ImageButton close_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_case_patient);
        close_button = (ImageButton) findViewById(R.id.close_btn);
        close_button.setOnClickListener(this);
    }
    private void closeActivty(){
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close_btn:
                closeActivty();
                break;
        }
    }
}
