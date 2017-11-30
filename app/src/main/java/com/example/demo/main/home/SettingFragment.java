package com.example.demo.main.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo.R;


public class SettingFragment extends Fragment {
    private View mCacheView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mCacheView == null) {
            Log.i("Test", "FindFragment onCreateView");
            mCacheView = inflater.inflate(R.layout.fragment_setting, null);
        }
        ViewGroup parent = (ViewGroup) mCacheView.getParent();
        if (parent != null) {
            parent.removeView(mCacheView);
        }
        return mCacheView;
    }
}
