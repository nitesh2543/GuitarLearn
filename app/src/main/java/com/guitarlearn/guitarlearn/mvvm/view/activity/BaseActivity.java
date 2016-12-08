package com.guitarlearn.guitarlearn.mvvm.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.guitarlearn.guitarlearn.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
