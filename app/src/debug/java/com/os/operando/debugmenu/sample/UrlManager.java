package com.os.operando.debugmenu.sample;


public class UrlManager {

    public static final String API_URL = "https://mercarikauru-debug.com";

    private static DebugInformationPrefs debugInformationPrefs;

    public static void setDebugInformationPrefs(DebugInformationPrefs debugInformationPrefs) {
        UrlManager.debugInformationPrefs = debugInformationPrefs;
    }

    public static String getApiUrl() {
        if (debugInformationPrefs == null || !debugInformationPrefs.hasApiUrl()) {
            return API_URL;
        }
        return debugInformationPrefs.getApiUrl();
    }
}