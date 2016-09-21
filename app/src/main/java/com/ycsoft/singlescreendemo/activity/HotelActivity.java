package com.ycsoft.singlescreendemo.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.FrameLayout;

import com.ycsoft.singlescreendemo.R;
import com.ycsoft.singlescreendemo.activity.fragment.HotelFragment;

/**
 * Created by Jeremy on 2016/9/20.
 * MV页面
 */
public class HotelActivity extends BaseActivity {
    public static final String TAG = "HotelActivity";
    private String mPageName;
    private HotelFragment mMvFragment;
    private FrameLayout flContent;

    @Override
    public void initActivity() {
        setContentView(R.layout.activity_hotel);
    }

    @Override
    public void initViews() {
        flContent = (FrameLayout) findViewById(R.id.fl_content);
        mHandler.sendEmptyMessageDelayed(INIT_FINISHED, 500);
    }

    @Override
    public void initDatas() {
        Intent intent = getIntent();
        mPageName = intent.getStringExtra(TAG);
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mMvFragment = new HotelFragment();
        Bundle bundle = new Bundle();
        bundle.putString(HotelFragment.TAG, mPageName);
        mMvFragment.setArguments(bundle);
        transaction.replace(flContent.getId(), mMvFragment);
        transaction.commit();
    }

    private static final int INIT_FINISHED = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case INIT_FINISHED:
                    setDefaultFragment();
                    break;
            }
        }
    };
}
