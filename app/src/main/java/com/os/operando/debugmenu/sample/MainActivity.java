package com.os.operando.debugmenu.sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.os.operando.debugmenu.sample.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        UrlManager.getApiUrl();

        Picasso.with(this)
                .load("https://www.mercarikauru.com/img/meta/logo.png")
                .into(binding.image);
    }
}