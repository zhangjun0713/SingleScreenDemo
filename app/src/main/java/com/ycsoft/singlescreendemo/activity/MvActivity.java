package com.ycsoft.singlescreendemo.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ycsoft.singlescreendemo.R;
import com.ycsoft.singlescreendemo.activity.fragment.MvFragment;

/**
 * Created by Jeremy on 2016/9/20.
 * MV页面
 */
public class MvActivity extends BaseActivity {
	public static final String TAG = "MvActivity";
	private static String mPageName;
	private MvFragment mMvFragment;
	private FrameLayout flContent;
	private TextView tvPageName;

	@Override
	public void initActivity() {
		setContentView(R.layout.activity_mv);
	}

	@Override
	public void initViews() {
		flContent = (FrameLayout) findViewById(R.id.fl_content);
		tvPageName = (TextView) findViewById(R.id.tv_page_name);
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
		mMvFragment = new MvFragment();
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
					tvPageName.setText(mPageName);
					break;
			}
		}
	};
}
