package com.ago.movieapp.ui.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ago.movieapp.R;
import com.ago.movieapp.ui.adapter.TabsAdapter;

public class HomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottom_nav;

    private TabsAdapter tabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabsAdapter = new TabsAdapter(getSupportFragmentManager());

        initUI();
    }

    private void initUI(){

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(tabsAdapter);

        bottom_nav = findViewById(R.id.bottom_nav);
    }
}
