package com.ago.movieapp.ui.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ago.movieapp.R;
import com.ago.movieapp.data.cache.sharedpreference.UserPreference;
import com.ago.movieapp.data.cache.storage.ImageStorageRepo;
import com.ago.movieapp.ui.adapter.TabsAdapter;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottom_nav;
    private DrawerLayout drawerLayout;
    private NavigationView nav;

    private TabsAdapter tabsAdapter;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabsAdapter = new TabsAdapter(getSupportFragmentManager());

        initUI();
        initSideMenu();
    }

    private void initUI(){

        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);
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

    private void initSideMenu(){

        drawerLayout = findViewById(R.id.drawerLayout);
        nav = findViewById(R.id.nav);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        mDrawerToggle.getDrawerArrowDrawable().setColor(Color.WHITE);

        drawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        //get header view
        View headerView =  nav.getHeaderView(0);
        TextView textView_name = headerView.findViewById(R.id.textView_name);
        CircleImageView profile_image = headerView.findViewById(R.id.profile_image);

        String userName = UserPreference.getInstance().getName();
        String profileImageURL = UserPreference.getInstance().getImageURL();

        if (userName!=null)
            textView_name.setText("Welcome, " + userName);

        if (profileImageURL != null)
            Picasso.get().load(profileImageURL).placeholder(R.drawable.profile_image).into(profile_image);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
