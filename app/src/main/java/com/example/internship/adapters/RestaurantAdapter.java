package com.example.internship.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internship.R;
import com.example.internship.models.Restaurant;
import com.example.internship.viewholders.RestaurantViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {

    private List<Restaurant> mRestaurantList;

    private Context mContext;

    private boolean isLoadingAdded = false;

    public RestaurantAdapter(Context context) {
        mRestaurantList = new ArrayList<>();
        mContext = context;
    }

    public RestaurantAdapter(List<Restaurant> restaurantList) {
        mRestaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = mRestaurantList.get(position);
        holder.bind(restaurant, mContext);
    }

    @Override
    public int getItemCount() {
        return mRestaurantList.size();
    }

    public void setRestaurantList(List<Restaurant> restaurantList){
        mRestaurantList = restaurantList;
        notifyDataSetChanged();
    }

    public void addRestaurants(List<Restaurant> restaurants){
        mRestaurantList.addAll(restaurants);
        notifyDataSetChanged();;
    }

}
