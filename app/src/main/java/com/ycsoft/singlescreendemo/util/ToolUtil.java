package com.ycsoft.singlescreendemo.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;

import com.ycsoft.singlescreendemo.entity.MvEntity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jeremy on 2016/8/18.
 */
public class ToolUtil {
	/**
	 * 打开第三方软件
	 *
	 * @param packageName
	 * @param context
	 */
	public static void openThirdApp(String packageName, Context context) {
		PackageManager packageManager = context.getPackageManager();
		PackageInfo packageInfo = null;
		try {
			packageInfo = packageManager.getPackageInfo(packageName, 0);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return;
		}
		if (packageInfo != null) {
			Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
			resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
			resolveIntent.setPackage(packageInfo.packageName);
			List<ResolveInfo> apps = packageManager.queryIntentActivities(
					resolveIntent, 0);
			ResolveInfo resolveInfo = apps.iterator().next();
			if (resolveInfo != null) {
				String className = resolveInfo.activityInfo.name;
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_LAUNCHER);
				ComponentName cn = new ComponentName(packageName, className);
				intent.setComponent(cn);
				context.startActivity(intent);
			}
		}
	}

	/**
	 * 判断应用是否已安装
	 *
	 * @param context
	 * @param packageName
	 * @return
	 */
	public static boolean isInstalled(Context context, String packageName) {
		boolean hasInstalled = false;
		PackageManager pm = context.getPackageManager();
		List<PackageInfo> list = pm
				.getInstalledPackages(PackageManager.PERMISSION_GRANTED);
		for (PackageInfo p : list) {
			if (packageName != null && packageName.equals(p.packageName)) {
				hasInstalled = true;
				break;
			}
		}
		return hasInstalled;
	}

	/**
	 * 静默安装
	 *
	 * @param apkAbsolutePath
	 * @return
	 */
	public static boolean slienceInstallApk(String apkAbsolutePath) {
		String[] args = {"pm", "install", "-r", apkAbsolutePath};
		String result = "";
		ProcessBuilder processBuilder = new ProcessBuilder(args);
		Process process = null;
		InputStream errIs = null;
		InputStream inIs = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int read = -1;
			process = processBuilder.start();
			errIs = process.getErrorStream();
			while ((read = errIs.read()) != -1) {
				baos.write(read);
			}
			baos.write("/n".getBytes());
			inIs = process.getInputStream();
			while ((read = inIs.read()) != -1) {
				baos.write(read);
			}
			byte[] data = baos.toByteArray();
			result = new String(data);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (errIs != null) {
					errIs.close();
				}
				if (inIs != null) {
					inIs.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (process != null) {
				process.destroy();
			}
		}
		if (TextUtils.isEmpty(result)) {
			return false;
		} else {
			return result.lastIndexOf("Success") > 0;
		}
	}

	/**
	 * 扫描所有歌曲歌曲文件(.mpg与.yc,.mkv,mp4，包含子文件夹中的)
	 */
	public static List<MvEntity> scanYCsoftVideo(String path) {
		final List<MvEntity> dataEntities = new ArrayList<>();
		File root = new File(path);
		File[] roots = root.listFiles();
		if (roots != null) {
			for (File file : roots) {
				if (file.isDirectory()) {
					dataEntities.addAll(scanYCsoftVideo(file.getAbsolutePath()));
				} else {
					String filename = file.getName();
					if (filename.toLowerCase(Locale.CHINESE).endsWith(".mpg")
							|| filename.toLowerCase(Locale.CHINESE).endsWith(".yc")
							|| filename.toLowerCase(Locale.CHINESE).endsWith(".mkv")
							|| filename.toLowerCase(Locale.CHINESE).endsWith(".mp4")) {
						MvEntity mvEntity = new MvEntity();
						String clearName = filename.substring(0,
								filename.lastIndexOf('.'));
						mvEntity.mvName = clearName;
						mvEntity.path = file.getAbsolutePath();
						//只取100首歌曲
						if (dataEntities.size() < 100)
							dataEntities.add(mvEntity);
						else
							break;
					}
				}
			}
		}
		return dataEntities;
	}
}
