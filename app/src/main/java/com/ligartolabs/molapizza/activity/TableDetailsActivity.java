package com.ligartolabs.molapizza.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
    public static final int REQUEST_CODE = 100;
    private Table mTable;

    DishFragment mDishFragment;
    private FloatingActionButton mPayButton;
    private FloatingActionButton mAddButton;
    private int mTableId;
    private FragmentManager mFragmentManager;
    private Fragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupUI();
        setupButtons();
    }

    private void setupUI() {
        setContentView(R.layout.activity_table_details);
        mTableId = getIntent().getIntExtra(EXTRA_TABLE, 0);

        mDishFragment = DishFragment.newInstance(mTableId, false);

        mFragmentManager = getFragmentManager();
        mFragment = mFragmentManager.findFragmentById(R.id.fragment_list_dish);
//        if (mFragment == null) {
//            mFragmentManager.beginTransaction()
//                    .add(R.id.fragment_list_dish, mDishFragment)
//                    .commit();
//        }
//        else {
            mFragmentManager.beginTransaction()
                    .add(R.id.fragment_list_dish, mDishFragment)
                    .commit();
        //}

    }

    private void setupButtons() {

        mTable = Restaurant.getInstance().getTable(mTableId);
        LinkedList<Dish> dishes = mTable.getDishes();
        boolean buildStatus = mTable.getBuildStatus();
        boolean tableHasItems = dishes != null && dishes.size() > 0;

        mPayButton = (FloatingActionButton) findViewById(R.id.table_details_activity_button_pay);
        mPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payTable();
            }
        });

        // If table does not have any dishes, the button must be hidden
        mPayButton.setVisibility(tableHasItems && !buildStatus ? View.VISIBLE : View.INVISIBLE);

        mAddButton = (FloatingActionButton) findViewById(R.id.table_details_activity_button_add);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewDish(mTable);
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

    void addNewDish(Table table) {
        Intent addDishActivityIntent = new Intent(this, AddDishActivity.class);
        addDishActivityIntent.putExtra(AddDishActivity.EXTRA_TABLE_ADD_DISH, table.getId() - 1);
        startActivityForResult(addDishActivityIntent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                setupUI();
                setupButtons();
//                setResult(RESULT_OK);
//                finish();
            }
        }
    }

}
