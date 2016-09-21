package com.ycsoft.singlescreendemo.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ycsoft.singlescreendemo.R;
import com.ycsoft.singlescreendemo.activity.fragment.MainFragment;
import com.ycsoft.singlescreendemo.util.net.WifiApAdmin;

/**
 * @author 俊
 *         Created by 俊 on 2016/8/15.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
	private FrameLayout flContent;
	private MainFragment mMainFragment;
	private Button btnOnOrOffAp;
	private boolean isWifiApOn;

	private TextView tvWifiApRemind;

	@Override
	public void initActivity() {
		setContentView(R.layout.activity_main);
	}

	@Override
	public void initViews() {
		btnOnOrOffAp = (Button) findViewById(R.id.btn_open_or_close_ap);
		btnOnOrOffAp.setOnClickListener(this);
		tvWifiApRemind = (TextView) findViewById(R.id.tv_ap_name);
		flContent = (FrameLayout) findViewById(R.id.fl_content);
		mHandler.sendEmptyMessageDelayed(INIT_FINISHED, 500);
	}

	private void setDefaultFragment() {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		mMainFragment = new MainFragment();
		transaction.replace(flContent.getId(), mMainFragment);
		transaction.commit();
	}

	@Override
	public void initDatas() {

	}

	@Override
	public void onClick(View v) {
		if (v == btnOnOrOffAp) {
			if (!isWifiApOn) {
				WifiApAdmin admin = new WifiApAdmin(this);
				admin.startWifiAp("H5-S2", "88888888");
				isWifiApOn = true;
				btnOnOrOffAp.setText("关闭WIFI");
				tvWifiApRemind.setVisibility(View.VISIBLE);
			} else {
				WifiApAdmin.closeWifiAp(this);
				isWifiApOn = false;
				btnOnOrOffAp.setText("开启WIFI");
				tvWifiApRemind.setVisibility(View.GONE);
			}
		}
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

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		switch (event.getAction()) {
			case KeyEvent.ACTION_DOWN:
				if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN)
					break;
		}
		return super.dispatchKeyEvent(event);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mScreenApplication.exitApp();
	}
}
