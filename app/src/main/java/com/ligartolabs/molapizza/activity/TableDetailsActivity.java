package com.ligartolabs.molapizza.activity;

import android.app.FragmentManager;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ligartolabs.molapizza.R;
import com.ligartolabs.molapizza.fragment.DishFragment;
import com.ligartolabs.molapizza.model.Dish;
import com.ligartolabs.molapizza.model.Restaurant;
import com.ligartolabs.molapizza.model.Table;

import java.util.LinkedList;

public class TableDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_TABLE = "com.ligartolabs.molapizza.activity.TableDetailsActivity.EXTRA_TABLE";
    private Table mTable;

    DishFragment mDishFragment;
    private FloatingActionButton mPayButton;
    private FloatingActionButton mAddButton;
    private LinkedList<Dish> mTableDishes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupUI();
        setupButtons();
    }

    private void setupUI() {
        setContentView(R.layout.activity_table_details);

        int tableId = getIntent().getIntExtra(EXTRA_TABLE, 0);
        mTable = Restaurant.getInstance().getTable(tableId);

        mTableDishes = mTable.getDishes();

        mDishFragment = DishFragment.newInstance(mTableDishes);

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_list_dish) == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_list_dish, mDishFragment)
                    .commit();
        }

    }

    private void setupButtons() {

        boolean buildStatus = mTable.getBuildStatus();
        boolean tableHasItems = mTableDishes != null && mTableDishes.size() > 0;

        mPayButton = (FloatingActionButton) findViewById(R.id.table_details_activity_button_pay);
        mPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payTable();
                Snackbar.make(findViewById(android.R.id.content), "Table bill was done.", Snackbar.LENGTH_LONG).show();
            }

        });

        // If table does not have any dishes, the button must be hidden
        mPayButton.setVisibility(tableHasItems && !buildStatus ? View.VISIBLE : View.INVISIBLE);

        mAddButton = (FloatingActionButton) findViewById(R.id.table_details_activity_button_add);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            addNewDish();
            Snackbar.make(findViewById(android.R.id.content), "New dish was added.", Snackbar.LENGTH_LONG).show();
            }
        });

        // If the table bill is already paid, the button for paying must be hidden
        mAddButton.setVisibility(buildStatus ? View.INVISIBLE : View.VISIBLE);
    }

    private void payTable() {
        Restaurant.getInstance().payBill(mTable);
        setResult(RESULT_OK);
        finish();
    }

    private void addNewDish() {

    }


}
