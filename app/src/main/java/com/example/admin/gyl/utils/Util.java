package com.example.admin.gyl.utils;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/2/7.
 */

public class Util {
    private static Toast mToast = null;

    private static Handler handler = new Handler();

    private static Runnable run = new Runnable() {
        public void run() {
            if (mToast != null) {
                mToast.cancel();
            }
        }
    };

    /**
     * 显示长时间提示Toast
     *
     * @param context
     * @param text
     */
    public static void toastLong(Context context, String text) {
        showToast(context, text, Toast.LENGTH_LONG);
    }

    /**
     * 显示短时间提示Toast
     *
     * @param context
     * @param text
     */
    public static void toastShort(Context context, String text) {
        showToast(context, text, Toast.LENGTH_SHORT);
    }

    private static void showToast(Context context, String msg, int duration) {
        // 空值判断
        if (context == null) {
            return;
        }
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        handler.removeCallbacks(run);
        int delayedTime = 0;
        switch (duration) {
            case Toast.LENGTH_SHORT:// Toast.LENGTH_SHORT值为0，对应的持续时间大概为1s
                delayedTime = 1000;
                break;
            case Toast.LENGTH_LONG:// Toast.LENGTH_LONG值为1，对应的持续时间大概为3s
                delayedTime = 3000;
                break;

            default:
                break;
        }
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, duration);
//			mToast = new Toast(context);
        }else{
            mToast.setText(msg);
            mToast.setDuration(duration);
        }
        handler.postDelayed(run, delayedTime);
        mToast.show();
    }

    public static boolean isEmpty(String s){
        if(s == null || "".equals(s)){
            return true;
        }
        return false;
    }

    /**
     * 检查手机号码是否合法
     * @param phoneNo
     * @return
     */
    public static boolean checkPhoneNumber(String phoneNo) {
        if (phoneNo == null || phoneNo.length() == 0)
            return false;
        String regex	= "^[1][0-9]{10}$";
        Pattern pattern	= Pattern.compile(regex);
        Matcher matcher	= pattern.matcher(phoneNo);
        return matcher.find();
    }

    /**
     *md5摘要
     * @param plainText
     *            明文
     * @return 32位密文
     */
    public static String md5Encryption(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }
}
