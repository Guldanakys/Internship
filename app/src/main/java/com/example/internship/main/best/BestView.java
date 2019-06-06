package com.example.internship.main.best;

import com.example.internship.models.Restaurant;

import java.util.List;

public interface BestView {

    void showRestaurants(List<Restaurant> restaurantList);

    void showError(String errorMessage);
}
