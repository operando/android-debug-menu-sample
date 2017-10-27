package com.os.operando.debugmenu.sample;

import java.util.ArrayList;
import java.util.List;


public class ApiEnvironment {

    private final String url;
    private final String description;

    public static final List<ApiEnvironment> API_ENVIRONMENTS = new ArrayList<ApiEnvironment>() {
        {
            add(new ApiEnvironment(UrlManager.API_URL, "デフォルト"));
            add(new ApiEnvironment("https://android.com/", "android debug API"));
        }
    };

    public ApiEnvironment(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }
}