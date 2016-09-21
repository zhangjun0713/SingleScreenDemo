package com.ycsoft.singlescreendemo.common;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth 俊
 * Created by Jeremy on 2016/8/18.
 */
public class SingleScreenApplication extends Application {
    private List<Activity> activities = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 将activiy添加到集合中统一管理
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 结束掉一个Activity
     *
     * @param baseActivity
     */
    public void removeActivity(Activity baseActivity) {
        activities.remove(baseActivity);
    }

    /**
     * 退出程序
     */
    public void exitApp() {
        //退出程序前先结束掉所有Activity
        while (activities.size() > 0) {
            activities.remove(0).finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
