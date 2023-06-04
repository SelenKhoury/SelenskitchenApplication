package com.example.selenskitchenapplication;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private List<String> cartItems;
    private int tableNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_activity);

        // Get the cart items from the intent extras
        cartItems = getIntent().getStringArrayListExtra("cartItems");

        // Calculate the number of meals
        int numMeals = cartItems.size();

        // Assign a table number based on the number of meals
        tableNumber = assignTableNumber(numMeals);

        // Display the assigned table number
        TextView tableNumberTextView = findViewById(R.id.table_number_text_view);
        tableNumberTextView.setText("Table " + tableNumber);

        // Display the order summary
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        String orderSummary = generateOrderSummary();
        orderSummaryTextView.setText(orderSummary);
    }

    private int assignTableNumber(int numMeals) {
        // Table assignment logic
        // Replace this with your own logic to assign a table number based on the number of meals
        // You can use a simple calculation or a more complex algorithm based on your requirements
        // For example, you can divide the number of meals by a constant and add an offset

        int tableNumber = (numMeals % 5) + 1; // Simple calculation: Assign tables 1-5 cyclically

        return tableNumber;
    }

    private String generateOrderSummary() {
        // Generate the order summary based on the cart items
        // You can customize this method to display the order details in the desired format

        StringBuilder sb = new StringBuilder();

        sb.append("Table Number: ").append(tableNumber).append("\n\n");
        sb.append("Order Summary:\n");

        for (String item : cartItems) {
            sb.append("- ").append(item).append("\n");
        }

        return sb.toString();
    }

    public void confirmOrder(View view) {
        EditText paymentDetailsEditText = findViewById(R.id.payment_details_edit_text);
        String paymentDetails = paymentDetailsEditText.getText().toString().trim();

        // Perform payment processing here
        // You can integrate a payment gateway or any other payment system of your choice
        // Handle the payment result and order confirmation accordingly

        // For this example, let's assume the payment is successful and the order is confirmed
        showOrderConfirmation();
    }

    private void showOrderConfirmation() {
        // Show a toast message or navigate to a confirmation screen
        // to indicate that the order is confirmed

        Toast.makeText(this, "Order confirmed!", Toast.LENGTH_SHORT).show();
        // Alternatively, you can start a new activity to display the order confirmation details
        // Intent intent = new Intent(this, OrderConfirmationActivity.class);
        // startActivity(intent);
    }
}
