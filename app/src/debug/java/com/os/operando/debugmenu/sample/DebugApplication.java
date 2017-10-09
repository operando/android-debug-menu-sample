package com.os.operando.debugmenu.sample;

import com.squareup.picasso.Picasso;

public class DebugApplication extends MainApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        DebugMenuNotificationManager.createNotificationChannel(this);

        UrlManager.setDebugInformationPrefs(DebugInformationPrefs.get(this));

        DebugInformationPrefs prefs = DebugInformationPrefs.get(this);
        Picasso picasso = Picasso.with(this);
        picasso.setLoggingEnabled(prefs.getPicassoLoggingEnabled());
        picasso.setIndicatorsEnabled(prefs.getPicassoAreIndicatorsEnabled());

        registerActivityLifecycleCallbacks(new DebugMenuActivityLifecycleCallbacks());
    }
}