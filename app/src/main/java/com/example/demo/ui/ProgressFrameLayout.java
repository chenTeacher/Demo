package com.example.demo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.demo.R;

/**
 * Created by ChenTeacher on 2017/12/14.
 */

public class ProgressFrameLayout extends FrameLayout {

    private ProgressView progressView;
    private TextView noResultTv;
    private TextView failTv;

    private AgainRequestListener mAgainRequestListener;

    public void setAgainRequestListener(AgainRequestListener listener){
        mAgainRequestListener=listener;
    }
    public interface AgainRequestListener{
        void againRequest();
    }

    public ProgressFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.frame_refresh_layout, this);
        progressView= (ProgressView) findViewById(R.id.progress_frame);
        noResultTv= (TextView) findViewById(R.id.tv_no_result);
        failTv = (TextView) findViewById(R.id.tv_load_fail);
        failTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAgainRequestListener.againRequest();
            }
        });
    }
    public void showRefresh(){//正在加载
        progressView.setVisibility(View.VISIBLE);
        progressView.startprogress();
        noResultTv.setVisibility(View.GONE);
        failTv.setVisibility(View.GONE);
    }
    public void stopRefresh(){//加载成功
        progressView.stopprogress();
        progressView.setVisibility(View.GONE);
        noResultTv.setVisibility(View.GONE);
        failTv.setVisibility(View.GONE);
    }
    public void setNoResult(){//暂无数据
        progressView.stopprogress();
        progressView.setVisibility(View.GONE);
        noResultTv.setVisibility(View.VISIBLE);
        failTv.setVisibility(View.GONE);
    }
    public void setLoadFail(){//加载失败
        progressView.stopprogress();
        progressView.setVisibility(View.GONE);
        noResultTv.setVisibility(View.GONE);
        failTv.setVisibility(View.VISIBLE);
    }


}