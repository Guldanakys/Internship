package com.example.internship.main;

import com.example.internship.models.Restaurant;

import java.util.List;

public interface MainView {

    void showRestaurants(List<Restaurant> restaurantList);

    void showError();
}
