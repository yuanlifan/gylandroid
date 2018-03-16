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

    //请求注册
    public void RequestRegist(String mobile, String password, String coName, MyCallBack<BaseModel> callBack) {
        HttpManager.getHttpManager().addParam("mobile", mobile).addParam("password", password)
                .addParam("co_name", coName).post(UrlConfig.getBaseUrl() + "/co_user/info/register", callBack);
    }

    //提交企业注册信息  jgxy_code可选
    public void postFormbody(String id, String co_name, String legal_person, String yyzz_code, String jgxy_code,
                             String khxk_code, String bank_name, String bank_card, String yyzz_img,
                             String idcard_img_front, String idcard_img_back, String khxk_img,
                             String yyzz_img_file, String idcard_img_front_file,
                             String idcard_img_back_file, String khxk_img_file,
                             MyCallBack<BaseModel> callBack) {

        HttpManager.getHttpManager().addParam("id", id).addParam("co_name", co_name)
                .addParam("legal_person",legal_person).addParam("yyzz_code", yyzz_code)
                .addParam("jgxy_code", jgxy_code).addParam("khxk_code", khxk_code)
                .addParam("bank_name", bank_name).addParam("bank_card", bank_card)
                .addParam("yyzz_img", yyzz_img).addParam("idcard_img_front", idcard_img_front)
                .addParam("idcard_img_back", idcard_img_back).addParam("khxk_img", khxk_img)
                .addParam("yyzz_img_file", yyzz_img_file).addParam("idcard_img_front_file", idcard_img_front_file)
                .addParam("idcard_img_back_file", idcard_img_back_file).addParam("khxk_img_file", khxk_img_file)
                .post(UrlConfig.getBaseUrl() + "/co_user/info/updateCompUser", callBack);
    }

    public void removeAllMessage() {
        HttpManager.getHttpManager().removeAllMessage();
    }

}
