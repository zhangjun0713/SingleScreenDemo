package com.ycsoft.singlescreendemo.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
}
