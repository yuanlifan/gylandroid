package com.example.admin.gyl.home.fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.admin.gyl.base.BaseFragment;
import com.example.admin.gyl.personcenter.SettingActivity;
import com.ylfcf.gyl.R;

/**
 * 我的
 * Created by Administrator on 2018/2/5.
 */

public class UserFragment extends BaseFragment {
    private FragmentManager fragmentManager = null;
    private View rootView;

    private LinearLayout zhzlLayout;//账户总览
    private LinearLayout jbszLayout;//基本设置
    private LinearLayout dqglLayout;//贷前管理
    private LinearLayout dqglHidenLayout;//贷前管理隐藏的布局
    private LinearLayout dhglLayout;//贷后管理
    private LinearLayout dhglHidenLayout;//贷后管理隐藏的布局
    private LinearLayout jkglLayout;//借款管理
    private LinearLayout xjjksqLayout;//新建借款申请
    private LinearLayout yfdwjqdjkLayout;//已放贷未结清的借款
    private LinearLayout yjqdjkLayout;//已结清的借款

    private ImageView dqglArrow,dhglArrow;

    public static Fragment newInstance(int position) {
        UserFragment f = new UserFragment();
        Bundle args = new Bundle();
        args.putInt("num", position);
        f.setArguments(args);
        return f;
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.user_fragment, null);
            findViews(rootView, inflater);
        }
        // 缓存的rootView需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    protected void initData(View view) {

    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    private void findViews(View view, LayoutInflater inflater) {
        zhzlLayout = view.findViewById(R.id.user_fragment_zhzl_layout);
        zhzlLayout.setOnClickListener(this);
        jbszLayout = view.findViewById(R.id.user_fragment_jbsz_layout);
        jbszLayout.setOnClickListener(this);
        dqglLayout = view.findViewById(R.id.user_fragment_dqgl_layout);
        dqglLayout.setOnClickListener(this);
        dqglHidenLayout = view.findViewById(R.id.user_fragment_dqgl_hiden_layout);
        dhglLayout = view.findViewById(R.id.user_fragment_dhgl_layout);
        dhglLayout.setOnClickListener(this);
        dhglHidenLayout = view.findViewById(R.id.user_fragment_dhgl_hiden_layout);
        jkglLayout = view.findViewById(R.id.user_fragment_jkgl_layout);
        jkglLayout.setOnClickListener(this);
        xjjksqLayout = view.findViewById(R.id.user_fragment_xjjksq_layout);
        xjjksqLayout.setOnClickListener(this);
        yfdwjqdjkLayout = view.findViewById(R.id.user_fragment_yfkwjqdjk_layout);
        yfdwjqdjkLayout.setOnClickListener(this);
        yjqdjkLayout = view.findViewById(R.id.user_fragment_yjqdjk_layout);
        yjqdjkLayout.setOnClickListener(this);

        dqglArrow = view.findViewById(R.id.user_fragment_dqgl_arrow);
        dhglArrow = view.findViewById(R.id.user_fragment_dhgl_arrow);
    }

    @Override
    public void onFragmentClick(View v) {
        switch (v.getId()){
            case R.id.user_fragment_zhzl_layout:
                //账户总览
                break;
            case R.id.user_fragment_jbsz_layout:
                //基本设置
                Intent intentSetting = new Intent(mContext, SettingActivity.class);
                startActivity(intentSetting);
                break;
            case R.id.user_fragment_dqgl_layout:
                //贷前管理
                if(dqglHidenLayout.isShown()){
                    rotateEnd(dqglArrow);
                    dqglHidenLayout.setVisibility(View.GONE);
                }else{
                    rotateStart(dqglArrow);
                    dqglHidenLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.user_fragment_jkgl_layout:
                //借款管理
                break;
            case R.id.user_fragment_xjjksq_layout:
                //新建借款申请
                break;
            case R.id.user_fragment_dhgl_layout:
                //贷后管理
                if(dhglHidenLayout.isShown()){
                    rotateEnd(dhglArrow);
                    dhglHidenLayout.setVisibility(View.GONE);
                }else{
                    rotateStart(dhglArrow);
                    dhglHidenLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.user_fragment_yfkwjqdjk_layout:
                //已放款未结清的借款
                break;
            case R.id.user_fragment_yjqdjk_layout:
                //已结清的借款
                break;
        }
    }

    private void rotateStart(ImageView img){
        ObjectAnimator.ofFloat(img, "rotation", 0.0F, 90.0F)
                .setDuration(200)
                .start();
    }

    private void rotateEnd(ImageView img){
        ObjectAnimator.ofFloat(img, "rotation", 90.0F, 0.0F)
                .setDuration(200)
                .start();
    }

}
