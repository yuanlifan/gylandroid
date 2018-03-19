package com.example.admin.gyl.personcenter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.gyl.base.BaseActivity;
import com.ylfcf.gyl.R;

/**
 * 新建贷款申请
 *
 */

public class NewLoanActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_loan);

    }

    @Override
    public void initView() {
        TextView topTitleTV = (TextView) findViewById(R.id.common_topbar_title);
        LinearLayout topLeftLayout = (LinearLayout) findViewById(R.id.common_topbar_left_layout);
        topLeftLayout.setOnClickListener(this);

        ImageView topLeftBtn = (ImageView) findViewById(R.id.common_topbar_left_btn);
        topLeftBtn.setImageResource(R.drawable.back_img);
        topTitleTV.setText("新建借款申请");
    }

    @Override
    public void onViewClick(View v) {

    }


}
