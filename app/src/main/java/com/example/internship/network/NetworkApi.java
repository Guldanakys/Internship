package com.example.internship.network;

import com.example.internship.models.Restaurant;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NetworkApi {

    @GET("restaurants")
    Observable<List<Restaurant>> getRestaurants();
}
