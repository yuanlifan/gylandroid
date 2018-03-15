package com.example.admin.gyl.personcenter.fragment;


import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.gyl.base.BaseFragment;
import com.example.admin.gyl.personcenter.AttestActivity;
import com.example.admin.gyl.utils.Util;
import com.ylfcf.gyl.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttestFirstFragment extends BaseFragment {

    private EditText mEt_company_id;
    private EditText mEt_company_name;
    private EditText mEt_company_number1;
    private EditText mEt_company_number2;
    private EditText mEt_bank_name;
    private EditText mEt_company_number3;

    @Override
    protected void initData(View view) {
        TextView topTitleTV = (TextView) view.findViewById(R.id.common_topbar_title);
        LinearLayout topLeftLayout = (LinearLayout) view.findViewById(R.id.common_topbar_left_layout);
        topLeftLayout.setOnClickListener(this);

        mEt_company_id = view.findViewById(R.id.et_company_id);
        mEt_company_name = view.findViewById(R.id.et_company_name);
        mEt_company_number1 = view.findViewById(R.id.et_company_number1);
        mEt_company_number2 = view.findViewById(R.id.et_company_number2);
        mEt_bank_name = view.findViewById(R.id.et_bank_name);
        mEt_company_number3 = view.findViewById(R.id.et_company_number3);

        TextView btn_next = view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        ImageView topLeftBtn = (ImageView) view.findViewById(R.id.common_topbar_left_btn);
        topLeftBtn.setImageResource(R.drawable.back_img);
        topTitleTV.setText("企业概况");
    }

    @Override
    public void onFragmentClick(View view) {
        switch (view.getId()){
            case R.id.common_topbar_left_layout:
                ((AttestActivity) mContext).finish();
                break;

            case R.id.btn_next:
                //TODO 下一步
                boolean isNotEmpty = checkOutText();
                if(isNotEmpty) {
                    ((AttestActivity) mContext).changeFragment(1);
                }
                break;
        }
    }

    private boolean checkOutText() {
        if(TextUtils.isEmpty(mEt_company_id.getText().toString())) {
            //企业名称不能为空
            Util.toastLong(mContext,"企业名称不能为空");
            return false;
        }else {
            if(TextUtils.isEmpty(mEt_company_name.getText().toString())) {
                //企业法人不能为空
                Util.toastLong(mContext,"企业法人不能为空");
                return false;
            }else {
                if(TextUtils.isEmpty(mEt_company_number1.getText().toString())) {
                    //营业执照号不能为空
                    Util.toastLong(mContext,"营业执照号不能为空");
                    return false;
                }else {
                    if(TextUtils.isEmpty(mEt_company_number2.getText().toString())) {
                        //开户许可证号不能为空
                        Util.toastLong(mContext,"开户许可证号不能为空");
                        return false;
                    }else {
                        if(TextUtils.isEmpty(mEt_bank_name.getText().toString())) {
                            //收款银行不能为空
                            Util.toastLong(mContext,"收款银行不能为空");
                            return false;
                        }else {
                            if(TextUtils.isEmpty(mEt_company_number3.getText().toString())) {
                                //收款账号不能为空
                                Util.toastLong(mContext,"收款账号不能为空");
                                return false;
                            }else {
//                                if(TextUtils.isEmpty(mEt_company_number1.getText().toString())) {
//                                    //机构信用代码错误
//                                    Util.toastLong(mContext,"机构信用代码错误");
//                                    return false;
//                                }else {
                                    //所有表格均不为空
                                    return true;
//                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_attest_first;
    }

}
