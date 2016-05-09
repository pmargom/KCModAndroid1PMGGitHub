package com.ligartolabs.molapizza.global;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Constants {

    public final static String TABLES_URI = "http://www.mocky.io/v2/573056af1200007901863812";
    public final static String DISHES_URI = "http://www.mocky.io/v2/572b7d36130000da14e2b873";
    public enum Allergens {
        gluten,
        crustaceos,
        huevos,
        pescado,
        cacahuetes,
        soja
    }

    public static String formatMoney(double amount) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return String.format("€%s", formatter.format(amount));
    }
}
