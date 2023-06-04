package com.example.selenskitchenapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private int imageMarginBottom = 16; // Set the desired margin value here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        linearLayout = findViewById(R.id.linear_layout);

        // Add images dynamically
     /*   addImageToLayout(R.drawable.italy, "Italian Food");
        addImageToLayout(R.drawable.israel, "Israeli Food");
        addImageToLayout(R.drawable.china, "Chinese Food");
        addImageToLayout(R.drawable.japan, "Japanese Food");
        addImageToLayout(R.drawable.mexico, "Mexican Food");
        addImageToLayout(R.drawable.greece, "Greek Food");
        addImageToLayout(R.drawable.france, "French Food");*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_example, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search: {
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.menu_Home: {
                Toast.makeText(HomeActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.MyProfile_menu: {
                Toast.makeText(HomeActivity.this, "My Profile clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, UserProfileActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.menu_logout: {
                Toast.makeText(HomeActivity.this, "Logout clicked", Toast.LENGTH_SHORT).show();
                logout();
                return true;
            }
            case R.id.about_menu: {
                Toast.makeText(HomeActivity.this, "About clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addImageToLayout(int imageResId, final String foodType) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(imageResId);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, imageMarginBottom);

        imageView.setLayoutParams(layoutParams);
        linearLayout.addView(imageView);

        // Set OnClickListener to navigate to the corresponding food type activity
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FoodTypeActivity.class);
                intent.putExtra("foodType", foodType);
                startActivity(intent);
            }
        });
    }

    private void logout() {
        // Clear user session (Example: assuming you store the session in SharedPreferences)
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Redirect to WelcomeActivity
        Intent intent = new Intent(HomeActivity.this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Close the current activity
    }
}
