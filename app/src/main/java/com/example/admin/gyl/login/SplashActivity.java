package com.example.admin.gyl.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.admin.gyl.base.BaseActivity;
import com.example.admin.gyl.home.MainFragmentActivity;
import com.ylfcf.gyl.R;

/**
 * Created by Administrator on 2018/2/8.
 */

public class SplashActivity extends BaseActivity {
    private static final String className = "SplashActivity";
    private static final int GOTO_MAINACTIVITY = 10;
    private static final int REQUEST_BANNER = 20;
    private ImageView splashImage;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GOTO_MAINACTIVITY:
                    gotoMainActivity();
                    break;
                case REQUEST_BANNER:
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        initView();
        handler.sendEmptyMessageDelayed(GOTO_MAINACTIVITY,2000L);
    }

    @Override
    public void initView() {
        // 方法1 Android获得屏幕的宽和高
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();
        DisplayMetrics metric = getResources().getDisplayMetrics();
        splashImage = (ImageView) findViewById(R.id.splash_activity_image);
    }

    @Override
    public void onViewClick(View v) {

    }

    private void gotoMainActivity() {
        Intent intent = new Intent();
        // 用户主界面
        intent.setClass(SplashActivity.this, MainFragmentActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

}
