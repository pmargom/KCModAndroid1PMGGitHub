package com.ligartolabs.molapizza.fragment;

import android.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ligartolabs.molapizza.R;
import com.ligartolabs.molapizza.adapter.DishAdapter;
import com.ligartolabs.molapizza.model.Dish;
import com.ligartolabs.molapizza.model.Restaurant;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DishFragment extends Fragment implements DishAdapter.OnDishClickListener {

    @Bind(R.id.fragment_dishes_recycler_view)
    RecyclerView dishesRecyclerView;

    final static String ARG_TABLE_DETAILS = "com.ligartolabs.molapizza.fragment.dishes";
    final static String ARG_TABLE_ID = "com.ligartolabs.molapizza.fragment.tableId";
    private LinkedList<Dish> mDishes;
    private int mTableId;
    private OnDishFrgamentListener mDishFragmentListener;

    public DishFragment() {
        // Required empty public constructor
    }

    public void setListener(OnDishFrgamentListener listener) {
        mDishFragmentListener = listener;
    }

    public static DishFragment newInstance(int tableId, boolean addNewDish) {

        LinkedList<Dish> dishes;
        if (addNewDish) {
            // I want to add a new dish to the current table, so I need to load all dishes
            dishes = Restaurant.getInstance().getDishes();
        } else {
            // I only want to load table dishes
            dishes = Restaurant.getInstance().getTable(tableId).getDishes();
        }
        DishFragment fragment = new DishFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_TABLE_DETAILS, dishes);
        bundle.putInt(ARG_TABLE_ID, tableId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mDishes = (LinkedList<Dish>) getArguments().getSerializable(ARG_TABLE_DETAILS);
            mTableId = getArguments().getInt(ARG_TABLE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_dish, container, false);
        ButterKnife.bind(this, view);

        DishAdapter adapter = new DishAdapter(mDishes, getActivity(), this);

        dishesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dishesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        dishesRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDishClick(Dish dish, View view) {
        Log.d("", "onDishClick: ");
        Restaurant.getInstance().getTable(mTableId).getDishes().add(dish);
        closefragment();

    }

    private void closefragment() {
        if (mDishFragmentListener != null) {
            mDishFragmentListener.onClose();
        }
    }

    public interface OnDishFrgamentListener {
        void onClose();
    }

}


