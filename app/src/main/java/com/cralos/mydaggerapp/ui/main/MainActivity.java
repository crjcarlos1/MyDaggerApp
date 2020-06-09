package com.cralos.mydaggerapp.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.cralos.mydaggerapp.BaseActivity;
import com.cralos.mydaggerapp.R;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), "MainActivity", Toast.LENGTH_SHORT).show();
    }

}
