package com.example.demo.main.home;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.demo.R;


public class SettingFragment extends Fragment {
    private View mCacheView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mCacheView == null) {
            mCacheView = inflater.inflate(R.layout.fragment_setting, null);
        }
        ViewGroup parent = (ViewGroup) mCacheView.getParent();
        if (parent != null) {
            parent.removeView(mCacheView);
        }
        return mCacheView;
    }
    @Override
    public void onStart() {
        super.onStart();
        ImageView iv_loading = (ImageView) mCacheView.findViewById(R.id.iv_loading);
        AnimationDrawable loadingDrawable = (AnimationDrawable) iv_loading.getDrawable();
        loadingDrawable.start();
    }
}
