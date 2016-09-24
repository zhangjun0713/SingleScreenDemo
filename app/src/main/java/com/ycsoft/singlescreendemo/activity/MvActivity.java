package com.ycsoft.singlescreendemo.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.ycsoft.singlescreendemo.R;
import com.ycsoft.singlescreendemo.adapter.MvAdapter;
import com.ycsoft.singlescreendemo.entity.MountEntity;
import com.ycsoft.singlescreendemo.entity.MvEntity;
import com.ycsoft.singlescreendemo.holder.DeviceInfoHolder;
import com.ycsoft.singlescreendemo.util.ToolUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 2016/9/24.
 * MV界面
 */
public class MvActivity extends BaseActivity implements AdapterView.OnItemClickListener {

	private static final int UPDATE_MV_LIST = 1;
	private static final int INIT_FINISHED = 2;
	private GridView gvMv;
	private MvAdapter mMvAdapter;
	private List<MvEntity> mMvEntities = new ArrayList<>();
	private List<MountEntity> mountEntities = new ArrayList<>();
	private DeviceInfoHolder deviceInfoHolder;
	private ScanTask mScanTask;
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case UPDATE_MV_LIST:
					if (mMvAdapter != null) {
						mMvAdapter.updateAdapter(mMvEntities);
					}
					break;
				case INIT_FINISHED:
					mountEntities = deviceInfoHolder.initMountList();
					if (mountEntities == null) {
						mMvEntities = new ArrayList<>();
					}
					if (mountEntities.size() > 0) {
						startScanTask();
					}
					break;
			}
		}
	};

	@Override
	public void initActivity() {
		setContentView(R.layout.activity_mv);
		deviceInfoHolder = new DeviceInfoHolder();
	}

	@Override
	public void initViews() {
		gvMv = (GridView) findViewById(R.id.gv_mv);
		mMvAdapter = new MvAdapter(this, mMvEntities);
		gvMv.setAdapter(mMvAdapter);
		gvMv.setOnItemClickListener(this);
	}

	@Override
	public void initDatas() {
		mHandler.sendEmptyMessageDelayed(INIT_FINISHED, 200);
	}

	private void startScanTask() {
		if (mScanTask != null) {
			mScanTask.cancel(true);
			mScanTask = null;
		}
		mScanTask = new ScanTask();
		mScanTask.execute();
	}

	private class ScanTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... strings) {
			for (MountEntity entity :
					mountEntities) {
				mMvEntities.addAll(ToolUtil.scanYCsoftVideo(entity.path));
			}
			return true;
		}

		@Override
		protected void onPostExecute(Boolean aBoolean) {
			if (aBoolean) {
				mHandler.sendEmptyMessage(UPDATE_MV_LIST);
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		//TODO 播放MV
//		Toast.makeText(MvActivity.this, "点击了《" + mMvEntities.get(position).mvName + "》", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, PlayerActivity.class);
		intent.putExtra("video_path", getSongPath(mMvEntities.get(position).path));
		startActivity(intent);
	}

	/**
	 * 获取播放路径，加密与不加密有区别
	 *
	 * @param path
	 * @return
	 */
	private String getSongPath(String path) {
		if (path.endsWith(".yc")) {
			return "ycfile:" + path;
		} else {
			return path;
		}
	}


	@Override
	protected void onDestroy() {
		mountEntities.clear();
		mountEntities = null;
		mMvEntities.clear();
		mMvEntities = null;
		super.onDestroy();
	}
}
