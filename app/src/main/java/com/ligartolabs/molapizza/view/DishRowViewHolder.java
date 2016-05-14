package com.ligartolabs.molapizza.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ligartolabs.molapizza.R;
import com.ligartolabs.molapizza.adapter.DishAdapter;
import com.ligartolabs.molapizza.global.Constants;
import com.ligartolabs.molapizza.global.Utils;
import com.ligartolabs.molapizza.model.Dish;

import java.util.LinkedList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DishRowViewHolder extends RecyclerView.ViewHolder {

    private Utils mUtils;
    private Dish mDish;

    @Bind(R.id.dish_name)
    TextView dishNameTextView;
    @Bind(R.id.dish_image)
    ImageView dishImage;

    @Bind(R.id.dish_price)
    TextView dishPriceTextView;

    @Bind(R.id.allergens_image_gluten)
    ImageView dishImageGluten;
    @Bind(R.id.allergens_image_crustaceos)
    ImageView dishImageCrustaceos;
    @Bind(R.id.allergens_image_pescado)
    ImageView dishImagePescado;
    @Bind(R.id.allergens_image_soja)
    ImageView dishImageSoja;
    @Bind(R.id.allergens_image_cacahuetes)
    ImageView dishImageCacahuetes;
    @Bind(R.id.allergens_image_huevos)
    ImageView dishImageHuevos;

    @Bind(R.id.gluten_name)
    TextView dishGlutenTextView;
    @Bind(R.id.crustaceos_name)
    TextView dishCrustaceosTextView;
    @Bind(R.id.pescado_name)
    TextView dishPescadoTextView;
    @Bind(R.id.soja_name)
    TextView dishSojaTextView;
    @Bind(R.id.cacahuetes_name)
    TextView dishCacahuetesTextView;
    @Bind(R.id.huevos_name)
    TextView dishHuevosTextView;

    @Bind(R.id.dish_quantity)
    TextView dishQuantityTextView;

    public DishRowViewHolder(final View itemView, final DishAdapter.OnDishClickListener parentClickListener) {
        super(itemView);
        mUtils = new Utils();
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DishAdapter", "han hecho click");
                parentClickListener.onDishClick(mDish, v);
            }
        });
    }

    public void bindDish(Dish dish, Context context) {

        mDish = dish;

        dishNameTextView.setText(dish.getName());
        dishImage.setImageDrawable(mUtils.buildDrawableForName(dish.getPhoto(), context));
        dishPriceTextView.setText(mUtils.formatMoney(dish.getPrice()));
        LinkedList<String> allergens = dish.getAllergens();
        if (!allergens.contains(Constants.Allergens.cacahuetes.toString())) {
            dishImageCacahuetes.setVisibility(View.GONE);
            dishCacahuetesTextView.setVisibility(View.GONE);
        }
        if (!allergens.contains(Constants.Allergens.gluten.toString())) {
            dishImageGluten.setVisibility(View.GONE);
            dishGlutenTextView.setVisibility(View.GONE);
        }
        if (!allergens.contains(Constants.Allergens.soja.toString())) {
            dishImageSoja.setVisibility(View.GONE);
            dishSojaTextView.setVisibility(View.GONE);
        }
        if (!allergens.contains(Constants.Allergens.pescado.toString())) {
            dishImagePescado.setVisibility(View.GONE);
            dishPescadoTextView.setVisibility(View.GONE);
        }
        if (!allergens.contains(Constants.Allergens.huevos.toString())) {
            dishImageHuevos.setVisibility(View.GONE);
            dishHuevosTextView.setVisibility(View.GONE);
        }
        if (!allergens.contains(Constants.Allergens.crustaceos.toString())) {
            dishImageCrustaceos.setVisibility(View.GONE);
            dishCrustaceosTextView.setVisibility(View.GONE);
        }

        dishQuantityTextView.setText(dish.getQuantity() + "");
    }

}
