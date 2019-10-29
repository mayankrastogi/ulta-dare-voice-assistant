package com.daremightythings.ultaassistant;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Product {
    private String sku;
    private String name;
    private String brand;
    private float price;

    public Product(String sku, String name, String brand, float price) {
        this.sku = sku;
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public static List<Product> createProductList(String response) {
        Gson gson = new Gson();

        HashMap<String, Object> jsonData = new HashMap<>();

        // Populate dummy data if response contains an error
        // e.g. when app is opened manually instead of from a deep-link from the assistant
        if (response.startsWith("Error")) {
            ArrayList<Product> products = new ArrayList<>();

            products.add(new Product("1031078", "Natural Lash - Black 110", "Ardell", 4.99f));
            products.add(new Product("1031079", "Natural Lash - Black 109", "Ardell", 4.99f));
            products.add(new Product("1031082", "Natural Lash - Black 105", "Ardell", 4.99f));
            products.add(new Product("1031088", "Natural Lash - Black 120", "Ardell", 4.99f));
            products.add(new Product("1031089", "Natural Lash - Black 117", "Ardell", 4.99f));

            jsonData.put("lst", products);

            response = gson.toJson(jsonData);
            jsonData = new HashMap<>();
        }

        Type listType = new TypeToken<HashMap<String, ArrayList<Product>>>() {
        }.getType();
        HashMap<String, ArrayList<Product>> products = gson.fromJson(response, listType);
        return products.get("lst");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImageUrl() {
        return "https://res.cloudinary.com/dumxziu5i/image/fetch/w_250,h_250,c_pad,r_max/https://images.ulta.com/is/image/Ulta/" + sku;
    }
}
