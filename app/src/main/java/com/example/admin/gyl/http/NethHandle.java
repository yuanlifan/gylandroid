package com.example.admin.gyl.http;


import com.example.admin.gyl.appconfig.UrlConfig;
import com.example.admin.gyl.http.model.BaseModel;
import com.example.admin.gyl.http.model.LoginModel;

/**
 * @author guozhangyu  create by 2017/8/22 19:32
 */
public class NethHandle {
    //采取单例模式
    private static NethHandle nethHandle = new NethHandle();

    public static NethHandle getNethHandle() {
        if (nethHandle == null) {
            nethHandle = new NethHandle();
        }
        return nethHandle;
    }

//    private String getPhone() {
//        return "手机型号: " + Build.MODEL + "\n手机API版本信息："
//                + Build.VERSION.SDK_INT + "\n手机具体系统版本"
//                + Build.VERSION.RELEASE + "\n手机厂商：" + android.os.Build.MANUFACTURER;
//    }

    //请求登录
    public void RequestLogin(String mobile, String password, MyCallBack<LoginModel> callBack) {
        HttpManager.getHttpManager().addParam("mobile", mobile).addParam("password", password)
                .post(UrlConfig.getBaseUrl() + "/co_user/info/login", callBack);
    }

    //注册
    public void RequestRegist(String mobile, String password, String coName, MyCallBack<BaseModel> callBack) {
        HttpManager.getHttpManager().addParam("mobile", mobile).addParam("password", password)
                .addParam("co_name", coName).post(UrlConfig.getBaseUrl() + "/co_user/info/register", callBack);
    }

    public void removeAllMessage() {
        HttpManager.getHttpManager().removeAllMessage();
    }

}
