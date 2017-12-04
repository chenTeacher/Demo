package com.example.demo.main.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.demo.R;

/**
 * 添加患者信息Activity
 */
public class AddPatientCaseActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case_patient);
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
