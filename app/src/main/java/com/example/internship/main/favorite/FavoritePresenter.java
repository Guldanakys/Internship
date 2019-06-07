package com.example.internship.main.favorite;

import android.util.Log;

import com.example.internship.models.Restaurant;
import com.example.internship.network.NetworkClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class FavoritePresenter {

    private FavoriteView mFavoriteView;

    private final static String TAG = "FavoritePresenter";

    public FavoritePresenter(FavoriteView favoriteView) {
        mFavoriteView = favoriteView;
    }

    public void getRestaurants(Integer offset) {
        getObservable(offset).subscribeWith(getObserver());
    }

    private Observable<List<Restaurant>> getObservable(Integer offset) {
        return NetworkClient.getInstance()
                .getNetworkApi()
                .getRestaurants(20, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<List<Restaurant>> getObserver() {
        return new DisposableObserver<List<Restaurant>>() {
            @Override
            public void onNext(List<Restaurant> restaurantList) {
                Log.d(TAG,"onNext");
                mFavoriteView.showRestaurants(restaurantList);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError" + e);
                mFavoriteView.showError("Unable to fetch data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
            }
        };
    }
}
