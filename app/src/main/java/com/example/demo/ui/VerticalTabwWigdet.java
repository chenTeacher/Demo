package com.example.demo.ui;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabWidget;

/**
 * Created by ChenTeacher on 2017/11/26.
 */

public class VerticalTabwWigdet extends TabWidget {
    private Resources res;

    public VerticalTabwWigdet(Context context, AttributeSet attrs) {
        super(context, attrs);
        res = context.getResources();
        setOrientation(LinearLayout.VERTICAL);
    }

    @Override
    public void addView(View child) {
        LinearLayout.LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1.0f);
        lp.setMargins(0,0,0,0);
        child.setLayoutParams(lp);
        super.addView(child);
    }
}

