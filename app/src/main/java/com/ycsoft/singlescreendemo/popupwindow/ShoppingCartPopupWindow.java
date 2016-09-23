package com.ycsoft.singlescreendemo.popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.ycsoft.singlescreendemo.R;
import com.ycsoft.singlescreendemo.adapter.ShoppingCartAdapter;
import com.ycsoft.singlescreendemo.holder.ShoppingCartHolder;
import com.ycsoft.singlescreendemo.holder.SpentHolder;

/**
 * Created by Jeremy on 2016/9/23.
 * 购物车弹窗界面
 */
public class ShoppingCartPopupWindow extends PopupWindow implements PopupWindow.OnDismissListener,
		View.OnClickListener {
	private Context mContext;
	private View view;
	private Button btnClose, btnConfirmOrder;
	private ListView lvShoppingCart;
	private ShoppingCartAdapter mShoppingCartAdapter;
	private ShoppingCartHolder mShoppingCartHolder;
	private SpentHolder mSpentHolder;

	public ShoppingCartPopupWindow(Context mContext) {
		this(null, 390, 560, mContext);
	}

	public ShoppingCartPopupWindow(View contentView, int width, int height, Context mContext) {
		super(contentView == null ? ((LayoutInflater)
				mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.popup_shopping_cart, null)
				: contentView, width, height);
		this.mContext = mContext;
		mShoppingCartHolder = ShoppingCartHolder.getInstance();
		mSpentHolder = SpentHolder.getInstance();
	}

	@Override
	public void setContentView(View contentView) {
		super.setContentView(contentView);
		this.view = contentView;
		init();
	}

	private void init() {
		btnClose = (Button) view.findViewById(R.id.btn_close);
		btnConfirmOrder = (Button) view.findViewById(R.id.btn_confirm_order_list);
		btnClose.setOnClickListener(this);
		btnConfirmOrder.setOnClickListener(this);
		lvShoppingCart = (ListView) view.findViewById(R.id.lv_goods);
		lvShoppingCart.setItemsCanFocus(true);
	}

	@Override
	public void showAtLocation(View parent, int gravity, int x, int y) {
		super.showAtLocation(parent, gravity, x, y);
		mShoppingCartAdapter = new ShoppingCartAdapter(mContext,
				ShoppingCartHolder.getInstance().getShoppingCartGoods());
		lvShoppingCart.setAdapter(mShoppingCartAdapter);
	}

	@Override
	public void onDismiss() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_close:
				dismiss();
				break;
			case R.id.btn_confirm_order_list:
				Toast.makeText(mContext, "提交订单成功！", Toast.LENGTH_SHORT).show();
				mSpentHolder.addAllGoods(mShoppingCartHolder.getShoppingCartGoods());
				mShoppingCartHolder.clearCart();
				dismiss();
				break;
		}
	}
}
