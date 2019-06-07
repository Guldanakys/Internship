package com.example.internship.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.internship.main.best.FragmentBest;
import com.example.internship.main.favorite.FragmentFavorite;
import com.example.internship.main.popular.FragmentPopular;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final int COUNT = 3;

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //return mFragmentList.get(position);
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentBest();
                break;
            case 1:
                fragment = new FragmentPopular();
                break;
            case 2:
                fragment = new FragmentFavorite();
                break;
        }
        return fragment;

    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Лучшее";
                break;
            case 1:
                title = "Популярное";
                break;
            case 2:
                title = "Избранные";
                break;
        }
        return title;
    }

}
