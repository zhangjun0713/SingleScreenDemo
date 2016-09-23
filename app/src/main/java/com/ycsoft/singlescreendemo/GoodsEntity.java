package com.ycsoft.singlescreendemo;

import java.io.Serializable;

/**
 * Created by Jeremy on 2016/9/23.
 * 商品实体
 */
public class GoodsEntity implements Serializable {
	/**
	 * 商品名字
	 */
	public String goodsName;
	/**
	 * 商品价格
	 */
	public String goodsPrice;
	/**
	 * 商品数量
	 */
	public String goodsCount;
}
