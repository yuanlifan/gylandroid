package com.example.admin.gyl.personcenter.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.gyl.base.BaseFragment;
import com.example.admin.gyl.personcenter.NewLoanActivity;
import com.ylfcf.gyl.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewLoan1Fragment extends BaseFragment {


    @Override
    public void onFragmentClick(View v) {
        switch (v.getId()) {
            case R.id.common_topbar_left_layout:
                ((NewLoanActivity) mContext).finish();
                break;

            case R.id.login_activity_btn:
                //todo 下一步
                ((NewLoanActivity) mContext).changeFragment(1);
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

        Button login_activity_btn = view.findViewById(R.id.login_activity_btn);
        login_activity_btn.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_new_loan1;
    }


}
