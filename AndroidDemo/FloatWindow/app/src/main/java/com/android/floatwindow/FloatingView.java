package com.android.floatwindow;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class FloatingView extends RelativeLayout implements View.OnTouchListener {
    private static final String TAG = "FloatingView";
    private Context mContext;
    private ImageView mImageView;
    private WindowManager.LayoutParams mParams;
    private FloatManager mFloatManager;
    private View mView;
    private int mTouchStartX,mTouchStartY;

    public FloatingView(Context context) {
        super(context);
        mContext = context.getApplicationContext();
        mFloatManager = FloatManager.getInstance(mContext);
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        mView = mLayoutInflater.inflate(R.layout.floating_view,null);
        mImageView = mView.findViewById(R.id.car);
        mImageView.setImageResource(R.drawable.load_image);
        mImageView.setOnTouchListener(this);
        Log.d(TAG, "FloatingView: construct finished");
    }

    public void show() {
        mParams = new WindowManager.LayoutParams();
        //1.设置窗口的位置
        mParams.gravity = Gravity.TOP | Gravity.LEFT;
        mParams.x = 0;
        mParams.y = 100;
        mParams.width = LayoutParams.WRAP_CONTENT;
        mParams.height = LayoutParams.WRAP_CONTENT;
        //2.设置窗口的类型
        mParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        mParams.alpha = (float) 0.9;
        //3.设置窗口的行为
        mFloatManager.addView(mView,mParams);
        AnimationDrawable mAnimationDrawable = (AnimationDrawable)mImageView.getDrawable();
        mAnimationDrawable.start();
    }

    public void hide() {
        mFloatManager.removeView(mView);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchStartX = (int) event.getRawX();
                mTouchStartY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                mParams.x = (int)event.getRawX() - mTouchStartX;
                mParams.y = (int)event.getRawY() - mTouchStartY;
                mFloatManager.updateView(mView,mParams);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }
}
