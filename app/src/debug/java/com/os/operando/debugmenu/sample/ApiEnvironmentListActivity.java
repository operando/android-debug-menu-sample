package com.os.operando.debugmenu.sample;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.os.operando.debugmenu.sample.databinding.ActivityApiEnvironmentListBinding;

public class ApiEnvironmentListActivity extends AppCompatActivity implements ApiEnvironmentListAdapter.OnApiEnvironmentClickListener {

    public static final String RESULT_API_URL = "api_url";

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, ApiEnvironmentListActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityApiEnvironmentListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_api_environment_list);

        ApiEnvironmentListAdapter apiEnvironmentListAdapter = new ApiEnvironmentListAdapter(this);
        apiEnvironmentListAdapter.addAll(ApiEnvironment.API_ENVIRONMENTS);
        binding.list.setAdapter(apiEnvironmentListAdapter);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.addItemDecoration(new DividerDecoration(this));
    }

    @Override
    public void onApiEnvironmentClick(ApiEnvironment apiEnvironment) {
        Intent i = new Intent();
        i.putExtra(RESULT_API_URL, apiEnvironment.getUrl());
        setResult(RESULT_OK, i);
        finish();
    }
}