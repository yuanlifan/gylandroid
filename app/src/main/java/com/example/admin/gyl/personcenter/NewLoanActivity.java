package com.example.admin.gyl.personcenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.admin.gyl.base.BaseActivity;
import com.example.admin.gyl.base.BaseFragment;
import com.example.admin.gyl.personcenter.fragment.NewLoan1Fragment;
import com.example.admin.gyl.personcenter.fragment.NewLoan2Fragment;
import com.example.admin.gyl.personcenter.fragment.NewLoan3Fragment;
import com.ylfcf.gyl.R;

import java.util.ArrayList;

/**
 * 新建贷款申请
 *
 */

public class NewLoanActivity extends BaseActivity {

    private int                     currentPosition = 0;
    private ArrayList<BaseFragment> fragments = new ArrayList<>();
    private BaseFragment mNewLoan1Fragment;
    private BaseFragment mNewLoan2Fragment;
    private BaseFragment mNewLoan3Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_loan);
    }

    @Override
    public void initView() {
        initFragments();
        changeFragment(0);
    }

    /**
     * 初始化集合
     */
    private void initFragments() {
        fragments.add(mNewLoan1Fragment);
        fragments.add(mNewLoan2Fragment);
        fragments.add(mNewLoan3Fragment);
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
            mNewLoan1Fragment = new NewLoan1Fragment();
            fragment = mNewLoan1Fragment;
        } else if (position == 1) {
            mNewLoan2Fragment = new NewLoan2Fragment();
            fragment = mNewLoan2Fragment;
        } else if (position == 2) {
            mNewLoan3Fragment = new NewLoan3Fragment();
            fragment = mNewLoan3Fragment;
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
    public void onViewClick(View v) {

    }


}
