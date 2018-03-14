package com.example.admin.gyl.appconfig;


import com.ylfcf.gyl.BuildConfig;

/**
 * Created by yjx on 2018/3/13.
 */

public class UrlConfig {

    public static String getBaseUrl() {
//        return BuildConfig.DEBUG ? DEFAULT_TEST_SERVER : DEFAULT_SERVER;
        return BuildConfig.DEBUG ? DEFAULT_SERVER : DEFAULT_SERVER;
    }

    public static String getBaseMallUrl() {
        return BuildConfig.DEBUG ? RNG_MALL_URL_TEST : RNG_MALL_URL;
    }

    //Url拼接地址
    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";
    public static final String DEFAULT_SERVER = HTTP + "www.gylapi.com";
    public static final String DEFAULT_TEST_SERVER = HTTP + "test.royalgroups.cn";
    public static final String RNG_MALL_URL_TEST = "http://testshop.royalgroups.cn/Mobile/Index/index/uid/";
    public static final String RNG_MALL_URL="http://shop.royalgroups.cn/Mobile/Index/index/uid/";
    public static final String RNG_IMG_URL= HTTP + "img.royalgroups.cn/";


}
