package com.ligartolabs.molapizza.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private View mView;
    private OnDishClickListener mParentClickListener;
    private Context mContext;

    public DishAdapter(LinkedList<Dish> dishes, Context context, OnDishClickListener parentClickListener) {
        layoutInflater = LayoutInflater.from(context);
        mDishes = dishes;
        mParentClickListener = parentClickListener;
        mContext = context;
    }

    @Override
    public DishRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = layoutInflater.inflate(R.layout.dish_row_details, parent, false);

        return new DishRowViewHolder(mView, mParentClickListener);
    }

    @Override
    public void onBindViewHolder(DishRowViewHolder holder, int position) {
        Dish dish = mDishes.get(position);

        holder.bindDish(dish, mContext);
    }

    @Override
    public int getItemCount() {
        return mDishes.size();
    }

    public interface OnDishClickListener {
        void onDishClick(Dish dish, View view);
    }
}
