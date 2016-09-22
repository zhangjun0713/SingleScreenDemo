package com.ycsoft.singlescreendemo.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ycsoft.singlescreendemo.common.SingleScreenApplication;

/**
 * Created by Jeremy on 2016/8/15.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static SingleScreenApplication mScreenApplication;

    /**
     * 初始化Activity
     */
    public abstract void initActivity();

    /**
     * 初始化界面控件
     */
    public abstract void initViews();

    /**
     * 初始化界面数据
     */
    public abstract void initDatas();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScreenApplication = (SingleScreenApplication) getApplication();
        mScreenApplication.addActivity(this);
        initActivity();
        initViews();
        initDatas();
    }

    @Override
    protected void onDestroy() {
        mScreenApplication.removeActivity(this);
        super.onDestroy();
    }
}
