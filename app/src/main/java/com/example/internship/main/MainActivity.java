package com.example.internship.main;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.internship.R;
import com.example.internship.models.Restaurant;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainPresenter = new MainPresenter(this);
        mMainPresenter.getRestaurants();
    }

    @Override
    public void showRestaurants(List<Restaurant> restaurantList) {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Network failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(0, R.anim.fade_out);
    }
}
