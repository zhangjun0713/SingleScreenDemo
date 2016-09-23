package com.ycsoft.singlescreendemo.holder;

import com.ycsoft.singlescreendemo.entity.GoodsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 2016/9/23.
 * 购物车逻辑类
 */
public class ShoppingCartHolder {
	private static ShoppingCartHolder mShoppingCartHolder;
	private List<GoodsEntity> mGoodsEntities = new ArrayList<>();

	private ShoppingCartHolder() {
	}

	public static ShoppingCartHolder getInstance() {
		if (mShoppingCartHolder == null)
			mShoppingCartHolder = new ShoppingCartHolder();
		return mShoppingCartHolder;
	}

	/**
	 * 获取购物车中的商品
	 *
	 * @return
	 */
	public List<GoodsEntity> getShoppingCartGoods() {
		return mGoodsEntities;
	}

	public boolean addGoodsToShoppingCart(GoodsEntity goodsEntity) {
		return mGoodsEntities.add(goodsEntity);
	}

	public boolean removeFromShoppingCart(GoodsEntity goodsEntity) {
		return mGoodsEntities.remove(goodsEntity);
	}

	/**
	 * 该商品是否已经存在于购物车中了
	 *
	 * @param goodsEntity
	 * @return
	 */
	public boolean isExist(GoodsEntity goodsEntity) {
		return mGoodsEntities.contains(goodsEntity);
	}

	/**
	 * 清除购物车中的物品
	 */
	public void clearCart() {
		mGoodsEntities.clear();
	}
}
