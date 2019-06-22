package com.android.floatwindow;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

/**
 * 悬浮窗管理类
 */
public class FloatManager {
    private WindowManager mWindowManager;
    private Context mContext;
    /**
     * 使用单例模式创建FloatManager的对象
     * ①创建私有的静态属性
     * ②创建私有的构造函数
     * ③创建共有的静态构造函数，用于外部获取FloatManager对象
     */
    private static FloatManager mFloatManager;

    public static FloatManager getInstance(Context context) {
        if (mFloatManager == null) {
            synchronized (FloatManager.class) {
                if (mFloatManager == null) {
                    mFloatManager = new FloatManager(context);
                }
            }
        }
        return mFloatManager;
    }

    private FloatManager(Context context) {
        mContext = context;
        mWindowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     *
     * @param view
     * @param params
     * @return
     */
    protected boolean addView(View view,WindowManager.LayoutParams params) {
        try {
            mWindowManager.addView(view,params);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     *
     * @param view
     * @return
     */
    protected boolean removeView(View view) {
        try {
            mWindowManager.removeView(view);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    protected boolean updateView(View view,WindowManager.LayoutParams params) {
        try {
            mWindowManager.updateViewLayout(view,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
