package com.os.operando.debugmenu.sample;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class DebugMenuActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private boolean isShowDebugMenuNotification;

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (!isShowDebugMenuNotification) {
            DebugMenuNotificationManager.showDebugMenuNotification(activity);
            isShowDebugMenuNotification = true;
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        // Activityのback stack上で最後のActivityがDestroyする時に通知をクリアする
        if (activity instanceof MainActivity) {
            DebugMenuNotificationManager.cancelDebugMenuNotification(activity);
            isShowDebugMenuNotification = false;
        }
    }
}