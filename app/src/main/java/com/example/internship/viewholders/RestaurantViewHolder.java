package com.example.internship.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.internship.R;
import com.example.internship.models.Restaurant;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageview_restaurant) ImageView mImage;
    @BindView(R.id.textview_title) TextView mTitle;

    public RestaurantViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Restaurant restaurant, Context context) {
        mTitle.setText(restaurant.getTitle());
        Glide.with(context).load(restaurant.getCardImageUrl()).into(mImage);
    }
}
