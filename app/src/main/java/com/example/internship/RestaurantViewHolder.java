package com.example.internship;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.internship.models.Restaurant;

import butterknife.BindView;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {

    private ImageView mImage;
    private TextView mTitle;

    public RestaurantViewHolder(@NonNull View itemView) {
        super(itemView);

        mTitle = (TextView) itemView.findViewById(R.id.textview_title);
        mImage = (ImageView) itemView.findViewById(R.id.imageview_restaurant);
    }

    public void bind(Restaurant restaurant, Context context) {
        mTitle.setText(restaurant.getTitle());
        Glide.with(context).load(restaurant.getCardImageUrl()).into(mImage);
    }
}
