package com.example.selenskitchenapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private List<String> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ListView cartListView = findViewById(R.id.cart_list_view);

        Intent intent = getIntent();
        cartItems = intent.getStringArrayListExtra("cartItems");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cartItems);
        cartListView.setAdapter(adapter);
    }

    public void checkout(View view) {
        Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putStringArrayListExtra("cartItems", (ArrayList<String>) cartItems);
        startActivity(intent);
    }

}
