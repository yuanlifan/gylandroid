package com.example.admin.gyl.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.admin.gyl.view.LoadingDialog;


/**
 *
 * Created by Administrator on 2018/2/5.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    protected Context mContext;
    protected LoadingDialog mLoadingDialog;
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        mLoadingDialog = new LoadingDialog(mContext,"正在加载...");
    }

    @Override
    public void onClick(View v) {
        onFragmentClick(v);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public abstract void onFragmentClick(View v);
}
