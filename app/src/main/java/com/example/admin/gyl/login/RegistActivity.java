package com.example.admin.gyl.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.gyl.base.BaseActivity;
import com.example.admin.gyl.http.MyCallBack;
import com.example.admin.gyl.http.model.BaseModel;
import com.example.admin.gyl.utils.SettingManager;
import com.example.admin.gyl.utils.Util;
import com.ylfcf.gyl.R;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by Administrator on 2018/2/6.
 */

public class RegistActivity extends BaseActivity {
    private final static int REQUEST_REGIST_WHAT = 1000;
    private final static int REQUEST_REGIST_SUC = 1001;

    private TextView topTitleTV;
    private LinearLayout topLeftLayout;
    private ImageView topLeftBtn;

    private EditText usernameET;
    private EditText passwordET;
    private EditText rePasswordET;
    private EditText phoneET;
    private Button registBtn;
    private CheckBox mCheckbox;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case REQUEST_REGIST_WHAT:
                    requestRegist(phone, Util.md5Encryption(password),username);
                    break;
                case REQUEST_REGIST_SUC:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registe_activity);
    }

    @Override
    public void initView() {
        topTitleTV = (TextView) findViewById(R.id.common_topbar_title);
        topLeftLayout = (LinearLayout) findViewById(R.id.common_topbar_left_layout);
        topLeftLayout.setOnClickListener(this);
        topLeftBtn = (ImageView) findViewById(R.id.common_topbar_left_btn);
        topLeftBtn.setImageResource(R.drawable.close_img);
        topTitleTV.setText("注册");

        usernameET = (EditText) findViewById(R.id.registe_activity_uname_et);
        passwordET = (EditText) findViewById(R.id.registe_activity_pwd_et);
        rePasswordET = (EditText) findViewById(R.id.registe_activity_repwd_et);
        phoneET = (EditText) findViewById(R.id.registe_activity_phone_et);
        registBtn = (Button) findViewById(R.id.regist_activity_btn);
        registBtn.setOnClickListener(this);
        mCheckbox = (CheckBox) findViewById(R.id.regist_activity_cb);
    }

    @Override
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.common_topbar_left_layout:
                finish();
                break;
            case R.id.regist_activity_btn:
                checkData();
                break;
        }
    }

    private String username,password,phone;
    private void checkData(){
        username = usernameET.getText().toString();
        password = passwordET.getText().toString();
        String rePassword = rePasswordET.getText().toString();
        phone = phoneET.getText().toString();
        if(!Util.isEmpty(username)){
            if(!Util.isEmpty(password)){
                if(password.equals(rePassword)){
                    if(Util.checkPhoneNumber(phone)){
                        handler.sendEmptyMessage(REQUEST_REGIST_WHAT);
                    }else{
                        Util.toastLong(RegistActivity.this,"请输入合法的手机号");
                    }
                }else{
                    Util.toastLong(RegistActivity.this,"两次输入的密码不一致");
                }
            }else{
                Util.toastLong(RegistActivity.this,"请输入密码");
            }
        }else{
            Util.toastLong(RegistActivity.this,"请输入账户名");
        }
    }

    /**
     * 注册
     * @param mobile
     * @param password
     * @param coName
     */
    private void requestRegist(String mobile,String password,String coName){
        if(mLoadingDialog != null){
            mLoadingDialog.show();
        }

        netHandler.RequestRegist(mobile, password, coName, new MyCallBack<BaseModel>() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(mLoadingDialog != null && mLoadingDialog.isShowing()){
                    mLoadingDialog.dismiss();
                }
                Util.toastShort(RegistActivity.this, "注册失败");
            }

            @Override
            public void onSuccess(Call call, BaseModel baseInfo) {
                if(mLoadingDialog != null && mLoadingDialog.isShowing()){
                    mLoadingDialog.dismiss();
                }
                if(baseInfo != null){
                    int resultCode = SettingManager.getResultCode(baseInfo);
                    if(resultCode == 0){
                        //注册成功
                        Util.toastLong(RegistActivity.this, "注册成功");
                        finish();
                    }else{
                        Util.toastLong(RegistActivity.this,baseInfo.getMsg());
                    }
                }
            }
        });

//        netHandler.xxxx(new MyCallBack<BaseModel>() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                System.out.println("xx");
//            }
//
//            @Override
//            public void onSuccess(Call call, BaseModel data) {
//                System.out.println("cc");
//            }
//        });


//        AsyncRegiste registeTask = new AsyncRegiste(RegistActivity.this, mobile, password, coName,
//                new Inter.OnCommonInter() {
//                    @Override
//                    public void back(BaseModel baseInfo) {
//                        if(mLoadingDialog != null && mLoadingDialog.isShowing()){
//                            mLoadingDialog.dismiss();
//                        }
//                        if(baseInfo != null){
//                            int resultCode = SettingManager.getResultCode(baseInfo);
//                            if(resultCode == 0){
//                                //注册成功
//                                Util.toastLong(RegistActivity.this, "注册成功");
//                                finish();
//                            }else{
//                                Util.toastLong(RegistActivity.this,baseInfo.getMsg());
//                            }
//                        }
//                    }
//                });
//        registeTask.executeAsyncTask(ThreadUtils.FULL_TASK_EXECUTOR);
    }
}
