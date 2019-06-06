package com.example.internship.main.best;

import android.util.Log;

import com.example.internship.main.best.BestView;
import com.example.internship.models.Restaurant;
import com.example.internship.network.NetworkClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BestPresenter {

    private BestView mBestView;

    private final static String TAG = "BestPresenter";

    public BestPresenter(BestView bestView) {
        mBestView = bestView;
    }

    public void getRestaurants() {
        getObservable().subscribeWith(getObserver());
    }

    private Observable<List<Restaurant>> getObservable() {
        return NetworkClient.getInstance()
                .getNetworkApi()
                //.getAllRestaurants()
                .getRestaurants(20, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<List<Restaurant>> getObserver() {
        return new DisposableObserver<List<Restaurant>>() {
            @Override
            public void onNext(List<Restaurant> restaurantList) {
                Log.d(TAG,"onNext");
                mBestView.showRestaurants(restaurantList);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError" + e);
                mBestView.showError("Unable to fetch data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
            }
        };
    }
}
