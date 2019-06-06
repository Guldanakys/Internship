package com.example.internship.main;

import com.example.internship.models.Banner;

import java.util.List;

public interface MainView {

    void showBanner(List<Banner> bannerList);

    void showError(String errorMessage);
}
