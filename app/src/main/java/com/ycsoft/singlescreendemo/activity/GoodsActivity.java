package com.ycsoft.singlescreendemo.activity;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.ycsoft.singlescreendemo.R;
import com.ycsoft.singlescreendemo.dialog.OrderDialog;

/**
 * Created by Jeremy on 2016/9/22.
 * 酒水界面
 */
public class GoodsActivity extends BaseActivity implements View.OnClickListener {
	public static final String TAG = "GoodsActivity";
	private String mPageName;
	private TextView tvPageName;
	private TextView[] mGoodsViewArray = new TextView[14];
	private String mGoodsName;

	@Override
	public void initActivity() {
		setContentView(R.layout.activity_goods);
		mPageName = getIntent().getStringExtra(TAG);
	}

	@Override
	public void initViews() {
		tvPageName = (TextView) findViewById(R.id.tv_page_goods);
		tvPageName.setText(mPageName);
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
			textView.setOnClickListener(this);
		}

	}

	@Override
	public void initDatas() {

	}

	@Override
	public void onClick(View v) {
		String string = ((TextView) v).getText().toString();
		mGoodsName = string.substring(0, string.indexOf(" "));
		showOrderDialog(mGoodsName);
	}

	@NonNull
	private OrderDialog showOrderDialog(String goodsName) {
		OrderDialog dialog = OrderDialog.getInstance(this, goodsName);
		dialog.show();
		dialog.setContentView();
		return dialog;
	}
}
