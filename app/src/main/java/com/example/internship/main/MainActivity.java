package com.example.internship.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.internship.R;
import com.example.internship.RestaurantAdapter;
import com.example.internship.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mMainPresenter;
    private RestaurantAdapter mRestaurantAdapter;
    private List<Restaurant> mRestaurantList;

    private RecyclerView mRestaurantsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainPresenter = new MainPresenter(this);

        mRestaurantList = new ArrayList<>();
        mRestaurantsRecycler = (RecyclerView) findViewById(R.id.recycler_restaurants);
        mRestaurantsRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRestaurantAdapter = new RestaurantAdapter(mRestaurantList, this);
        mRestaurantsRecycler.setAdapter(mRestaurantAdapter);

        mMainPresenter.getRestaurants();
    }

    @Override
    public void showRestaurants(List<Restaurant> restaurantList) {
        mRestaurantList.addAll(restaurantList);
        mRestaurantAdapter.notifyDataSetChanged();
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
