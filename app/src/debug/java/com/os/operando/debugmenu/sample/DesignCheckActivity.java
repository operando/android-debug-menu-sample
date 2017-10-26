package com.os.operando.debugmenu.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DesignCheckActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, DesignCheckActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_check);
    }
}
