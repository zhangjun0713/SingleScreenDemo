package com.ycsoft.singlescreendemo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.AssetManager;

/**
 * @author HomgWu
 */
public class AssetUtil {
	/**
	 * 复制asset下指定目录中的所有文件（不复制子目录）到指定路径。
	 *
	 * @param assetPath
	 * @param targetPath
	 * @param context
	 * @return
	 */
	public static boolean copyAssetPathToPath(String assetPath,
	                                          String targetPath, Context context) {
		AssetManager assetManager = context.getAssets();
		boolean isSuccess = true;
		try {
			String[] files = assetManager.list(assetPath);
			File dir = new File(targetPath);
			if (!dir.exists()) {
				dir.mkdir();
			}
			InputStream mInput = null;
			OutputStream mOutput = null;
			if (files.length > 0) {
				// 复制路径下的所有文件
				for (String string : files) {
					mInput = assetManager.open(assetPath + "/" + string);
					String outFileName = targetPath + string;
					mOutput = new FileOutputStream(outFileName);
					byte[] buffer = new byte[1024 * 4];
					int length;
					while ((length = mInput.read(buffer)) > 0) {
						mOutput.write(buffer, 0, length);
					}
					mOutput.flush();
				}
				try {
					if (mInput != null) {
						mInput.close();
					}
					if (mOutput != null) {
						mOutput.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * 复制asset下的指定文件到指定的位置
	 *
	 * @param assetName
	 * @param targetPath
	 * @param targetFileName
	 * @param context
	 * @return
	 */
	public static boolean copyFileToPath(String assetName, String targetPath, String targetFileName,
	                                     Context context) {
		AssetManager assetManager = context.getAssets();
		boolean isSuccess = true;
		InputStream mInput = null;
		OutputStream mOutput = null;
		try {
			File dir = new File(targetPath);
			if (!dir.exists()) {
				dir.mkdir();
			}
			String outFileName = targetPath + targetFileName;
			mOutput = new FileOutputStream(outFileName);
			mInput = assetManager.open(assetName);
			byte[] buffer = new byte[1024 * 4];
			int length;
			while ((length = mInput.read(buffer)) > 0) {
				mOutput.write(buffer, 0, length);
			}
			mOutput.flush();
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
		} finally {
			try {
				if (mInput != null) {
					mInput.close();
				}
				if (mOutput != null) {
					mOutput.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isSuccess;
	}
}
