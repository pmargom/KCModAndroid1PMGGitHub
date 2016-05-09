package com.ligartolabs.molapizza.fragment;

import android.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ligartolabs.molapizza.R;
import com.ligartolabs.molapizza.adapter.DishAdapter;
import com.ligartolabs.molapizza.model.Dish;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DishFragment extends Fragment {

    @Bind(R.id.fragment_dishes_recycler_view)
    RecyclerView dishesRecyclerView;

    final static String ARG_TABLE_DETAILS = "com.ligartolabs.molapizza.fragment.dishes";
    private LinkedList<Dish> mDishes;

    public DishFragment() {
        // Required empty public constructor
    }

    public static DishFragment newInstance(LinkedList<Dish> dishes) {
        DishFragment fragment = new DishFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_TABLE_DETAILS, dishes);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDishes = (LinkedList<Dish>) getArguments().getSerializable(ARG_TABLE_DETAILS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_dish, container, false);
        ButterKnife.bind(this, view);

        DishAdapter adapter = new DishAdapter(mDishes, getActivity());

        dishesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dishesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        dishesRecyclerView.setAdapter(adapter);

        return view;
    }

}
