package com.example.admin.gyl.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.ylfcf.gyl.R;

import java.util.Date;

/**
 * Created by Administrator on 2018/2/1.
 */

public class LoadingDialog extends ProgressDialog {
    private Context mContext;
    private String mLoadingTip;
    private TextView mLoadingTv;
    private int count = 0;
    private long showTime;//loadingDialog开始显示的时间
    private long endTimeTemp;

    public LoadingDialog(Context context, String content) {
        super(context, R.style.CustomProgressDialog);
        this.mContext = context;
        this.mLoadingTip = content;
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void show() {
        super.show();
        showTime = new Date().getTime();
        endTimeTemp = showTime + 1000;
    }

    @Override
    public void dismiss() {
//    	long nowTime = new Date().getTime();
//    	if(nowTime < endTimeTemp){
//    		new Handler().postDelayed(new Runnable() {
//				@Override
//				public void run() {
//					dismiss();
//				}
//			}, 300L);//延迟半秒消失。不至于网速太快时会出现闪屏的情况。
//    		return;
//    	}
        super.dismiss();
    }

    private void initData() {
        mLoadingTv.setText(mLoadingTip);
    }

    public void setContent(String str) {
        mLoadingTv.setText(str);
    }

    private void initView() {
        setContentView(R.layout.progress_dialog);
        mLoadingTv = (TextView) findViewById(R.id.loadingTv);
    }
}
