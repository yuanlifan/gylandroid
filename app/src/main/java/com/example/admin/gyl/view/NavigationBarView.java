package com.example.admin.gyl.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.admin.gyl.utils.SettingManager;
import com.ylfcf.gyl.R;


/**
 * 导航按钮
 * Created by Administrator on 2018/2/1.
 */

public class NavigationBarView extends LinearLayout implements View.OnClickListener {
    private Context context;
    public NavigationBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init(context);
    }

    public NavigationBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context);
    }

    public NavigationBarView(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    private void init(Context context){
        this.context = context;
        setOrientation(LinearLayout.HORIZONTAL);
        setBackgroundColor(Color.parseColor("#ffffff"));
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.navigation_bar, this);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        one = (BottomSingleView) findViewById(R.id.nav_one);
        two = (BottomSingleView) findViewById(R.id.nav_two);


        one.setOnClickListener(this);
        two.setOnClickListener(this);
    }

    private ViewPager mViewPager ;
    public void setViewPager(ViewPager mViewPager,NaviBtnOnClickListener naviListener){
        this.mViewPager = mViewPager ;
        this.mViewPager.setOnPageChangeListener(listener);
        this.mNaviBtnOnClickListener = naviListener;
    }


    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener(){
        @Override
        public void onPageScrollStateChanged(int arg0) {
            if (arg0 == 0 ) {
                isClick = false ;
            }
        }
        @Override
        public void onPageScrolled(int position, float positionOffset, int arg2) {
            if (!isClick) {
                setChildProgress(position, 1 - positionOffset);
                setChildProgress(position + 1, positionOffset);
            }
        }
        @Override
        public void onPageSelected(int arg0) {
            currentID = arg0  ;
        }
    };

    private  void setChildProgress(int position , float progress){
        switch (position) {
            case 0 :
                one.setProgress(progress);
                break;
            case 1:
                two.setProgress(progress);
                break;
        }
    }

    public void setViewPagerCurrentPosition(int position){
        isClick = true ;
        restoreState(currentID);
        currentID = position;
        if(position == 0){
            one.setProgress(1);
        }else if(position == 1){
            two.setProgress(1);
        }
        mViewPager.setCurrentItem(position,false);
    }

    private BottomSingleView one;
    private BottomSingleView two;

    private int currentID = 0 ;


    private boolean isClick = false ;
    private NaviBtnOnClickListener mNaviBtnOnClickListener;
    @Override
    public void onClick(View v) {
        isClick = true ;
        restoreState(currentID);
        switch (v.getId()) {
            case R.id.nav_one:
                one.setProgress(1);
                currentID = 0 ;
                mViewPager.setCurrentItem(0,false);//切换时没有滑动效果
                break;
            case R.id.nav_two:
                boolean isLogin = SettingManager.isLogin(context);
                if(isLogin){
                    two.setProgress(1);
                    currentID = 1 ;
                    mViewPager.setCurrentItem(1, false);
                }else{
                    if(mNaviBtnOnClickListener != null){
                        mNaviBtnOnClickListener.back(two);
                    }
                }
                break;
        }
    }

    private void restoreState(int lastPosition){
        switch (lastPosition) {
            case 0:
                one.setProgress(0);
                break;
            case 1:
                two.setProgress(0);
                break;
        }
    }

    /**
     * 首页导航按钮 “我的”监听
     */
    public interface NaviBtnOnClickListener{
        void back(View v);
    }
}
