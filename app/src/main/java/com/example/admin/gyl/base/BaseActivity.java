package com.example.admin.gyl.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

import com.example.admin.gyl.MainApp;
import com.example.admin.gyl.http.NethHandle;
import com.example.admin.gyl.view.LoadingDialog;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2018/2/1.
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener{
    /**
     * Application实例
     */
    protected MainApp mApp;
    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected LoadingDialog mLoadingDialog = null;
    protected NethHandle netHandler = NethHandle.getNethHandle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mApp = MainApp.getApplication();
        mLoadingDialog = new LoadingDialog(this,"正在加载...");
        mApp.addActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        findViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        onViewClick(v);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mApp.removeActivity(this);
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
        }
    }

    public abstract void findViews();
    public abstract void onViewClick(View v);
}
