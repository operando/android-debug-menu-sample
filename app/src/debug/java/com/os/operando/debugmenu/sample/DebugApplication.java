package com.os.operando.debugmenu.sample;

public class DebugApplication extends MainApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        DebugMenuNotificationManager.createNotificationChannel(this);

        UrlManager.setDebugInformationPrefs(DebugInformationPrefs.get(this));

        registerActivityLifecycleCallbacks(new DebugMenuActivityLifecycleCallbacks());
    }
}