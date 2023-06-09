package com.example.selenskitchenapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodTypeActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private LinearLayout linearLayout;
    private int imageMarginBottom = 16; // Set the desired margin value here
    private Map<String, List<String>> foodItems;
    private List<String> cartItems;
    private CartManager cartManager;
    private Button orderButton1;
    private Button orderButton2;
    private Button orderButton3;
    private Button orderButton4;
    private Button orderButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        linearLayout = findViewById(R.id.linear_layout);

        cartItems = new ArrayList<>();
        foodItems = new HashMap<>();

        // Create an instance of CartManager
        cartManager = new CartManager();

        // Get the food type from the intent extra
        String foodType = getIntent().getStringExtra("foodType");

        // Set the appropriate layout based on the food type
        int layoutResId = getLayoutResourceForFoodType(foodType);
        setContentView(layoutResId);

        // Set the title
        TextView titleTextView = findViewById(R.id.title_text_view);
        titleTextView.setText(foodType);

        // Find order buttons
        orderButton1 = findViewById(R.id.order_button_1);
        orderButton2 = findViewById(R.id.order_button_2);
        orderButton3 = findViewById(R.id.order_button_3);
        orderButton4 = findViewById(R.id.order_button_4);
        orderButton5 = findViewById(R.id.order_button_5);

        // Set click listeners for order buttons
        orderButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOrderButtonClick(orderButton1.getText().toString());
            }
        });
        orderButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOrderButtonClick(orderButton2.getText().toString());
            }
        });
        orderButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOrderButtonClick(orderButton3.getText().toString());
            }
        });
        orderButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOrderButtonClick(orderButton4.getText().toString());
            }
        });
        orderButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOrderButtonClick(orderButton5.getText().toString());
            }
        });

        // Add food items
        addFoodItems(foodType);
    }

    private void addFoodItems(String foodType) {
        List<String> foodTypeItems = foodItems.get(foodType);
        if (foodTypeItems != null) {
            displayFoodItems(foodTypeItems);
        } else {
            // Food type not found, handle the error or display a message
            Toast.makeText(this, "Food type not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayFoodItems(List<String> foodTypeItems) {
        if (foodTypeItems.size() >= 1) {
            orderButton1.setText(foodTypeItems.get(0));
            orderButton1.setVisibility(View.VISIBLE);
        } else {
            orderButton1.setVisibility(View.GONE);
        }
        if (foodTypeItems.size() >= 2) {
            orderButton2.setText(foodTypeItems.get(1));
            orderButton2.setVisibility(View.VISIBLE);
        } else {
            orderButton2.setVisibility(View.GONE);
        }
        if (foodTypeItems.size() >= 3) {
            orderButton3.setText(foodTypeItems.get(2));
            orderButton3.setVisibility(View.VISIBLE);
        } else {
            orderButton3.setVisibility(View.GONE);
        }
        if (foodTypeItems.size() >= 4) {
            orderButton4.setText(foodTypeItems.get(3));
            orderButton4.setVisibility(View.VISIBLE);
        } else {
            orderButton4.setVisibility(View.GONE);
        }
        if (foodTypeItems.size() >= 5) {
            orderButton5.setText(foodTypeItems.get(4));
            orderButton5.setVisibility(View.VISIBLE);
        } else {
            orderButton5.setVisibility(View.GONE);
        }
    }

    private void handleOrderButtonClick(String item) {
        // Call the addToCart method of the cartManager
        cartManager.addToCart(item);
        Toast.makeText(this, "Added to cart: " + item, Toast.LENGTH_SHORT).show();
    }

    public void goToFoodType(String foodType) {
        Intent intent = new Intent(this, FoodTypeActivity.class);
        intent.putExtra("foodType", foodType);
        startActivity(intent);
    }

    private int getLayoutResourceForFoodType(String foodType) {
        switch (foodType) {
            case "Italian Food":
                return R.layout.italian_food_layout;
            case "Israeli Food":
                return R.layout.israeli_food_layout;
            case "Japanese Food":
                return R.layout.japanese_food_layout;
            case "Chinese Food":
                return R.layout.chinese_food_layout;
            case "Mexican Food":
                return R.layout.mexican_food_layout;
            case "Greek Food":
                return R.layout.greek_food_layout;
            case "French Food":
                return R.layout.french_food_layout;
            default:
                return R.layout.default_food_layout;
        }
    }

    public void goToHome(View view) {
        startActivity(new Intent(FoodTypeActivity.this, HomeActivity.class));
    }
}