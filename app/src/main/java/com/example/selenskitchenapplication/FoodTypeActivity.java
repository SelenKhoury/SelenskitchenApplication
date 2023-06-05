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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scrollView = findViewById(R.id.scroll_view);
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

        // Add food items
        addFoodItems(foodType);
    }
/*
    private void addFoodItems(String foodType) {
        switch (foodType) {
            case "Italian Food":
                addItalianFoodItems();
                break;
            case "Israeli Food":
                addIsraeliFoodItems();
                break;
            case "Japanese Food":
                addJapaneseFoodItems();
                break;
            case "Chinese Food":
                addChineseFoodItems();
                break;
            case "Mexican Food":
                addMexicanFoodItems();
                break;
            case "Greek Food":
                addGreekFoodItems();
                break;
            case "French Food":
                addFrenchFoodItems();
                break;
            default:
                break;
        }
    }*/
    private void addFoodItems(String foodType) {
        List<String> foodTypeItems = foodItems.get(foodType);
        if (foodTypeItems != null) {
            displayFoodItems(foodTypeItems);
        } else {
            // Food type not found, handle the error or display a message
        }
    }
    /*
    private void addItalianFoodItems() {
        List<String> italianFoodItems = new ArrayList<>();
        italianFoodItems.add("Italian Pot Roast made Tuscan style");
        italianFoodItems.add("Italian Ravioli with Spinach, Artichokes, Capers, Sun-Dried Tomatoes");
        italianFoodItems.add("Caprese Salad");
        italianFoodItems.add("Creamy Roasted Red Pepper Tortellini");
        italianFoodItems.add("Spicy Sausage Rigatoni");
        italianFoodItems.add("Spinach Ricotta Stuffed Shells with Basil Vodka Sauce");
        italianFoodItems.add("Tagliatelle Bolognese");
        italianFoodItems.add("One Pan Tomato Basil Pasta");
        foodItems.put("Italian Food", italianFoodItems);
    }

    private void addIsraeliFoodItems() {
        List<String> israeliFoodItems = new ArrayList<>();
        israeliFoodItems.add("Falafel");
        israeliFoodItems.add("Hummus");
        israeliFoodItems.add("Jachnun");
        israeliFoodItems.add("Kebab");
        israeliFoodItems.add("Sabich with amba");
        israeliFoodItems.add("Msabbaha");
        israeliFoodItems.add("Tabbuleh");
        israeliFoodItems.add("Shawarma");
        israeliFoodItems.add("Mujadara");
        foodItems.put("Israeli Food", israeliFoodItems);
    }

    private void addJapaneseFoodItems() {
        List<String> japaneseFoodItems = new ArrayList<>();
        japaneseFoodItems.add("Grilled Skewered Chicken (Yakitori)");
        japaneseFoodItems.add("Gyoza");
        japaneseFoodItems.add("Sukiyaki");
        japaneseFoodItems.add("Onigiri");
        japaneseFoodItems.add("Teriyaki Salmon Bowl");
        japaneseFoodItems.add("Karaage");
        japaneseFoodItems.add("Gyukatsu (Beef Cutlet)");
        japaneseFoodItems.add("Katsudon");
        japaneseFoodItems.add("Gyudon");
        japaneseFoodItems.add("Miso Soup");
        foodItems.put("Japanese Food", japaneseFoodItems);
    }

    private void addChineseFoodItems() {
        List<String> chineseFoodItems = new ArrayList<>();
        chineseFoodItems.add("Braised Lamb Casserole, Hong-Kong Style");
        chineseFoodItems.add("Juicy Pork and Chive Pan-Fried Dumplings");
        chineseFoodItems.add("Sticky Garlic Sesame Chicken");
        chineseFoodItems.add("Lo Mein Noodles");
        chineseFoodItems.add("Roast Pork with Chinese Vegetables");
        foodItems.put("Chinese Food", chineseFoodItems);
    }

    private void addMexicanFoodItems() {
        List<String> mexicanFoodItems = new ArrayList<>();
        mexicanFoodItems.add("Burritos");
        mexicanFoodItems.add("Caldo Azteca (Aztec Soup)");
        mexicanFoodItems.add("Chiles en Nogada (Stuffed Peppers)");
        mexicanFoodItems.add("Tamales");
        mexicanFoodItems.add("Enchiladas");
        foodItems.put("Mexican Food", mexicanFoodItems);
    }

    private void addGreekFoodItems() {
        List<String> greekFoodItems = new ArrayList<>();
        greekFoodItems.add("Moussaka");
        greekFoodItems.add("Gyro");
        greekFoodItems.add("Tzatziki");
        greekFoodItems.add("Spanakopita");
        greekFoodItems.add("Souvlaki");
        foodItems.put("Greek Food", greekFoodItems);
    }

    private void addFrenchFoodItems() {
        List<String> frenchFoodItems = new ArrayList<>();
        frenchFoodItems.add("Croissant");
        frenchFoodItems.add("Baguette");
        frenchFoodItems.add("Escargots de Bourgogne (Snails in Garlic Butter)");
        frenchFoodItems.add("Coq au Vin");
        frenchFoodItems.add("Ratatouille");
        frenchFoodItems.add("Creme Brulee");
        foodItems.put("French Food", frenchFoodItems);
    }*/

    private void displayFoodItems(List<String> foodTypeItems) {
        for (String item : foodTypeItems) {
            View foodItemView = createFoodItemView(item);
            Button orderButton = foodItemView.findViewById(R.id.order_button);
            orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Call the addToCart method of the cartManager
                    cartManager.addToCart(item);
                    Toast.makeText(FoodTypeActivity.this, "Added to cart: " + item, Toast.LENGTH_SHORT).show();
                }
            });
            linearLayout.addView(foodItemView);
        }
    }

    private View createFoodItemView(final String item) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        LinearLayout foodItemLayout = new LinearLayout(this);
        foodItemLayout.setLayoutParams(layoutParams);
        foodItemLayout.setOrientation(LinearLayout.HORIZONTAL);
        foodItemLayout.setPadding(16, 16, 16, 16);

        TextView itemTextView = new TextView(this);
        itemTextView.setText(item);
        itemTextView.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1
        ));
        Button orderButton = new Button(this);
        orderButton.setText("Order Now");
        orderButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the addToCart method of the cartManager
                cartManager.addToCart(item);
                Toast.makeText(FoodTypeActivity.this, "Added to cart: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        foodItemLayout.addView(itemTextView);
        foodItemLayout.addView(orderButton);

        return foodItemLayout;
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

    public void homereturn(View view) {
        startActivity(new Intent(FoodTypeActivity.this, HomeActivity.class));
    }
}
