package com.android.floatwindow;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FloatControlService extends Service {
    private static final String TAG = "FloatControlService";
    public final static String ACTION = "action";
    public final static String SHOW = "show";
    public final static String  HIDE= "hide";
    private FloatingView mFloatingView;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mFloatingView = new FloatingView(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getStringExtra(ACTION);
            switch (action) {
                case SHOW:
                    mFloatingView.show();
                    break;
                case HIDE:
                    mFloatingView.hide();
                    break;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
