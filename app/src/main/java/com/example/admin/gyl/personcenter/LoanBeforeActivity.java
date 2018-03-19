package com.example.admin.gyl.personcenter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.gyl.base.BaseActivity;
import com.ylfcf.gyl.R;

/**
 * 代签
 * 
 */

public class LoanBeforeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_before);
    }

    @Override
    public void initView() {
        TextView topTitleTV = (TextView) findViewById(R.id.common_topbar_title);
        LinearLayout topLeftLayout = (LinearLayout) findViewById(R.id.common_topbar_left_layout);
        topLeftLayout.setOnClickListener(this);

        ImageView topLeftBtn = (ImageView) findViewById(R.id.common_topbar_left_btn);
        topLeftBtn.setImageResource(R.drawable.back_img);
        topTitleTV.setText("借款管理");
    }

    @Override
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.common_topbar_left_layout:
                finish();
                break;
        }
    }

}
