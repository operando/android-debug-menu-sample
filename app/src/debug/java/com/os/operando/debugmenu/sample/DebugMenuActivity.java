package com.os.operando.debugmenu.sample;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DebugMenuActivity extends AppCompatActivity {

    public static PendingIntent createPendingIntent(Context context) {
        Intent i = new Intent(context, DebugMenuActivity.class);
        return PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_menu);
    }

}
