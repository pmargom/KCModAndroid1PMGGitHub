package com.ligartolabs.molapizza.activity;

import android.app.FragmentManager;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ligartolabs.molapizza.R;
import com.ligartolabs.molapizza.fragment.DishFragment;
import com.ligartolabs.molapizza.model.Restaurant;
import com.ligartolabs.molapizza.model.Table;

public class TableDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_TABLE = "com.ligartolabs.molapizza.activity.TableDetailsActivity.EXTRA_TABLE";
    private Table mTable;

    DishFragment mDishFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_table_details);

        int tableId = getIntent().getIntExtra(EXTRA_TABLE, 0);
        mTable = Restaurant.getInstance().getTable(tableId);


        mDishFragment = DishFragment.newInstance(mTable.getDishes());

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_list_dish) == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_list_dish, mDishFragment)
                    .commit();
        }
    }


}
