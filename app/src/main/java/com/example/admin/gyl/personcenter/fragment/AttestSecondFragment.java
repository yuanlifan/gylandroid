package com.example.admin.gyl.personcenter.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.gyl.base.BaseFragment;
import com.example.admin.gyl.personcenter.AttestActivity;
import com.example.admin.gyl.personcenter.adapter.AttestPictureAdapter;
import com.ylfcf.gyl.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttestSecondFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private AttestPictureAdapter mAttestPictureAdapter;
    private List<String> photosListData = new ArrayList();

    @Override
    protected void initData(View view) {
        TextView topTitleTV = (TextView) view.findViewById(R.id.common_topbar_title);
        LinearLayout topLeftLayout = (LinearLayout) view.findViewById(R.id.common_topbar_left_layout);
        topLeftLayout.setOnClickListener(this);
        ImageView topLeftBtn = (ImageView) view.findViewById(R.id.common_topbar_left_btn);
        topLeftBtn.setImageResource(R.drawable.back_img);

        TextView btn_before = view.findViewById(R.id.btn_before);
        btn_before.setOnClickListener(this);
        TextView btn_push = view.findViewById(R.id.btn_push);
        btn_push.setOnClickListener(this);
        topTitleTV.setText("企业概况");
        mRecyclerView = view.findViewById(R.id.recycler_picture);
        initAdapter();

    }

    private void initAdapter() {
        photosListData.add("");
        photosListData.add("");
        photosListData.add("");
        photosListData.add("");
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL,false));
        if (mAttestPictureAdapter == null)
            mAttestPictureAdapter = new AttestPictureAdapter(R.layout.item_photo_images, photosListData);
        mAttestPictureAdapter.setEnableLoadMore(false);
        mRecyclerView.setAdapter(mAttestPictureAdapter);
        mAttestPictureAdapter.addHeaderView(initHeadView());
    }

    private View initHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_attest_headview, mRecyclerView, false);
        return view;
    }

    @Override
    public void onFragmentClick(View view) {
        switch (view.getId()){
            case R.id.common_topbar_left_layout:
                //上一步
                ((AttestActivity) mContext).changeFragment(0);
                break;

            case R.id.btn_before:
                ((AttestActivity) mContext).changeFragment(0);
                break;

            case R.id.btn_push:
                //TODO 提交申请

                break;
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_attest_second;
    }

}
