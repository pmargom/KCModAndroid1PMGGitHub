package com.ligartolabs.molapizza.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ligartolabs.molapizza.R;
import com.ligartolabs.molapizza.model.Dish;
import com.ligartolabs.molapizza.view.DishRowViewHolder;

import java.util.LinkedList;

public class DishAdapter extends RecyclerView.Adapter<DishRowViewHolder> {

    private final LayoutInflater layoutInflater;
    private final LinkedList<Dish> mDishes;

    public DishAdapter(LinkedList<Dish> dishes, Context context) {
        layoutInflater = LayoutInflater.from(context);
        mDishes = dishes;
    }

    @Override
    public DishRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.dish_row_details, parent, false);

        return new DishRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DishRowViewHolder holder, int position) {
        Dish dish = mDishes.get(position);

        holder.setDishImage(dish.getPhoto());
        holder.setDishName(dish.getName());
        holder.setDishPrice(dish.getPrice());
        holder.setDishAllergens(dish.getAllergens());
    }

    @Override
    public int getItemCount() {
        return mDishes.size();
    }
}
