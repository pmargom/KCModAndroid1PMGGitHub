package com.ligartolabs.molapizza.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ligartolabs.molapizza.R;
import com.ligartolabs.molapizza.fragment.DishFragment;

public class AddDishActivity extends AppCompatActivity implements DishFragment.OnDishFrgamentListener {

    public static final String EXTRA_TABLE_ADD_DISH = "com.ligartolabs.molapizza.activity.AddDishActivity.EXTRA_TABLE_ADD_DISH";
    private DishFragment mDishFragment;
    private FloatingActionButton mPayButton;
    private FloatingActionButton mAddButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupUI();
        setupButtons();
    }

    private void setupUI() {
        setContentView(R.layout.activity_table_details);

        int tableId = getIntent().getIntExtra(EXTRA_TABLE_ADD_DISH, 0);

        mDishFragment = DishFragment.newInstance(tableId, true);
        mDishFragment.setListener(this);

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_list_dish) == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_list_dish, mDishFragment)
                    .commit();
        }

    }

    private void setupButtons() {

        // I have to hide buttons
        mPayButton = (FloatingActionButton) findViewById(R.id.table_details_activity_button_pay);
        mPayButton.setVisibility(View.INVISIBLE);
        mAddButton = (FloatingActionButton) findViewById(R.id.table_details_activity_button_add);
        mAddButton.setVisibility(View.INVISIBLE );
    }

    @Override
    public void onClose() {
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_list_dish);
        if (fragment != null) {
            fm.beginTransaction().remove(fragment).commit();
        }
        setResult(RESULT_OK);
        finish();
    }
}


