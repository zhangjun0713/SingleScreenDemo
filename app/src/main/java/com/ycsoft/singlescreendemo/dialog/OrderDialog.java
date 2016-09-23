package com.ycsoft.singlescreendemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsoft.singlescreendemo.GoodsEntity;
import com.ycsoft.singlescreendemo.R;
import com.ycsoft.singlescreendemo.holder.ShoppingCartHolder;

/**
 * Created by Jeremy on 2016/9/22.
 * 自定义下单Dialog
 */
public class OrderDialog extends Dialog implements View.OnClickListener {

	private static OrderDialog mOrderDialog;
	private Button[] mNumberButtonArray = new Button[10];
	private StringBuilder mInputStringBuilder = new StringBuilder();
	private ImageView ivAdd, ivReduce;
	private ImageButton ibtnDelete;
	private TextView tvGoodsName, tvCount;
	private Button btnPositive, btnNegative;
	private int count = 1;
	private static GoodsEntity mGoodsEntity;
	private ShoppingCartHolder mShoppingCartHolder;

	public OrderDialog(Context context) {
		this(context, R.style.Dialog);
	}

	private OrderDialog(Context context, int theme) {
		super(context, theme);
		mShoppingCartHolder = ShoppingCartHolder.getInstance();
	}

	/**
	 * 拿到Dialog对象
	 *
	 * @param context
	 * @return
	 */
	public static OrderDialog getInstance(Context context, GoodsEntity goodsEntity) {
		mGoodsEntity = goodsEntity;
		mOrderDialog = new OrderDialog(context);
		return mOrderDialog;
	}

	/**
	 * 设置自定义的Dialog布局
	 */
	public void setContentView() {
		setContentView(R.layout.dialog_order);
		init();
	}

	private void init() {
		tvGoodsName = (TextView) findViewById(R.id.tv_goods_name);
		tvGoodsName.setText(mGoodsEntity.goodsName);
		ivAdd = (ImageView) findViewById(R.id.iv_count_add);
		ivAdd.setOnClickListener(this);
		ivReduce = (ImageView) findViewById(R.id.iv_count_reduce);
		ivReduce.setOnClickListener(this);
		tvCount = (TextView) findViewById(R.id.tv_count);
		mInputStringBuilder.append(tvCount.getText().toString());
		mNumberButtonArray[0] = (Button) findViewById(R.id.btn_0);
		mNumberButtonArray[1] = (Button) findViewById(R.id.btn_1);
		mNumberButtonArray[2] = (Button) findViewById(R.id.btn_2);
		mNumberButtonArray[3] = (Button) findViewById(R.id.btn_3);
		mNumberButtonArray[4] = (Button) findViewById(R.id.btn_4);
		mNumberButtonArray[5] = (Button) findViewById(R.id.btn_5);
		mNumberButtonArray[6] = (Button) findViewById(R.id.btn_6);
		mNumberButtonArray[7] = (Button) findViewById(R.id.btn_7);
		mNumberButtonArray[8] = (Button) findViewById(R.id.btn_8);
		mNumberButtonArray[9] = (Button) findViewById(R.id.btn_9);
		for (Button btn :
				mNumberButtonArray) {
			btn.setOnClickListener(numberClickListener);
		}
		ibtnDelete = (ImageButton) findViewById(R.id.ibtn_delete);
		ibtnDelete.setOnClickListener(this);
		btnNegative = (Button) findViewById(R.id.btn_negative);
		btnPositive = (Button) findViewById(R.id.btn_positive);
		btnNegative.setOnClickListener(this);
		btnPositive.setOnClickListener(this);
	}

	private View.OnClickListener numberClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (Integer.valueOf(mInputStringBuilder.toString() + ((Button) v).getText().toString()) <= 999) {
				mInputStringBuilder.append(((Button) v).getText().toString());
				count = Integer.valueOf(mInputStringBuilder.toString());
			} else {
				Toast.makeText(getContext(), "数量最大为999", Toast.LENGTH_SHORT).show();
			}
			tvCount.setText(mInputStringBuilder.toString());
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_negative:
				dismiss();
				break;

			case R.id.btn_positive:
				if (TextUtils.isEmpty(tvCount.getText().toString())) {
					Toast.makeText(getContext(),"数量不能为空！",Toast.LENGTH_SHORT).show();
					return;
				} else if(!TextUtils.isEmpty(tvCount.getText().toString())
						&& Integer.valueOf(tvCount.getText().toString()) == 0){
					Toast.makeText(getContext(),"数量不能为0！",Toast.LENGTH_SHORT).show();
					return;
				}
				if (mShoppingCartHolder.isExist(mGoodsEntity)) {
					mGoodsEntity.goodsCount = String.valueOf(Integer.valueOf(tvCount.getText().toString())
							+ Integer.valueOf(mGoodsEntity.goodsCount));
				} else {
					mGoodsEntity.goodsCount = tvCount.getText().toString();
					mShoppingCartHolder.addGoodsToShoppingCart(mGoodsEntity);
				}
				dismiss();
				break;

			case R.id.iv_count_add:
				if (count < 999) {
					count++;
				} else {
					Toast.makeText(getContext(), "数量最大为999", Toast.LENGTH_SHORT).show();
				}
				mInputStringBuilder.replace(0, mInputStringBuilder.length(), String.valueOf(count));
				tvCount.setText(mInputStringBuilder.toString());
				break;

			case R.id.iv_count_reduce:
				if (count > 1) {
					count--;
				}
				mInputStringBuilder.replace(0, mInputStringBuilder.length(), String.valueOf(count));
				tvCount.setText(mInputStringBuilder.toString());
				break;

			case R.id.ibtn_delete:
				if (mInputStringBuilder.length() > 0) {
					mInputStringBuilder.delete(mInputStringBuilder.length() - 1, mInputStringBuilder.length());
					count = Integer.valueOf(mInputStringBuilder.length() == 0 ? "0" : mInputStringBuilder.toString());
				}
				tvCount.setText(mInputStringBuilder.toString());
				break;
		}
	}
}
