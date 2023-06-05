package com.example.selenskitchenapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.selenskitchenapplication.CartAdapter;
import com.example.selenskitchenapplication.CartItem;
import com.example.selenskitchenapplication.CheckoutActivity;
import com.example.selenskitchenapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private List<String> cartItems;
    private ListView cartListView;
    private Button checkoutButton;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = findViewById(R.id.cart_list_view);
        checkoutButton = findViewById(R.id.checkoutButton);

        cartItems = getIntent().getStringArrayListExtra("cartItems");

        cartAdapter = new CartAdapter(this, cartItems);
        cartListView.setAdapter(cartAdapter);

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout();
            }
        });
    }

    private void checkout() {
        Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putStringArrayListExtra("cartItems", new ArrayList<>(cartItems));
        startActivity(intent);
    }
}
