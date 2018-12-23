package com.ago.movieapp.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

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

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                switch (i){
                    case 0:
                        bottom_nav.setSelectedItemId(R.id.action_play_now);
                        break;

                    case 1:
                        bottom_nav.setSelectedItemId(R.id.action_top);
                        break;

                    case 2:
                        bottom_nav.setSelectedItemId(R.id.action_fav);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        bottom_nav = findViewById(R.id.bottom_nav);
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.action_play_now:
                        viewPager.setCurrentItem(0,true);
                        break;

                    case R.id.action_top:
                        viewPager.setCurrentItem(1,true);
                        break;

                    case R.id.action_fav:
                        viewPager.setCurrentItem(2,true);
                        break;
                }

                return true;
            }
        });
    }
}
