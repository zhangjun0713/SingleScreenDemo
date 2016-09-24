package com.ycsoft.singlescreendemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ycsoft.singlescreendemo.R;
import com.ycsoft.singlescreendemo.entity.MvEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 2016/9/24.
 * MV适配器
 */
public class MvAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private List<MvEntity> mMvEntities = new ArrayList<>();

	public MvAdapter(Context mContext, List<MvEntity> mMvEntities) {
		this.mContext = mContext;
		this.mMvEntities = mMvEntities;
		mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mMvEntities.size();
	}

	@Override
	public Object getItem(int position) {
		return mMvEntities.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.item_mv_list, null);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_mv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvName.setText(mMvEntities.get(position).mvName);
		return convertView;
	}

	public void updateAdapter(List<MvEntity> mMvEntities) {
		this.mMvEntities = mMvEntities;
		notifyDataSetChanged();
	}

	class ViewHolder {
		TextView tvName;
	}
}
