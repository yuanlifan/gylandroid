package com.example.admin.gyl.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.gyl.base.BaseFragment;
import com.ylfcf.gyl.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */

public class FirstpageFragment extends BaseFragment implements OnBannerListener {
    private FragmentManager fragmentManager = null;
    private View rootView;
    private Banner mBanner;

    public static Fragment newInstance(int position) {
        FirstpageFragment f = new FirstpageFragment();
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
            rootView = inflater.inflate(R.layout.first_page_fragment, null);
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

    private void findViews(View view, LayoutInflater inflater) {
        mBanner = (Banner) view.findViewById(R.id.first_page_fragment_banner);
        mBanner.setOnBannerListener(this);
    }

    private void initBanner(){
        List<String> images = new ArrayList<String>();
        List<String> titles = new ArrayList<String>();
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
//        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Accordion);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        if(mBanner != null)
            mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        if(mBanner != null)
            mBanner.stopAutoPlay();
    }

    @Override
    public void onFragmentClick(View v) {

    }

    @Override
    public void OnBannerClick(int position) {

    }
}
