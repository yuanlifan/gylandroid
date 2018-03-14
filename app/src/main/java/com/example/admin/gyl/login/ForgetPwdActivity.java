package com.example.admin.gyl.login;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.gyl.base.BaseActivity;
import com.ylfcf.gyl.R;

/**
 * 找回密码
 * Created by Administrator on 2018/2/6.
 */

public class ForgetPwdActivity extends BaseActivity {
    private TextView topTitleTV;
    private LinearLayout topLeftLayout;
    private ImageView topLeftBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpwd_activity);
    }

    @Override
    public void findViews() {
        topTitleTV = (TextView) findViewById(R.id.common_topbar_title);
        topLeftLayout = (LinearLayout) findViewById(R.id.common_topbar_left_layout);
        topLeftLayout.setOnClickListener(this);
        topLeftBtn = (ImageView) findViewById(R.id.common_topbar_left_btn);
        topLeftBtn.setImageResource(R.drawable.back_img);
        topTitleTV.setText("找回密码");
    }

    @Override
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.common_topbar_left_layout:
                finish();
                break;
        }
    }
}
