package com.ycsoft.singlescreendemo.activity.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsoft.singlescreendemo.R;
import com.ycsoft.singlescreendemo.activity.GoodsActivity;
import com.ycsoft.singlescreendemo.util.DensityUtil;

/**
 * Created by Jeremy on 2016/9/21.
 */
public class HotelFragment extends Fragment implements View.OnFocusChangeListener, View.OnClickListener {
    public static final String TAG = "HotelFragment";
    private String mPageName;
    private View mView;
    private TextView tvPageName;
    /**
     * 0 酒店介绍<p>1 酒水点单<p>2 新闻<p>3 天气<p>4 主题<p>5 消费记录 <p>6 多屏互动<p>7 呼叫服务
     */
    private TextView[] mTextViewArrays = new TextView[8];
    private ImageView whiteBorder;
    private int mFocusItemIndex = -1;
    /**
     * 白色焦点框要移动到的坐标x和y点
     */
    private int whiteX, whiteY;
    /**
     * 白色焦点框宽高
     */
    private int whiteWidth, whiteHeight;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mPageName = bundle.getString(TAG);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_hotel, null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        whiteBorder = (ImageView) mView.findViewById(R.id.iv_white_border);
        tvPageName = (TextView) mView.findViewById(R.id.tv_page_hotel);
        mTextViewArrays[0] = (TextView) mView.findViewById(R.id.tv_hotel_1);
        mTextViewArrays[1] = (TextView) mView.findViewById(R.id.tv_hotel_2);
        mTextViewArrays[2] = (TextView) mView.findViewById(R.id.tv_hotel_3);
        mTextViewArrays[3] = (TextView) mView.findViewById(R.id.tv_hotel_4);
        mTextViewArrays[4] = (TextView) mView.findViewById(R.id.tv_hotel_5);
        mTextViewArrays[5] = (TextView) mView.findViewById(R.id.tv_hotel_6);
        mTextViewArrays[6] = (TextView) mView.findViewById(R.id.tv_hotel_7);
        mTextViewArrays[7] = (TextView) mView.findViewById(R.id.tv_hotel_8);
        for (TextView textView :
                mTextViewArrays) {
            textView.setOnFocusChangeListener(this);
        }
        for (TextView textView :
                mTextViewArrays) {
            textView.setOnClickListener(this);
        }
        tvPageName.setText(mPageName);
        mHandler.sendEmptyMessageDelayed(INIT_FINISHED, 500);
    }

    /**
     * 初始化完成
     */
    private static final int INIT_FINISHED = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case INIT_FINISHED:
                    mTextViewArrays[0].requestFocus();
                    break;
            }
        }
    };

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.tv_hotel_1:
                mFocusItemIndex = 0;
                break;
            case R.id.tv_hotel_2:
                mFocusItemIndex = 1;
                break;
            case R.id.tv_hotel_3:
                mFocusItemIndex = 2;
                break;
            case R.id.tv_hotel_4:
                mFocusItemIndex = 3;
                break;
            case R.id.tv_hotel_5:
                mFocusItemIndex = 4;
                break;
            case R.id.tv_hotel_6:
                mFocusItemIndex = 5;
                break;
            case R.id.tv_hotel_7:
                mFocusItemIndex = 6;
                break;
            case R.id.tv_hotel_8:
                mFocusItemIndex = 7;
                break;
        }
        if (b) {
            whiteWidth = view.getMeasuredWidth();
            whiteHeight = view.getMeasuredHeight();
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) whiteBorder.getLayoutParams();
            params.width = whiteWidth + DensityUtil.dip2px(getActivity(), 16);
            params.height = whiteHeight + DensityUtil.dip2px(getActivity(), 16);
            whiteBorder.setLayoutParams(params);
            int[] location = new int[2];
            view.getLocationOnScreen(location);
            whiteX = location[0] - DensityUtil.dip2px(getActivity(), 8);
            whiteY = location[1] - DensityUtil.dip2px(getActivity(), 8);
            whiteBorder.setVisibility(View.VISIBLE);
            switch (mFocusItemIndex) {
                case 0:
                case 1:
                    flyWhiteBorder(whiteX, whiteY, 0, -80);
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    flyWhiteBorder(whiteX, whiteY, 0, -80);
                    break;

            }
        } else {
            whiteBorder.setVisibility(View.GONE);
        }
    }

    /**
     * 白色焦点框飞动、移动、变大
     *
     * @param paramX
     * @param paramY
     * @param offsetX x坐标偏移量，相对于初始的白色框的中心点
     * @param offsetY y坐标偏移量，相对于初始的白色框的中心点
     */
    private void flyWhiteBorder(float paramX,
                                float paramY, int offsetX, int offsetY) {
        if ((this.whiteBorder != null)) {
            ViewPropertyAnimator localViewPropertyAnimator = this.whiteBorder
                    .animate();
            localViewPropertyAnimator.setDuration(150L);
            localViewPropertyAnimator.x(paramX + offsetX).y(paramY + offsetY);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mTextViewArrays[0]) {
            Toast.makeText(getActivity(), "暂未开放-->" + mTextViewArrays[0].getText().toString(), Toast.LENGTH_SHORT).show();
        } else if (view == mTextViewArrays[1]) {
            Intent intent = new Intent(getActivity(), GoodsActivity.class);
            intent.putExtra(GoodsActivity.TAG,mTextViewArrays[1].getText().toString());
            getActivity().startActivity(intent);
        } else if (view == mTextViewArrays[2]) {
            Toast.makeText(getActivity(), "暂未开放-->" + mTextViewArrays[2].getText().toString(), Toast.LENGTH_SHORT).show();
        } else if (view == mTextViewArrays[3]) {
            Toast.makeText(getActivity(), "暂未开放-->" + mTextViewArrays[3].getText().toString(), Toast.LENGTH_SHORT).show();
        } else if (view == mTextViewArrays[4]) {
            Toast.makeText(getActivity(), "暂未开放-->" + mTextViewArrays[4].getText().toString(), Toast.LENGTH_SHORT).show();
        } else if (view == mTextViewArrays[5]) {
            Toast.makeText(getActivity(), "暂未开放-->" + mTextViewArrays[5].getText().toString(), Toast.LENGTH_SHORT).show();
        } else if (view == mTextViewArrays[6]) {
            Toast.makeText(getActivity(), "暂未开放-->" + mTextViewArrays[6].getText().toString(), Toast.LENGTH_SHORT).show();
        } else if (view == mTextViewArrays[7]) {
            Toast.makeText(getActivity(), "暂未开放-->" + mTextViewArrays[7].getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
