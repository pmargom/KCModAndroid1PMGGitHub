package com.ligartolabs.molapizza.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ligartolabs.molapizza.global.Constants;
import com.ligartolabs.molapizza.model.Dish;
import com.ligartolabs.molapizza.model.Restaurant;
import com.ligartolabs.molapizza.model.Table;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import com.ligartolabs.molapizza.R;

public class TableListActivity extends AppCompatActivity {

    private ArrayAdapter<Table> mAdapter;
    private Restaurant restaurant;
    private ListView mTableListView;

    private void setupModel() {
        restaurant = Restaurant.getInstance();
    }

    private void setupUI() {

        setContentView(R.layout.activity_table_list);
        mTableListView = (ListView) findViewById(R.id.tables_list);

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, restaurant.getTables());

        mTableListView.setAdapter(mAdapter);

        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.table_list_activity_button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewTable();
                Snackbar.make(findViewById(android.R.id.content), "New table was added at the end.", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void addNewTable() {
        int newId = restaurant.getTables().getLast().getId() + 1;
        restaurant.addNewTable(new Table(newId, new LinkedList<Dish>(), false, 0));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupModel();
        setupUI();

        downloadMenu();
        downloadTables();
    }

    private void downloadTables() {
        AsyncTask<Object, Integer, LinkedList<Table>> menuDownloader = new AsyncTask<Object, Integer, LinkedList<Table>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected LinkedList<Table> doInBackground(Object... params) {
                URL url;
                InputStream input = null;
                try {
                    url = new URL(Constants.TABLES_URI);
                    HttpURLConnection con = (HttpURLConnection) (url.openConnection());
                    con.connect();
                    int responseLength = con.getContentLength();
                    byte[] data = new byte[1024];
                    long currentBytes = 0;
                    int downloadedBytes;
                    input = con.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    while ((downloadedBytes = input.read(data)) != -1) {
                        if (isCancelled()) {
                            input.close();
                            return null;
                        }

                        sb.append(new String(data, 0, downloadedBytes));
                        if (responseLength > 0) {
                            currentBytes += downloadedBytes;
                        }
                    }

                    JSONObject jsonData = new JSONObject(sb.toString());
                    JSONArray tables = jsonData.getJSONArray("tables");
                    for (int i = 0; i < tables.length(); i++) {
                        JSONObject tableObject = tables.getJSONObject(i);
                        int idTable = tableObject.getInt("id");
                        double bill = tableObject.getDouble("bill");
                        boolean billStatus = tableObject.getBoolean("billStatus");
                        JSONArray dishesJSONArray = tableObject.getJSONArray("dishes");
                        LinkedList<Dish> dishes = new LinkedList<>();
                        for (int j = 0; j < dishesJSONArray.length(); j++) {
                            JSONObject element = dishesJSONArray.getJSONObject(j);
                            String name = element.getString("name");
                            double price = element.getDouble("price");
                            int idDish = element.getInt("id");
                            String photo = element.getString("photo");
                            JSONArray allergensJSONArray = element.getJSONArray("allergens");
                            LinkedList<String> allergens = new LinkedList<>();
                            for (int k = 0; k < allergensJSONArray.length(); k++) {
                                allergens.add(allergensJSONArray.getString(k));
                            }
                            dishes.add(new Dish(idDish, name, price, photo, allergens));
                        }
                        restaurant.addNewTable(new Table(idTable, dishes, billStatus, bill));
                    }
                    return restaurant.getTables();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(LinkedList<Table> tables) {
                super.onPostExecute(tables);
                /*if (tables.size() == 0) {
                    Snackbar.make(findViewById(android.R.id.content), "There are no tables active at the moment.", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Active tables downloaded.", Snackbar.LENGTH_LONG).show();
                }*/
            }
        };

        menuDownloader.execute();
    }

    private void downloadMenu() {
        AsyncTask<Object, Integer, LinkedList<Dish>> menuDownloader = new AsyncTask<Object, Integer, LinkedList<Dish>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected LinkedList<Dish> doInBackground(Object... params) {
                URL url;
                InputStream input = null;
                try {
                    url = new URL(Constants.DISHES_URI);
                    HttpURLConnection con = (HttpURLConnection) (url.openConnection());
                    con.connect();
                    int responseLength = con.getContentLength();
                    byte[] data = new byte[1024];
                    long currentBytes = 0;
                    int downloadedBytes;
                    input = con.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    while ((downloadedBytes = input.read(data)) != -1) {
                        if (isCancelled()) {
                            input.close();
                            return null;
                        }

                        sb.append(new String(data, 0, downloadedBytes));
                        if (responseLength > 0) {
                            currentBytes += downloadedBytes;
                        }
                    }

                    JSONObject jsonData = new JSONObject(sb.toString());
                    JSONArray dishesJSONArray = jsonData.getJSONArray("dishes");
                    LinkedList<Dish> dishes = new LinkedList<>();
                    for (int i = 0; i < dishesJSONArray.length(); i++) {
                        JSONObject dishObject = dishesJSONArray.getJSONObject(i);
                        String name = dishObject.getString("name");
                        double price = dishObject.getDouble("price");
                        int id = dishObject.getInt("id");
                        String photo = dishObject.getString("photo");
                        JSONArray allergensJSONArray = dishObject.getJSONArray("allergens");
                        LinkedList<String> allergens = new LinkedList<>();
                        for (int k = 0; k < allergensJSONArray.length(); k++) {
                            allergens.add(allergensJSONArray.getString(k));
                        }
                        dishes.add(new Dish(id, name, price, photo, allergens));
                    }
                    restaurant.setDishes(dishes);
                    return restaurant.getDishes();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(LinkedList<Dish> dishes) {
                super.onPostExecute(dishes);

                Snackbar.make(findViewById(android.R.id.content), "Restaurant dish list and active tables downloaded.", Snackbar.LENGTH_LONG).show();
            }
        };

        menuDownloader.execute();
    }



}
