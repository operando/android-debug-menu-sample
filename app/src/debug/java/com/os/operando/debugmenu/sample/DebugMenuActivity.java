package com.os.operando.debugmenu.sample;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

import com.os.operando.debugmenu.sample.databinding.ActivityDebugMenuBinding;

public class DebugMenuActivity extends AppCompatActivity {

    private ActivityDebugMenuBinding binding;

    public static PendingIntent createPendingIntent(Context context) {
        Intent i = new Intent(context, DebugMenuActivity.class);
        return PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_menu);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_debug_menu);

        binding.openApplicationSettings.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.fromParts("package", getPackageName(), null));
            startActivity(intent);
        });
    }
}