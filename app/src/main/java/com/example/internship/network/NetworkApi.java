package com.example.internship.network;

import com.example.internship.models.Banner;
import com.example.internship.models.Restaurant;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkApi {

    @GET("restaurants/")
    Observable<List<Restaurant>> getRestaurants(@Query("limit") int limit, @Query("offset") int offset);

    @GET("restaurants")
    Observable<List<Restaurant>> getAllRestaurants();

    @GET("ads/banner/main-slider/?image_size=xxhdpi")
    Single<List<Banner>> getBanner();
}
