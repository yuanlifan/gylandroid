package com.example.admin.gyl.utils;

import android.content.Context;

import com.example.admin.gyl.http.model.BaseModel;

/**
 * Created by Administrator on 2018/2/6.
 */

public class SettingManager extends DefaultPreferences {
    public static final String USER_ID 	= "userid";

    /**
     *
     * @param context
     * @param userId
     */
    public static void setUserId(Context context, String userId){
        DefaultPreferences.setString(context,USER_ID, userId);
//			try {
//				String encrypt	= SimpleCrypto.encrypt(masterSeed, userId);
//				DefaultPreferences.setString(context,USER_ID, encrypt);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
    }

    public static String getUserId(Context context){
        String userId = DefaultPreferences.getString(context,USER_ID, "");
//		try {
//			if (!userId.isEmpty())
//				userId = SimpleCrypto.decrypt(masterSeed, userId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        return userId;
    }

    public static boolean isLogin(Context context){
        String userId = getUserId(context);
        if(userId != null && !"".equals(userId)){
            return true;
        }
        return false;
    }

    /**
     * 获取请求状态码
     * @param baseInfo
     * @return
     */
    public static int getResultCode(BaseModel baseInfo){
        int resultCode = -1;
        try {
            resultCode = Integer.parseInt(baseInfo.getError_id());
        } catch (Exception e) {
        }
        return resultCode;
    }

    /**
     * 获取请求返回信息，仅限于msg为数字的时候
     * @param baseInfo
     * @return
     */
    public static int getResultMsg(BaseModel baseInfo){
        int resultMsg = -1;
        try {
            resultMsg = Integer.parseInt(baseInfo.getMsg());
        } catch (Exception e) {
        }
        return resultMsg;
    }
}
