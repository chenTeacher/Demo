package com.example.demo.main.home;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demo.R;
import com.example.demo.main.module.Patient;
import com.example.demo.utils.Code;
import com.foamtrace.photopicker.ImageCaptureManager;
import com.foamtrace.photopicker.ImageConfig;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;
import com.foamtrace.photopicker.intent.PhotoPreviewIntent;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.DatePicker;
import cn.addapp.pickers.picker.SinglePicker;

import  com.example.demo.utils.Code;

/**
 * 添加患者信息Activity
 */
public class AddPatientCaseActivity extends Activity implements View.OnClickListener{
    private ImageButton close_button;
    private Button add_case_patient_doctor;
    private Button add_case_patient_start;
    private Button add_case_patient_time;
    private TextView add_patient_name_value;
    private int columnWidth;
    private ArrayList<String> imagePaths = null;
    private GridAdapter gridAdapter;
    private GridView gv;
    private ImageCaptureManager captureManager; // 相机拍照处理类


    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case_patient);
        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("patient");
        initView();
    }
    private void initView(){
        close_button = (ImageButton) findViewById(R.id.close_btn);
        add_case_patient_doctor = (Button) findViewById(R.id.add_case_patient_doctor);
        add_case_patient_start = (Button) findViewById(R.id.add_case_patient_state);
        add_case_patient_time = (Button) findViewById(R.id.add_case_patient_time);
        add_patient_name_value = (TextView) findViewById(R.id.add_patient_name_value);
        gv = (GridView) findViewById(R.id.gv);
        add_patient_name_value.setText(patient.getPatient_name());
        close_button.setOnClickListener(this);
        add_case_patient_doctor.setOnClickListener(this);
        add_case_patient_start.setOnClickListener(this);
        add_case_patient_time.setOnClickListener(this);

        setImage();
        loadAdpater(new ArrayList<String>());
    }
    private void setImage(){
        //得到GridView中每个ImageView宽高
        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;
        gv.setNumColumns(cols);
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int columnSpace = getResources().getDimensionPixelOffset(R.dimen.space_size);
        columnWidth = (screenWidth - columnSpace * (cols-1)) / cols;
        //GridView item点击事件（浏览照片）
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == imagePaths.size()){
                    add_case_patient_capture();
                }else{
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(AddPatientCaseActivity.this);
                    intent.setCurrentItem(position);
                    intent.setPhotoPaths(imagePaths);
                    startActivityForResult(intent,Code.REQUEST_PREVIEW_CODE);
                }

            }
        });
    }
    private void closeActivty(){
        finish();
    }
    private void add_case_patient_time(){
        Calendar calendar = Calendar.getInstance();
        final DatePicker picker = new DatePicker(this);
        picker.setCanLoop(false);
        picker.setWheelModeEnable(true);
        picker.setTopPadding(15);
        picker.setRangeStart(1900, 1, 1);
        picker.setRangeEnd(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
        picker.setSelectedItem(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        picker.setWeightEnable(true);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                add_case_patient_time.setText(year+"-"+month+"-"+day);
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }
    private void add_case_patient_start(){
        ArrayList<String> list = new ArrayList<>();
        list.add("首次面诊");
        list.add("复诊");
        list.add("住院");
        list.add("出院");
        list.add("回访");
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
                add_case_patient_start.setText(item);
            }
        });
        picker.show();
    }
    private void add_case_patient_doctor(){
        ArrayList<String> list = new ArrayList<>();
        list.add("王医师");
        list.add("李医师");
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
                add_case_patient_doctor.setText(item);
            }
        });
        picker.show();
    }
    @Override
    public void onClick(View v) {
        Log.i("capture","add_case_patient_capture");
        switch (v.getId()){
            case R.id.close_btn:
                closeActivty();
                break;
            case R.id.add_case_patient_doctor:
                add_case_patient_doctor();
                break;
            case R.id.add_case_patient_state:
                add_case_patient_start();
                break;
            case R.id.add_case_patient_time:
                add_case_patient_time();
                break;
//            case R.id.add_case_patient_photo:
//                add_case_patient_capture();
//                break;
        }

    }

    private void add_case_patient_capture() {
        PhotoPickerIntent intent1 = new PhotoPickerIntent(AddPatientCaseActivity.this);
        intent1.setSelectModel(SelectModel.MULTI);
        intent1.setShowCarema(true); // 是否显示拍照
        intent1.setMaxTotal(4); // 最多选择照片数量，默认为9
        intent1.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
        startActivityForResult(intent1, Code.REQUEST_CAMERA_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode){
                // 选择照片
                case Code.REQUEST_CAMERA_CODE:
                    loadAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    break;
                //浏览照片
                case Code.REQUEST_PREVIEW_CODE:
                    loadAdpater(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    break;
                // 调用相机拍照
                case ImageCaptureManager.REQUEST_TAKE_PHOTO:
                    if(captureManager.getCurrentPhotoPath() != null) {
                        captureManager.galleryAddPic();
                        ArrayList<String> paths = new ArrayList<>();
                        paths.add(captureManager.getCurrentPhotoPath());
                        loadAdpater(paths);
                    }
                    break;

            }
        }
    }
    private void loadAdpater(ArrayList<String> paths){
        if(imagePaths == null){
            imagePaths = new ArrayList<>();
        }
        imagePaths.clear();
        imagePaths.addAll(paths);
        if(gridAdapter == null){
            gridAdapter = new GridAdapter(imagePaths);
            gv.setAdapter(gridAdapter);
        }else {
            gridAdapter.notifyDataSetChanged();
        }
    }
    private class GridAdapter extends BaseAdapter {
        private ArrayList<String> listUrls;

        public GridAdapter(ArrayList<String> listUrls) {
            this.listUrls = listUrls;
        }

        @Override
        public int getCount() {
            if(listUrls.size() ==4){
                return listUrls.size();
            }
            return listUrls.size()+1;
        }

        @Override
        public String getItem(int position) {
                       if(listUrls.size() ==4){
                    return listUrls.get(position);
                }else{
                    if(position == (listUrls.size())){
                        return null;
                    }else{
                        return listUrls.get(position);
                    }

            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.item_image, null);
                imageView = (ImageView) convertView.findViewById(R.id.add_case_patient_photo);
                convertView.setTag(imageView);
            }else {
                imageView = (ImageView) convertView.getTag();
            }

                if(getItem(position)!=null){
                    Glide.with(AddPatientCaseActivity.this)
                            .load(new File(getItem(position)))
                            .placeholder(R.mipmap.default_error)
                            .error(R.mipmap.default_error)
                            .centerCrop()
                            .crossFade()
                            .into(imageView);
                }else{
                    Glide.with(AddPatientCaseActivity.this)
                            .load(R.drawable.add_image)
                            .placeholder(R.mipmap.default_error)
                            .error(R.mipmap.default_error)
                            .centerCrop()
                            .crossFade()
                            .into(imageView);
                }
            return convertView;
        }
    }
}
