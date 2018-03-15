package com.example.admin.gyl.personcenter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.admin.gyl.base.BaseActivity;
import com.example.admin.gyl.base.BaseFragment;
import com.example.admin.gyl.personcenter.fragment.AttestFirstFragment;
import com.example.admin.gyl.personcenter.fragment.AttestSecondFragment;
import com.ylfcf.gyl.R;

import java.util.ArrayList;

public class AttestActivity extends BaseActivity {

    private int                     currentPosition = 0;
    private ArrayList<BaseFragment> fragments       = new ArrayList<>();
    private BaseFragment mAttestFirstFragment;
    private BaseFragment mAttestSecondFragment;
    public int currentPosition1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attest);
    }

    @Override
    public void initView() {
        initFragments();
        changeFragment(0);
    }

    @Override
    public void onViewClick(View v) {

    }

    /**
     * 初始化集合
     */
    private void initFragments() {
        fragments.add(mAttestFirstFragment);
        fragments.add(mAttestSecondFragment);
    }

    /**
     * 切换Fragment
     */
    public void changeFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        hideFragment(transaction);
        if (position >= fragments.size() || fragments.get(position) == null) {
            BaseFragment fragment = getFragment(position);
            fragments.set(position, fragment);
            transaction.add(R.id.vp_main, fragment).commitAllowingStateLoss();
        } else {
            Fragment fragment = fragments.get(position);
            transaction.show(fragment).commitAllowingStateLoss();
            currentPosition = position;
        }
    }

    /**
     * 根据点击获取显示的Fragment
     */
    private BaseFragment getFragment(int position) {
        BaseFragment fragment = null;
        if (position == 0) {
            mAttestFirstFragment = new AttestFirstFragment();
            fragment = mAttestFirstFragment;
        } else if (position == 1) {
            mAttestSecondFragment = new AttestSecondFragment();
            fragment = mAttestSecondFragment;
        }
        return fragment;
    }

    /**
     * 隐藏Fragment
     */
    private void hideFragment(FragmentTransaction transaction) {
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                transaction.hide(fragment);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
//            showImage(imagePath);
            if(getFragment(1) != null) {
                ((AttestSecondFragment) fragments.get(1)).showImage(imagePath, currentPosition1);
            }
            c.close();
        }else if(requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            if (data.getData() != null || data.getExtras() != null) { // 防止没有返回结果
                Uri uri = data.getData();
            if(getFragment(1) != null) {
                ((AttestSecondFragment) fragments.get(1)).showImage(String.valueOf(uri), currentPosition1);
            }
        }

    }

}
}
