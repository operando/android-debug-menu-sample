package com.os.operando.debugmenu.sample;

import android.content.Context;
import android.content.SharedPreferences;

public class DebugInformationPrefs {

    private static final String NAME = "debug_info";
    private static final String KEY_API_URL = "api_url";
    private static final String KEY_PICASSO_LOGGING_ENABLED = "picasso_logging_enabled";
    private static final String KEY_PICASSO_ARE_INDICATORS_ENABLED = "picasso_are_indicators_enabled";

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

    public boolean getPicassoLoggingEnabled() {
        return prefs.getBoolean(KEY_PICASSO_LOGGING_ENABLED, false);
    }

    public void setPicassoLoggingEnabled(boolean loggingEnabled) {
        prefs.edit().putBoolean(KEY_PICASSO_LOGGING_ENABLED, loggingEnabled).apply();
    }


    public boolean getPicassoAreIndicatorsEnabled() {
        return prefs.getBoolean(KEY_PICASSO_ARE_INDICATORS_ENABLED, false);
    }

    public void setPicassoAreIndicatorsEnabled(boolean areIndicatorsEnabled) {
        prefs.edit().putBoolean(KEY_PICASSO_ARE_INDICATORS_ENABLED, areIndicatorsEnabled).apply();
    }
}