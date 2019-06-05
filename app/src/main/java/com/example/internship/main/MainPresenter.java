package com.example.internship.main;

import android.util.Log;

import com.example.internship.models.Restaurant;
import com.example.internship.network.NetworkClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    private MainView mMainView;

    private String TAG = "MainPresenter";

    public MainPresenter(MainView mainView) {
        mMainView = mainView;
    }

    public void getRestaurants() {
        getObservable().subscribeWith(getObserver());
    }

    public Observable<List<Restaurant>> getObservable() {
        return NetworkClient.getInstance()
                .getNetworkApi()
                .getRestaurants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<List<Restaurant>> getObserver() {
        return new DisposableObserver<List<Restaurant>>() {
            @Override
            public void onNext(List<Restaurant> restaurantList) {
                Log.d(TAG,"onNext");
                mMainView.showRestaurants(restaurantList);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError" + e);
                mMainView.showError();
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
            }
        };
    }
}
