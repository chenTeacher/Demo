package com.example.demo.main.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.utils.Code;

import java.io.File;
import java.util.ArrayList;

import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.NumberPicker;
import cn.addapp.pickers.picker.SinglePicker;

/**
 * 添加患者信息Activity
 */
public class AddPatientActivity extends Activity implements View.OnClickListener{
    private ImageButton close_button;
    private Button select_sex;
    private Button select_age;
    private Button select_local;
    private Button mCamera;
    private TextView patient_local;
    private ImageView head_portrait;

    //调用照相机返回图片文件
    private File tempFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        initView();
    }

    /*初始化组件*/
    private void initView(){
        close_button = (ImageButton) findViewById(R.id.close_btn);
        select_age = (Button) findViewById(R.id.activity_add_patient_select_age);
        select_sex = (Button) findViewById(R.id.activity_add_patient_select_sex);
        select_local = (Button) findViewById(R.id.activity_see_patient_select_local);
        patient_local = (TextView) findViewById(R.id.activity_add_patient_local);
        mCamera  = (Button) findViewById(R.id.activity_add_patient_camera);
        head_portrait = (ImageView) findViewById(R.id.add_patient_head_portrait);
        close_button.setOnClickListener(this);
        select_age.setOnClickListener(this);
        select_sex.setOnClickListener(this);
        select_local.setOnClickListener(this);
        mCamera.setOnClickListener(this);
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
    /**
     * 从相册获取图片
     */
    private void getPicFromAlbm() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, Code.ALBUM_REQUEST_CODE);
    }
    /**
     * 从相机获取图片
     */
    private void getPicFromCamera() {
        //用于保存调用相机拍照后所生成的文件
        tempFile = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(AddPatientActivity.this, "com.example.demo", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, Code.CAMERA_REQUEST_CODE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close_btn:
                closeActivty();
                break;
            case R.id.activity_add_patient_select_age:
                selectAge();
                break;
            case R.id.activity_add_patient_select_sex:
                selectSex();
                break;
            case R.id.activity_see_patient_select_local:
                selectLocal();
                break;
            case R.id.activity_add_patient_camera:
                getPicFromCamera();
                break;
        }
    }
    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, Code.CROP_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case Code.CAMERA_REQUEST_CODE:   //调用相机后返回
                if (resultCode == RESULT_OK) {
                    //用相机返回的照片去调用剪裁也需要对Uri进行处理
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(AddPatientActivity.this, "com.example.demo", tempFile);
                        cropPhoto(contentUri);
                    } else {
                        cropPhoto(Uri.fromFile(tempFile));
                    }
                }
                break;
            case Code.ALBUM_REQUEST_CODE:    //调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    cropPhoto(uri);
                }
                break;
            case Code.CROP_REQUEST_CODE:     //调用剪裁后返回
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle.getParcelable("data");
                    //设置到ImageView上
                    head_portrait.setImageBitmap(image);
                    //也可以进行一些保存、压缩等操作后上传
//                    String path = saveImage("crop", image);
                }
                break;
        }
    }
}
