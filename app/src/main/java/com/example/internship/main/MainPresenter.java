package com.example.internship.main;

import android.util.Log;

import com.example.internship.models.Banner;
import com.example.internship.network.NetworkClient;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    private MainView mMainView;

    private static final String TAG = "MainPresenter";

    public MainPresenter(MainView mainView) {
        mMainView = mainView;
    }

    public void getBanner() {
        getSingle().subscribeWith(getSingleObserver());
    }

    private Single<List<Banner>> getSingle() {
        return NetworkClient.getInstance()
                .getNetworkApi()
                .getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableSingleObserver<List<Banner>> getSingleObserver() {
        return new DisposableSingleObserver<List<Banner>>() {
            @Override
            public void onSuccess(List<Banner> banners) {
                Log.d(TAG,"onSuccess");
                mMainView.showBanner(banners);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError" + e);
                mMainView.showError("Unable to fetch data");
            }
        };
    }
}
