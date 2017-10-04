package com.os.operando.debugmenu.sample;

public class DebugApplication extends MainApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        DebugMenuNotificationManager.createNotificationChannel(this);

        registerActivityLifecycleCallbacks(new DebugMenuActivityLifecycleCallbacks());
    }
}