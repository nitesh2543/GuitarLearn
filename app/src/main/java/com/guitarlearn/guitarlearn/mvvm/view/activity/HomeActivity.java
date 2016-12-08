package com.guitarlearn.guitarlearn.mvvm.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.guitarlearn.guitarlearn.R;
import com.guitarlearn.guitarlearn.tunner.activity.SimpleGuitarTunerActivity;

public class HomeActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button btn = (Button) findViewById(R.id.btn_start_tuner);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SimpleGuitarTunerActivity.class));
            }
        });
    }
}
