package com.example.internship.main.favorite;

import android.os.Bundle;
import android.os.Handler;
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
import com.example.internship.PaginationScrollListener;
import com.example.internship.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentFavorite extends Fragment implements FavoriteView {

    private FavoritePresenter mFavoritePresenter;
    private RestaurantAdapter mRestaurantAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<Restaurant> mRestaurantList;

    private boolean mIsLoading = false;
    private boolean mIsLastPage = false;
    private int mOffset = 0;

    @BindView(R.id.recycler_restaurants_favorite) RecyclerView mRestaurantsRecycler;

    public FragmentFavorite() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container,false);
        ButterKnife.bind(this, view);

        initUI();

        return view;
    }

    private void initUI() {
        mFavoritePresenter = new FavoritePresenter(this);
        mRestaurantList = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRestaurantsRecycler.setLayoutManager(mLayoutManager);
        mRestaurantAdapter = new RestaurantAdapter(getActivity());
        mRestaurantsRecycler.setAdapter(mRestaurantAdapter);

        mRestaurantsRecycler.addOnScrollListener(new PaginationScrollListener(mLayoutManager) {

            @Override
            protected void loadMoreItems() {
                mIsLoading = true;
                if (!mIsLastPage) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadData(mOffset);
                        }
                    }, 200);
                }
            }

            @Override
            public boolean isLastPage() {
                return mIsLastPage;
            }

            @Override
            public boolean isLoading() {
                return mIsLoading;
            }
        });
        loadData(mOffset);
    }

    @Override
    public void showRestaurants(List<Restaurant> restaurantList) {
        mRestaurantList.addAll(restaurantList);
        mRestaurantAdapter.notifyDataSetChanged();
        //mProgressBar.setVisibility(View.INVISIBLE);
        mIsLoading = false;
        if (restaurantList.size() == 20) {
            mRestaurantAdapter.addRestaurants(restaurantList);
            mOffset += 20;
        } else {
            mIsLastPage = true;
        }
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void loadData(int offset) {
        String offsetMessage = "Loading offset " + offset + " =)";
        Toast.makeText(getActivity(), offsetMessage, Toast.LENGTH_SHORT).show();
        mFavoritePresenter.getRestaurants(offset);
    }

}
