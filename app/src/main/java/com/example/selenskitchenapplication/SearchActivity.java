package com.example.selenskitchenapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private List<String> yourDataList;
    private List<String> filteredDataList; // List to store filtered data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initialize yourDataList
        yourDataList = new ArrayList<>();

        // Add your data to yourDataList
        // Add French food types
        yourDataList.add("Coq Au Vin");
        yourDataList.add("Croque-Monsieur");
        yourDataList.add("Meuniére");
        yourDataList.add("Potatoes Dauphinoise");
        yourDataList.add("Quiche Lorraine");
        yourDataList.add("Salade Landaise");
        yourDataList.add("Spring Vegetable Stew");
        yourDataList.add("Salmon En Papillote");

        // Add Greek food types
        yourDataList.add("Gemista (Stuffed Veggies with Rice)");
        yourDataList.add("Giouvetsi (Beef Stew with Orzo Pasta)");
        yourDataList.add("Briam (Roasted Vegetables)");
        yourDataList.add("Dolmades (Stuffed Grapevine Leaves)");
        yourDataList.add("Horta (Leafy Boiled Greens)");
        yourDataList.add("Kleftiko");
        yourDataList.add("Kolokithokeftedes (Fried Zucchini - Courgette Balls)");
        yourDataList.add("Papoutsakia (Stuffed Eggplants)");
        yourDataList.add("Pastitsio (Greek Lasagna)");
        yourDataList.add("Soutzoukakia (Greek Meatballs)");
        yourDataList.add("Souvlaki (Gyros)");
        yourDataList.add("Choriatiki (Greek Salad)");
        yourDataList.add("Tomatokeftedes (Tomato Fritters)");
        yourDataList.add("Tirokroketes (Fried Cheese Balls)");
        yourDataList.add("Stifado (Greek Beef Stew)");

        // Add Mexican food types
        yourDataList.add("Burritos");
        yourDataList.add("Caldo Azteca (Aztec Soup)");
        yourDataList.add("Tacos");
        yourDataList.add("Ceviche");
        yourDataList.add("Chilaquiles");
        yourDataList.add("Huevos Rancheros (Ranch Eggs)");
        yourDataList.add("Enchiladas");

        // Add Chinese food types
        yourDataList.add("Chinese Braised Lamb Casserole, Hong-Kong Style");
        yourDataList.add("Juicy Pork and Chive Pan-Fried Dumplings");
        yourDataList.add("Sticky Garlic Sesame Chicken");
        yourDataList.add("Lo Mein Noodles");
        yourDataList.add("Roast Pork with Chinese Vegetables");

        // Add Japanese food types
        yourDataList.add("Yakitori (Japanese-style Grilled Skewered Chicken)");
        yourDataList.add("Gyoza");
        yourDataList.add("Sukiyaki");
        yourDataList.add("Onigiri");
        yourDataList.add("Teriyaki Salmon Bowl");
        yourDataList.add("Karaage");
        yourDataList.add("Gyukatsu (Beef Cutlet)");
        yourDataList.add("Katsudon");
        yourDataList.add("Gyudon");
        yourDataList.add("Miso Soup");

        // Add Israeli food types
        yourDataList.add("Falafel");
        yourDataList.add("Hummus");
        yourDataList.add("Jachnun");
        yourDataList.add("Kebab");
        yourDataList.add("Sabich with Amba");
        yourDataList.add("Msabbaha");
        yourDataList.add("Tabbuleh");
        yourDataList.add("Shawarma");
        yourDataList.add("Mujadara");

        // Add Italian food types
        yourDataList.add("Italian Pot Roast made Tuscan style");
        yourDataList.add("Italian Ravioli with Spinach, Artichokes, Capers, Sun-Dried Tomatoes");
        yourDataList.add("Caprese Salad");
        yourDataList.add("Creamy Roasted Red Pepper Tortellini");
        yourDataList.add("Spicy Sausage Rigatoni");
        yourDataList.add("Spinach Ricotta Stuffed Shells with Basil Vodka Sauce");
        yourDataList.add("Tagliatelle Bolognese");
        yourDataList.add("One Pan Tomato Basil Pasta");

        // Initialize the filteredDataList
        filteredDataList = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performFiltering(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_search) {
            Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void performSearch(String query) {
        // Perform search logic here
        // You can display search results in a ListView, RecyclerView, or any other desired view
        // For simplicity, let's just display a Toast with the search query
        Toast.makeText(this, "Search query: " + query, Toast.LENGTH_SHORT).show();
    }

    private void performFiltering(String newText) {
        // Perform real-time filtering or suggestions based on the entered text
        // Update the search results accordingly
        // For example, you can update a ListView or RecyclerView with filtered data

        filteredDataList.clear(); // Clear the previous filtered data

        for (String item : yourDataList) {
            if (item.toLowerCase().contains(newText.toLowerCase())) {
                filteredDataList.add(item);
            }
        }
    }
}

