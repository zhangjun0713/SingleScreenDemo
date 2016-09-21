package com.ycsoft.singlescreendemo.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Environment;

import com.ycsoft.singlescreendemo.R;
import com.ycsoft.singlescreendemo.common.Constants;
import com.ycsoft.singlescreendemo.util.AssetUtil;
import com.ycsoft.singlescreendemo.util.ToolUtil;

import java.io.File;
import java.io.IOException;

public class StartActivity extends BaseActivity {

	private static final String PATH_THIRD_APK = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";

	@Override
	public void initActivity() {
		setContentView(R.layout.activity_start);
	}

	@Override
	public void initViews() {
		mAsyncTask.execute();
	}

	private AsyncTask<String, Integer, Boolean> mAsyncTask = new AsyncTask<String, Integer, Boolean>() {
		@Override
		protected Boolean doInBackground(String... strings) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			whetherCopyAndInstallThirdApk();
			return true;
		}

		@Override
		protected void onPostExecute(Boolean aBoolean) {
			Intent intent = new Intent(StartActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	};

	/**
	 * 是否需要复制和安装第三方软件
	 */
	private void whetherCopyAndInstallThirdApk() {
		boolean isCopySuccess = true;
		// 判断是否需要复制assets下面的第三方app到本地并安装
		AssetManager assetManager = getAssets();
		try {
			String[] thirdApks = assetManager.list("thirdApk");
			if (thirdApks.length > 0) {
				isCopySuccess = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!isCopySuccess) {
			if (!new File(PATH_THIRD_APK
					+ Constants.TV_FILE_NAME).exists()
					|| !new File(PATH_THIRD_APK
					+ Constants.CIBN_FILE_NAME).exists()) {
				AssetUtil.copyAssetPathToPath("thirdApk",
						PATH_THIRD_APK, this);
			}
			if (!ToolUtil.isInstalled(this, Constants.TV_PACKAGE)) {
				ToolUtil.slienceInstallApk(PATH_THIRD_APK
						+ Constants.TV_FILE_NAME);
			}
			if (!ToolUtil.isInstalled(this, Constants.CIBN_PACKAGE)) {
				ToolUtil.slienceInstallApk(PATH_THIRD_APK
						+ Constants.CIBN_FILE_NAME);
			}
		}
	}

	@Override
	public void initDatas() {
	}
}
