package com.example.admin.gyl.personcenter.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.gyl.base.BaseFragment;
import com.example.admin.gyl.personcenter.NewLoanActivity;
import com.example.admin.gyl.utils.Util;
import com.ylfcf.gyl.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewLoan2Fragment extends BaseFragment {


    @Override
    public void onFragmentClick(View v) {
        switch (v.getId()) {
            case R.id.common_topbar_left_layout:
                ((NewLoanActivity) mContext).changeFragment(0);
                break;
            case R.id.login_activity_btn1:
                ((NewLoanActivity) mContext).changeFragment(0);
                break;
            case R.id.login_activity_btn2:
                Util.toastShort(mContext, "保存成功！");
                break;
            case R.id.login_activity_btn3:
                ((NewLoanActivity) mContext).changeFragment(2);
                break;
        }
    }

    @Override
    protected void initData(View view) {
        TextView topTitleTV = (TextView) view.findViewById(R.id.common_topbar_title);
        LinearLayout topLeftLayout = (LinearLayout) view.findViewById(R.id.common_topbar_left_layout);
        topLeftLayout.setOnClickListener(this);

        ImageView topLeftBtn = (ImageView) view.findViewById(R.id.common_topbar_left_btn);
        topLeftBtn.setImageResource(R.drawable.back_img);
        topTitleTV.setText("新建借款申请");

        Button login_activity_btn1 = view.findViewById(R.id.login_activity_btn1);
        Button login_activity_btn2 = view.findViewById(R.id.login_activity_btn2);
        Button login_activity_btn3 = view.findViewById(R.id.login_activity_btn3);
        login_activity_btn1.setOnClickListener(this);
        login_activity_btn2.setOnClickListener(this);
        login_activity_btn3.setOnClickListener(this);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_new_loan2;
    }
}
