package com.example.demo.main.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;

import java.util.ArrayList;

import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.NumberPicker;
import cn.addapp.pickers.picker.SinglePicker;

/**
 * Created by ChenTeacher on 2017/12/4.
 */

public class SeePatientActivity extends Activity implements View.OnClickListener{
    private ImageButton close_button;
    private Button select_sex;
    private Button select_age;
    private Button select_local;
    private TextView patient_local;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_patient);
        initView();
    }
    /*初始化组件*/
    private void initView(){
        close_button = (ImageButton) findViewById(R.id.close_btn);
        select_age = (Button) findViewById(R.id.activity_see_patient_select_age);
        select_sex = (Button) findViewById(R.id.activity_see_patient_select_sex);
        select_local = (Button) findViewById(R.id.activity_see_patient_select_local);
        patient_local = (TextView) findViewById(R.id.activity_see_patient_local);
        close_button.setOnClickListener(this);
        select_age.setOnClickListener(this);
        select_sex.setOnClickListener(this);
        select_local.setOnClickListener(this);
    }
    /** 关闭activity*/
    private void closeActivty(){
        finish();
    }

    /**选择年龄*/
    private void selectAge(){
        NumberPicker picker = new NumberPicker(this);
        picker.setWidth(picker.getScreenWidthPixels());
        picker.setCanLoop(false);
        picker.setLineVisible(false);
        picker.setWheelModeEnable(true);
        picker.setOffset(2);//偏移量
        picker.setRange(1, 200, 1);//数字范围
        picker.setSelectedItem(1);
        picker.setLabel("岁");
        picker.setOnNumberPickListener(new NumberPicker.OnNumberPickListener() {
            @Override
            public void onNumberPicked(int index, Number item) {
//                showToast("index=" + index + ", item=" + item.intValue());
                select_age.setText(Integer.toString( item.intValue()));

            }
        });
        picker.show();
    }
    /**选择性别*/
    private void selectSex(){
        ArrayList<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        SinglePicker<String> picker = new SinglePicker<>(this, list);
        picker.setCanLoop(false);//不禁用循环
        picker.setLineVisible(true);
        picker.setShadowVisible(true);
        picker.setTextSize(18);
        picker.setSelectedIndex(0);
        picker.setWheelModeEnable(true);
        //启用权重 setWeightWidth 才起作用
        picker.setLabel("");
        picker.setWeightEnable(true);
        picker.setWeightWidth(1);
        picker.setSelectedTextColor(0xFF279BAA);//前四位值是透明度
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setOnSingleWheelListener(new OnSingleWheelListener() {
            @Override
            public void onWheeled(int index, String item) {
            }
        });
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                select_sex.setText(item);
            }
        });
        picker.show();
    }
    /**选择地址*/
    private void selectLocal(){

        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {

                Toast.makeText(getApplication(),"数据初始化失败",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                String s ="";
                if (county == null) {
                    s = province.getAreaName()+city.getAreaName();
                } else {
                    s = province.getAreaName()+city.getAreaName()+county.getAreaName();
                }
                Toast.makeText(getApplication(),s,Toast.LENGTH_LONG).show();
                patient_local.setText(s);
            }
        });
        task.execute("北京", "北京", "东城");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close_btn:
                closeActivty();
                break;
            case R.id.activity_see_patient_select_age:
                selectAge();
                break;
            case R.id.activity_see_patient_select_sex:
                selectSex();
                break;
            case R.id.activity_see_patient_select_local:
                selectLocal();
                break;
        }
    }
}
