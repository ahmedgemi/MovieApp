package com.ago.movieapp.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ago.movieapp.ui.fragment.PlayNowFragment;
import com.ago.movieapp.ui.fragment.TopMoviesFragment;

public class TabsAdapter extends FragmentPagerAdapter {

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new PlayNowFragment();

            case 1:
                return new TopMoviesFragment();

            case 2:
                return new PlayNowFragment();

        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Play Now";

            case 1:
                return "Top Movies";

            case 2:
                return "Favorites";

        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
