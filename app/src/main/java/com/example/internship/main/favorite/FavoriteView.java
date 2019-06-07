package com.example.internship.main.favorite;

import com.example.internship.models.Restaurant;

import java.util.List;

public interface FavoriteView {

    void showRestaurants(List<Restaurant> restaurantList);

    void showError(String errorMessage);
}
