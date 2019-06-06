package com.example.internship.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.internship.R;
import com.example.internship.adapters.ViewPagerAdapter;
import com.example.internship.main.best.FragmentBest;
import com.example.internship.main.favorite.FragmentFavorite;
import com.example.internship.main.popular.FragmentPopular;
import com.example.internship.models.Banner;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mMainPresenter;
    private ViewPagerAdapter mViewPagerAdapter;

    @BindView(R.id.imageview_banner) ImageView mBannerImage;
    @BindView (R.id.tablayout) TabLayout mTabLayout;
    @BindView (R.id.viewpager) ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainPresenter = new MainPresenter(this);
        mMainPresenter.getBanner();

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
    public void showBanner(List<Banner> bannerList) {
        Banner banner = bannerList.get(0);
        Glide.with(this).load(banner.getImageUrl()).into(mBannerImage);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
