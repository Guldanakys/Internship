package com.example.internship.main.best;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internship.R;
import com.example.internship.adapters.RestaurantAdapter;
import com.example.internship.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentBest extends Fragment implements BestView {

    private BestPresenter mBestPresenter;
    private RestaurantAdapter mRestaurantAdapter;
    private List<Restaurant> mRestaurantList;

    @BindView(R.id.recycler_restaurants) RecyclerView mRestaurantsRecycler;

    public FragmentBest() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.best_fragment, container, false);
        ButterKnife.bind(this, view);

        initUI();

        mBestPresenter.getRestaurants();

        return view;
    }

    private void initUI() {
        mBestPresenter = new BestPresenter(this);
        mRestaurantList = new ArrayList<>();
        mRestaurantsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRestaurantAdapter = new RestaurantAdapter(mRestaurantList, getActivity());
        mRestaurantsRecycler.setAdapter(mRestaurantAdapter);
    }

    @Override
    public void showRestaurants(List<Restaurant> restaurantList) {
        mRestaurantList.addAll(restaurantList);
        mRestaurantAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), "Network failure", Toast.LENGTH_SHORT).show();
    }
}
