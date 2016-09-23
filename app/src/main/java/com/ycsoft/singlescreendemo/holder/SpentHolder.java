package com.ycsoft.singlescreendemo.holder;

import com.ycsoft.singlescreendemo.GoodsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 2016/9/23.
 * 消费逻辑类
 */
public class SpentHolder {
	private static SpentHolder mSpentHolder;
	private List<GoodsEntity> mSpentGoodsEntities = new ArrayList<>();

	private SpentHolder() {
	}

	public static SpentHolder getInstance() {
		if (mSpentHolder == null)
			mSpentHolder = new SpentHolder();
		return mSpentHolder;
	}

	/**
	 * 获取消费记录商品
	 *
	 * @return
	 */
	public List<GoodsEntity> getSpentGoods() {
		return mSpentGoodsEntities;
	}

	/**
	 * 添加到已消费
	 *
	 * @param goodsEntity
	 * @return
	 */
	public boolean addGoodsToSpent(GoodsEntity goodsEntity) {
		return mSpentGoodsEntities.add(goodsEntity);
	}
}
