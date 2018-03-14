package com.example.admin.gyl;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import com.example.admin.gyl.home.MainFragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjx on 2018/3/13.
 */

public class MainApp extends MultiDexApplication {

    private List<Activity> activityList;
    private static MainApp theApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        theApplication = this;
        activityList = new ArrayList<Activity>();//haha
        init();
    }

    private void init(){

    }
    private static void initialize() {
        theApplication = new MainApp();
        theApplication.onCreate();
    }

    /**
     *
     * @return
     */
    public static MainApp getApplication() {
        if (theApplication == null)
            initialize();
        return theApplication;
    }

    public MainFragmentActivity getMainActivity() {
        MainFragmentActivity mainActivity = null;
        for (Activity activity : activityList) {
            if (activity instanceof MainFragmentActivity) {
                mainActivity = (MainFragmentActivity) activity;
                break;
            }
        }
        return mainActivity;
    }

    public static MainApp getContext() {
        return theApplication;
    }

    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    public void finishAllActivity() {
        for (Activity activity : activityList) {
            if (null != activity) {
                activity.finish();
            }
        }
    }

}
