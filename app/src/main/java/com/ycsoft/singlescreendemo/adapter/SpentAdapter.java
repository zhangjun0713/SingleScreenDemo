package com.ycsoft.singlescreendemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ycsoft.singlescreendemo.R;
import com.ycsoft.singlescreendemo.entity.GoodsEntity;
import com.ycsoft.singlescreendemo.holder.ShoppingCartHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 2016/9/23.
 * 消费记录适配器
 */
public class SpentAdapter extends BaseAdapter {
	private LayoutInflater mLayoutInflater;
	private List<GoodsEntity> mGoodsEntities = new ArrayList<>();

	public SpentAdapter(Context context, List<GoodsEntity> mGoodsEntities) {
		if (mGoodsEntities != null)
			this.mGoodsEntities = mGoodsEntities;
		mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mGoodsEntities.size();
	}

	@Override
	public Object getItem(int position) {
		return mGoodsEntities.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.item_spent_goods_list, null);
			viewHolder = new ViewHolder();
			viewHolder.tvGoodsName = (TextView) convertView.findViewById(R.id.tv_spent_goods_name);
			viewHolder.tvGoodsCount = (TextView) convertView.findViewById(R.id.tv_spent_goods_count);
			viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_spent_goods_price);
			viewHolder.tvTotalPrice = (TextView) convertView.findViewById(R.id.tv_spent_goods_total_price);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tvGoodsName.setText(mGoodsEntities.get(position).goodsName);
		viewHolder.tvPrice.setText("￥" + mGoodsEntities.get(position).goodsPrice);
		viewHolder.tvGoodsCount.setText("x" + mGoodsEntities.get(position).goodsCount);
		int totalPrice = Integer.valueOf(mGoodsEntities.get(position).goodsPrice) *
				Integer.valueOf(mGoodsEntities.get(position).goodsCount);
		viewHolder.tvTotalPrice.setText("￥" + totalPrice);
		return convertView;
	}

	/**
	 * 更新适配器
	 *
	 * @param mGoodsEntities
	 */
	public void updateAdapter(List<GoodsEntity> mGoodsEntities) {
		if (mGoodsEntities != null) {
			this.mGoodsEntities = mGoodsEntities;
		}
		notifyDataSetChanged();
	}

	class ViewHolder {
		TextView tvGoodsName, tvPrice, tvGoodsCount, tvTotalPrice;
	}
}
