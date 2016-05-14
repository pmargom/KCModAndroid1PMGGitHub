package com.ligartolabs.molapizza.model;

import com.ligartolabs.molapizza.global.Constants;
import com.ligartolabs.molapizza.global.Utils;

import java.io.Serializable;
import java.util.LinkedList;

public class Table implements Serializable {
    private int mId;
    private LinkedList<Dish> mDishes;
    private boolean mBillStatus;
    private double mBill;

    public double getBill() {

        updateBill();
        return mBill;
    }

    private void updateBill() {
        if (mDishes == null || mDishes.size() == 0) return;

        mBill = 0;
        for (Dish dish: mDishes) {
            mBill += dish.getPrice() * dish.getQuantity();
        }
    }

    public Table(int id, LinkedList<Dish> dishes, boolean billStatus) {
        this.mId = id;
        this.mDishes = dishes;
        this.mBillStatus = billStatus;
        updateBill();
    }

    public boolean getBuildStatus() {
        return mBillStatus;
    }

    protected void setBuildStatus(boolean buildStatus) {
        this.mBillStatus = buildStatus;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public LinkedList<Dish> getDishes() {
        return mDishes;
    }

    public void setDishes(LinkedList<Dish> dishes) {
        mDishes = dishes;
    }

    public void addNewDish(Dish dish) {
        mDishes.add(dish);
        updateBill();
    }

    @Override
    public String toString() {
        return String.format("Mesa nº - %d: Paid: %s -> Bill: %s", mId, mBillStatus ? "YES" : "NO ", new Utils().formatMoney(mBill));
    }
}
