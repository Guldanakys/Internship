package com.example.internship.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.internship.R;
import com.example.internship.adapters.ViewPagerAdapter;
import com.example.internship.main.best.FragmentBest;
import com.example.internship.main.favorite.FragmentFavorite;
import com.example.internship.main.popular.FragmentPopular;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private ViewPagerAdapter mViewPagerAdapter;

    @BindView (R.id.tablayout) TabLayout mTabLayout;
    @BindView (R.id.viewpager) ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupViewPager();

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(new FragmentBest(), "Лучшее");
        mViewPagerAdapter.addFragment(new FragmentPopular(), "Популярное");
        mViewPagerAdapter.addFragment(new FragmentFavorite(), "Избранное");
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(0, R.anim.fade_out);
    }
}
