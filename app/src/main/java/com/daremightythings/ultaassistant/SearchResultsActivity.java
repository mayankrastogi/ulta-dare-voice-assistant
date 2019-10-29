package com.daremightythings.ultaassistant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SearchResultsActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Intent intent = getIntent();
        Uri data = intent.getData();

        if (data != null) {
            String product = data.getQueryParameter("product");
            String brand = data.getQueryParameter("brand");
            String color = data.getQueryParameter("color");
            String price = data.getQueryParameter("price");

            try {
                doGetRequest(product, brand, color, price);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                doGetRequest(null, null, null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doGetRequest(String product, String brand, String color, String price) throws IOException {
        String url = "https://us-central1-analytical-rain-257118.cloudfunctions.net/search";
        HashMap<String, String> requestMap = new HashMap<>();
        requestMap.put("category", product);
        requestMap.put("brand", brand);
        requestMap.put("color", color);
        requestMap.put("price", price);

        Gson gson = new Gson();
        String payload = gson.toJson(requestMap);

        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .post(RequestBody.create(payload, MediaType.parse("application/json")))
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, IOException e) {
                        // Error

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // For the example, you can show an error dialog or a toast
                                // on the main UI thread
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String res = response.body().string();

                        // Do something with the response
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // For the example, you can show an error dialog or a toast
                                // on the main UI thread
                                populateProductsList(res);
                            }
                        });
                    }
                });
    }

    private void populateProductsList(String response) {
        RecyclerView productsRecyclerView = findViewById(R.id.productsRecyclerView);

        // Initialize contacts
        List<Product> products = Product.createProductList(response);
        // Create adapter passing in the sample user data
        ProductRecyclerViewAdapter adapter = new ProductRecyclerViewAdapter(products);
        // Attach the adapter to the recyclerview to populate items
        productsRecyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
