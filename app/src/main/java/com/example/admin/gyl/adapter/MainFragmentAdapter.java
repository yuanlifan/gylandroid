package com.example.admin.gyl.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.gyl.home.fragment.FirstpageFragment;
import com.example.admin.gyl.home.fragment.UserFragment;


/**
 * Created by Administrator on 2018/2/5.
 */

public class MainFragmentAdapter extends FragmentStatePagerAdapter{
    public static final int FIRSTPAGE_FRAGMENT_FRAG = 0;//首页
    public static final int USER_FRAGMENT_FRAG = 1;//用户

    public boolean isTrue = true;

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Object instantiateItem(ViewGroup arg0, int arg1) {
        return super.instantiateItem(arg0, arg1);
    }

    @Override
    public Fragment getItem(int arg0) {

        if(!isTrue) {
            return null;
        }
        Fragment fragment = null;
        int index = arg0 % 2;//可以无限循环
        switch(index) {
            case FIRSTPAGE_FRAGMENT_FRAG:
                fragment = FirstpageFragment.newInstance(index);
                break;
            case USER_FRAGMENT_FRAG:
                fragment = UserFragment.newInstance(index);
                break;
        }
        return fragment;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public int getItemPosition(Object object) {
        if(!isTrue) {
            return PagerAdapter.POSITION_NONE;
        }
        return PagerAdapter.POSITION_UNCHANGED;
    }

    @Override
    public int getCount() {
        if(!isTrue) {
            return 0;
        }
        return 2;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
    }
}
