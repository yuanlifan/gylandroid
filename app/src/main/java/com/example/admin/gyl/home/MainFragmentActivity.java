package com.example.admin.gyl.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.example.admin.gyl.adapter.MainFragmentAdapter;
import com.example.admin.gyl.base.BaseActivity;
import com.example.admin.gyl.login.LoginActivity;
import com.example.admin.gyl.view.NavigationBarView;
import com.example.admin.gyl.view.NoScrollViewPager;
import com.ylfcf.gyl.R;


/**
 *
 * Created by Administrator on 2018/2/1.
 */

public class MainFragmentActivity extends BaseActivity {
    public static final int REQUESTCODE_LOGIN = 100;
    public static final int RESULTCODE_LOGIN = 101;
    private NoScrollViewPager viewPager;
    private NavigationBarView navBarView;

    private MainFragmentAdapter fragmentAdapter = null;
    public FragmentManager fragmentManager = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULTCODE_LOGIN){
            //登录成功
            viewPager.setCurrentItem(1,false);
            navBarView.setViewPagerCurrentPosition(1);
        }
    }

    @Override
    public void findViews() {
        viewPager = (NoScrollViewPager)findViewById(R.id.main_activity_viewpager);
        navBarView = (NavigationBarView)findViewById(R.id.main_navigation_bar);
        viewPager.setOffscreenPageLimit(1);//首页的2个fragment数据都保留
        viewPager.setScanScroll(false);//禁止滑动
        navBarView.setViewPager(viewPager, new NavigationBarView.NaviBtnOnClickListener() {
            @Override
            public void back(View v) {
                //未登录，
                Intent intent = new Intent(MainFragmentActivity.this,LoginActivity.class);
                startActivityForResult(intent,REQUESTCODE_LOGIN);
            }
        });
        initAdapter();
    }

    private void initAdapter(){
        fragmentManager = getSupportFragmentManager();
        fragmentAdapter = new MainFragmentAdapter(fragmentManager);
        viewPager.setAdapter(fragmentAdapter);
    }

    @Override
    public void onViewClick(View v) {

    }
}
