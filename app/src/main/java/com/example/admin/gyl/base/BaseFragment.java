package com.example.admin.gyl.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.gyl.http.NethHandle;
import com.example.admin.gyl.view.LoadingDialog;


/**
 *
 * Created by Administrator on 2018/2/5.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    protected Context mContext;
    protected LoadingDialog mLoadingDialog;
    protected NethHandle netHandler = NethHandle.getNethHandle();

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

    public abstract void onFragmentClick(View v);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutResId(), container, false);//加载Fragment布局文件
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(view);
    }

    //todo 页面数据的初始化
    protected abstract void initData(View view);

    public void onDestroy() {
        netHandler.removeAllMessage();
        super.onDestroy();
    }

    /**
     * 获取Fragment布局文件的R.layout int
     */
    protected abstract int getLayoutResId();


}
