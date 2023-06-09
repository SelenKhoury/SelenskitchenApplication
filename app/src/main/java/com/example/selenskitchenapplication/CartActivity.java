package com.example.selenskitchenapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private List<CartItem> cartItems;
    private ListView cartListView;
    private Button checkoutButton;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = findViewById(R.id.cart_list_view);
        checkoutButton = findViewById(R.id.checkoutButton);

        Intent intent = getIntent();
        if (intent != null) {
            cartItems = intent.getParcelableArrayListExtra("cartItems");
        }

        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        cartAdapter = new CartAdapter(this, cartItems);
        cartListView.setAdapter(cartAdapter);

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout();
            }
        });
    }
    public void viewCart(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra("cartItems", new ArrayList<>(CartManager.getCartItems())); // Pass the cart items to the CartActivity
        startActivity(intent);
    }

    private void checkout() {
        Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putParcelableArrayListExtra("cartItems", new ArrayList<>(cartItems));
        startActivity(intent);
    }
}