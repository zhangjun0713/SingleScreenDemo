package com.ycsoft.singlescreendemo.activity;

import android.widget.ListView;
import android.widget.TextView;

import com.ycsoft.singlescreendemo.R;
import com.ycsoft.singlescreendemo.adapter.SpentAdapter;
import com.ycsoft.singlescreendemo.entity.GoodsEntity;
import com.ycsoft.singlescreendemo.holder.SpentHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 2016/9/23.
 * 消费记录界面
 */
public class SpentActivity extends BaseActivity {

	private ListView lvSpent;
	private SpentAdapter mSpentAdapter;
	private List<GoodsEntity> mGoodsEntities = new ArrayList<>();
	private SpentHolder mSpentHolder;
	private TextView tvTotalSpent;
	private int mTotalSpent;

	@Override
	public void initActivity() {
		setContentView(R.layout.activity_spent);
		mSpentHolder = SpentHolder.getInstance();
	}

	@Override
	public void initViews() {
		tvTotalSpent = (TextView) findViewById(R.id.tv_total_spent);
		lvSpent = (ListView) findViewById(R.id.lv_goods_spent);
		lvSpent.setItemsCanFocus(true);
		mSpentAdapter = new SpentAdapter(this, mGoodsEntities);
		lvSpent.setAdapter(mSpentAdapter);
	}

	@Override
	public void initDatas() {
		mGoodsEntities = mSpentHolder.getSpentGoods();
		mSpentAdapter.updateAdapter(mGoodsEntities);
		for (GoodsEntity en :
				mGoodsEntities) {
			mTotalSpent += Integer.valueOf(en.goodsPrice) * Integer.valueOf(en.goodsCount);
		}
		tvTotalSpent.setText("总计：" + String.valueOf(mTotalSpent));
	}
}
