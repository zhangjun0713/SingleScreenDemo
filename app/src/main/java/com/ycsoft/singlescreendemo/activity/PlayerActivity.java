package com.ycsoft.singlescreendemo.activity;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ycsoft.singlescreendemo.R;
import com.ycsoft.singlescreendemo.view.YCVideoView;

/**
 * Created by Jeremy on 2016/9/24.
 * 播放器界面
 */
public class PlayerActivity extends BaseActivity implements View.OnClickListener {
	private YCVideoView mYcVideoView;
	private String videoPath;
	private ImageView ivPlay;

	@Override
	public void initActivity() {
		setContentView(R.layout.activity_player);
		videoPath = getIntent().getStringExtra("video_path");
	}

	@Override
	public void initViews() {
		ivPlay = (ImageView) findViewById(R.id.iv_play_or_pause);
		mYcVideoView = (YCVideoView) findViewById(R.id.ycVideoView);
		mYcVideoView.setOnPreparedListener(mPreparedListener);
		mYcVideoView.setOnCompletionListener(mCompletionListener);
		mYcVideoView.setOnErrorListener(mOnErrorListener);
		mYcVideoView.setOnSeekCompleteListener(mOnSeekCompleteListener);
		mYcVideoView.setOnClickListener(this);
	}

	@Override
	public void initDatas() {
		next();
	}

	MediaPlayer.OnPreparedListener mPreparedListener = new MediaPlayer.OnPreparedListener() {
		@Override
		public void onPrepared(MediaPlayer mp) {
			mYcVideoView.start();
		}
	};

	MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
		public void onCompletion(MediaPlayer mp) {
			next();
		}

	};

	private void next() {
		mYcVideoView.nextVideo(videoPath, false);
	}

	private static final String TAG = "PlayerActivity";
	MediaPlayer.OnErrorListener mOnErrorListener = new MediaPlayer.OnErrorListener() {
		public boolean onError(MediaPlayer mp, int what, int extra) {
			Log.e(TAG, "onError: " + extra);
			return true;
		}
	};
	MediaPlayer.OnSeekCompleteListener mOnSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() {
		public void onSeekComplete(MediaPlayer mp) {
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mYcVideoView.destroyPlayer();
	}

	@Override
	public void onClick(View view) {
		if (mYcVideoView.isPlaying()) {
			mYcVideoView.pause();
			ivPlay.setVisibility(View.VISIBLE);
		} else {
			mYcVideoView.start();
			ivPlay.setVisibility(View.GONE);
		}
	}
}
