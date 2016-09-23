package com.ycsoft.singlescreendemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ycsoft.singlescreendemo.GoodsEntity;
import com.ycsoft.singlescreendemo.R;
import com.ycsoft.singlescreendemo.holder.ShoppingCartHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 2016/9/23.
 * 购物车适配器
 */
public class ShoppingCartAdapter extends BaseAdapter {
	private LayoutInflater mLayoutInflater;
	private List<GoodsEntity> mGoodsEntities = new ArrayList<>();

	public ShoppingCartAdapter(Context context, List<GoodsEntity> mGoodsEntities) {
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
			convertView = mLayoutInflater.inflate(R.layout.item_goods_list, null);
			viewHolder = new ViewHolder();
			viewHolder.ibtnDelete = (ImageButton) convertView.findViewById(R.id.ibtn_delete_item);
			viewHolder.tvGoodsName = (TextView) convertView.findViewById(R.id.tv_item_goods_name);
			viewHolder.tvGoodsCount = (TextView) convertView.findViewById(R.id.tv_item_count);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.ibtnDelete.setOnClickListener(new ClickElement(position));
		viewHolder.tvGoodsName.setText(mGoodsEntities.get(position).goodsName);
		viewHolder.tvGoodsCount.setText("x" + mGoodsEntities.get(position).goodsCount);
		return convertView;
	}

	class ClickElement implements View.OnClickListener {
		int position;

		public ClickElement(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			ShoppingCartHolder.getInstance().removeFromShoppingCart(mGoodsEntities.get(position));
			mGoodsEntities = ShoppingCartHolder.getInstance().getShoppingCartGoods();
			notifyDataSetChanged();
		}
	}

	class ViewHolder {
		TextView tvGoodsName, tvGoodsCount;
		ImageButton ibtnDelete;
	}
}
