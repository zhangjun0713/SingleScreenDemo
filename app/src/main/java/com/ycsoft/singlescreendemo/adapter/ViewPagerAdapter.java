package com.ycsoft.singlescreendemo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 2016/9/18.
 * ViewPager的适配器
 */
public class ViewPagerAdapter extends PagerAdapter {
	/**
	 * 用来显示的页面集合
	 */
	private List<View> mAdapterViews;

	public ViewPagerAdapter(List<View> mAdapterViews) {
		if (mAdapterViews != null) {
			this.mAdapterViews = mAdapterViews;
		} else {
			this.mAdapterViews = new ArrayList<>();
		}
	}

	@Override
	public int getCount() {
		return mAdapterViews.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(mAdapterViews.get(position));
		return mAdapterViews.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mAdapterViews.get(position));
	}
}
