package com.ligartolabs.molapizza.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ligartolabs.molapizza.global.Constants;
import com.ligartolabs.molapizza.model.Dish;
import com.ligartolabs.molapizza.model.Table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import com.ligartolabs.molapizza.R;

public class TableListActivity extends AppCompatActivity {

    private LinkedList<Table> mTables;
    private ArrayAdapter<Table> mAdapter;
    private ListView mTableListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTables = new LinkedList<>();

        setContentView(R.layout.activity_table_list);
        mTableListView = (ListView) findViewById(R.id.tables_list);

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, mTables);

        mTableListView.setAdapter(mAdapter);

        downloadMenu();
    }



    private void downloadMenu() {
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

                            String name = dishesJSONArray.getJSONObject(i).getString("name");
                            double price = dishesJSONArray.getJSONObject(i).getDouble("price");
                            int idDish = dishesJSONArray.getJSONObject(i).getInt("id");
                            String photo = dishesJSONArray.getJSONObject(i).getString("photo");
                            JSONArray allergensJSONArray = dishesJSONArray.getJSONObject(i).getJSONArray("allergens");
                            LinkedList<String> allergens = new LinkedList<>();
                            for (int k = 0; k < allergensJSONArray.length(); k++) {
                                allergens.add(allergensJSONArray.getString(i));
                            }
                            dishes.add(new Dish(idDish, name, price, photo, allergens));
                        }
                        mTables.add(new Table(idTable, dishes, billStatus, bill));
                    }
                    return mTables;
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
                if (tables == null) {

                    // Ha habido algún error, se lo indicamos al usuario
                    /*AlertDialog.Builder alertDialog = new AlertDialog.Builder(t);
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("No se pudo descargar la información del tiempo");
                    alertDialog.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            downloadMenu();
                        }
                    });
                    alertDialog.setNegativeButton("Regresar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Esto normalmente no se hace así, se le avisa a la actividad y ella decide
                            getActivity().finish();
                        }
                    });

                    // Mostramos el diálogo
                    alertDialog.show();*/
                }

            }
        };

        menuDownloader.execute();
    }



}
