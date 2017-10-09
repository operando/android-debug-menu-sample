package com.os.operando.debugmenu.sample;

import android.content.Context;
import android.content.SharedPreferences;

public class DebugInformationPrefs {

    private static final String NAME = "debug_info";
    private static final String KEY_API_URL = "api_url";

    private static DebugInformationPrefs singleton;
    private SharedPreferences prefs;

    private DebugInformationPrefs(Context context) {
        this.prefs = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static DebugInformationPrefs get(Context context) {
        if (singleton != null) return singleton;
        synchronized (DebugInformationPrefs.class) {
            if (singleton == null) singleton = new DebugInformationPrefs(context);
        }
        return singleton;
    }

    public String getApiUrl() {
        return prefs.getString(KEY_API_URL, "");
    }

    public void setApiUrl(String apiUrl) {
        prefs.edit().putString(KEY_API_URL, apiUrl).apply();
    }

    public boolean hasApiUrl() {
        return prefs.contains(KEY_API_URL);
    }
}