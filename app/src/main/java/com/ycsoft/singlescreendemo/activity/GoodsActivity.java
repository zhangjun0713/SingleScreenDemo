package com.ycsoft.singlescreendemo.activity;

import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.ycsoft.singlescreendemo.GoodsEntity;
import com.ycsoft.singlescreendemo.R;
import com.ycsoft.singlescreendemo.dialog.OrderDialog;
import com.ycsoft.singlescreendemo.popupwindow.ShoppingCartPopupWindow;

/**
 * Created by Jeremy on 2016/9/22.
 * 酒水界面
 */
public class GoodsActivity extends BaseActivity implements View.OnClickListener {
	public static final String TAG = "GoodsActivity";
	private String mPageName;
	private TextView tvPageName;
	private TextView[] mGoodsViewArray = new TextView[14];
	private TextView tvShoppingCart;
	private ShoppingCartPopupWindow popupWindow;

	@Override
	public void initActivity() {
		setContentView(R.layout.activity_goods);
		mPageName = getIntent().getStringExtra(TAG);
	}

	@Override
	public void initViews() {
		tvPageName = (TextView) findViewById(R.id.tv_page_goods);
		tvPageName.setText(mPageName);
		tvShoppingCart = (TextView) findViewById(R.id.tv_shopping_cart);
		tvShoppingCart.setOnClickListener(this);
		mGoodsViewArray[0] = (TextView) findViewById(R.id.tv_goods_1);
		mGoodsViewArray[1] = (TextView) findViewById(R.id.tv_goods_2);
		mGoodsViewArray[2] = (TextView) findViewById(R.id.tv_goods_3);
		mGoodsViewArray[3] = (TextView) findViewById(R.id.tv_goods_4);
		mGoodsViewArray[4] = (TextView) findViewById(R.id.tv_goods_5);
		mGoodsViewArray[5] = (TextView) findViewById(R.id.tv_goods_6);
		mGoodsViewArray[6] = (TextView) findViewById(R.id.tv_goods_7);
		mGoodsViewArray[7] = (TextView) findViewById(R.id.tv_goods_8);
		mGoodsViewArray[8] = (TextView) findViewById(R.id.tv_goods_9);
		mGoodsViewArray[9] = (TextView) findViewById(R.id.tv_goods_10);
		mGoodsViewArray[10] = (TextView) findViewById(R.id.tv_goods_11);
		mGoodsViewArray[11] = (TextView) findViewById(R.id.tv_goods_12);
		mGoodsViewArray[12] = (TextView) findViewById(R.id.tv_goods_13);
		mGoodsViewArray[13] = (TextView) findViewById(R.id.tv_goods_14);
		for (TextView textView :
				mGoodsViewArray) {
			textView.setOnClickListener(mGoodsClickListener);
		}

	}

	/**
	 * 商品点击监听器
	 */
	private View.OnClickListener mGoodsClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			GoodsEntity goodsEntity = (GoodsEntity) v.getTag();
			showOrderDialog(goodsEntity);
		}
	};

	@Override
	public void initDatas() {
		for (int i = 0; i < mGoodsViewArray.length; i++) {
			GoodsEntity entity = new GoodsEntity();
			entity.goodsName = "鸡尾酒" + (i + 1);
			entity.goodsPrice = 100 + "";
			mGoodsViewArray[i].setText(entity.goodsName + " ￥" + entity.goodsPrice);
			mGoodsViewArray[i].setTag(entity);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_shopping_cart:
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				} else {
					popupWindow = new ShoppingCartPopupWindow(this);
					popupWindow.setOutsideTouchable(false);
					popupWindow.setFocusable(true);
					popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
					popupWindow.showAtLocation((View) v.getParent(), Gravity.NO_GRAVITY, 865, 160);
				}
				break;
		}
	}

	@NonNull
	private OrderDialog showOrderDialog(GoodsEntity goodsEntity) {
		OrderDialog dialog = OrderDialog.getInstance(this, goodsEntity);
		dialog.show();
		dialog.setContentView();
		return dialog;
	}
}
