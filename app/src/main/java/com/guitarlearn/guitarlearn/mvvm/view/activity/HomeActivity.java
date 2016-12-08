package com.guitarlearn.guitarlearn.mvvm.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.guitarlearn.guitarlearn.R;
import com.guitarlearn.guitarlearn.common.CustomDrawerLayout;
import com.guitarlearn.guitarlearn.common.FragmentHelper;
import com.guitarlearn.guitarlearn.common.Utility;
import com.guitarlearn.guitarlearn.mvvm.view.fragment.MenuFragment;

public class HomeActivity extends BaseActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private CustomDrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Fragment fragment = new MenuFragment();
        FragmentHelper.addFragment(this, R.id.navigation_view, fragment);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = (CustomDrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Utility.hideKeyboard(drawerView.getContext());
            }
        };
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);

    }
}
