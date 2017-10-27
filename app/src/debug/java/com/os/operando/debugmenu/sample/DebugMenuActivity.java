package com.os.operando.debugmenu.sample;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;

import com.os.operando.debugmenu.sample.databinding.ActivityDebugMenuBinding;
import com.squareup.picasso.Picasso;

import onactivityresult.ActivityResult;
import onactivityresult.ExtraString;
import onactivityresult.OnActivityResult;

public class DebugMenuActivity extends AppCompatActivity {

    private static final int RESULT_SELECT_API_ENVIRONMENT = 1;

    private ActivityDebugMenuBinding binding;

    public static PendingIntent createPendingIntent(Context context) {
        Intent i = new Intent(context, DebugMenuActivity.class);
        return PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_menu);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_debug_menu);

        binding.openApplicationSettings.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.fromParts("package", getPackageName(), null));
            startActivity(intent);
        });

        binding.apiUrl.setText(UrlManager.getApiUrl());
        binding.apiUrl.setSelection(binding.apiUrl.getText().toString().length());
        binding.apiUrl.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                DebugInformationPrefs.get(DebugMenuActivity.this).setApiUrl(s.toString());
            }
        });
        binding.apiUrlClear.setOnClickListener(v -> {
            DebugInformationPrefs.get(DebugMenuActivity.this).setApiUrl(UrlManager.API_URL);
            binding.apiUrl.setText(UrlManager.API_URL);
        });
        binding.apiUrlList.setOnClickListener(v -> startActivityForResult(ApiEnvironmentListActivity.createIntent(this), RESULT_SELECT_API_ENVIRONMENT));

        binding.designCheck.setOnClickListener(v -> startActivity(DesignCheckActivity.createIntent(this)));

        binding.picassoEnableDebugLog.setChecked(Picasso.with(this).isLoggingEnabled());
        binding.picassoEnableDebugLog.setOnCheckedChangeListener((buttonView, isChecked) -> {
            DebugInformationPrefs.get(this).setPicassoLoggingEnabled(isChecked);
            Picasso.with(this).setLoggingEnabled(isChecked);
        });

        binding.picassoEnabledIndicators.setChecked(Picasso.with(this).areIndicatorsEnabled());
        binding.picassoEnabledIndicators.setOnCheckedChangeListener((buttonView, isChecked) -> {
            DebugInformationPrefs.get(this).setPicassoAreIndicatorsEnabled(isChecked);
            Picasso.with(this).setIndicatorsEnabled(isChecked);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResult.onResult(requestCode, resultCode, data).into(this);
    }

    @OnActivityResult(requestCode = RESULT_SELECT_API_ENVIRONMENT, resultCodes = {RESULT_OK})
    void onSelectApiEnvironment(@ExtraString(name = ApiEnvironmentListActivity.RESULT_API_URL) String url) {
        binding.apiUrl.setText(url);
    }
}