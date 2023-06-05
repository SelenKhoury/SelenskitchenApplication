package com.example.selenskitchenapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CartAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> cartItems;

    public CartAdapter(Context context, List<String> cartItems) {
        super(context, 0, cartItems);
        this.context = context;
        this.cartItems = cartItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String cartItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        }

        TextView cartItemTextView = convertView.findViewById(R.id.cart_item_text_view);
        cartItemTextView.setText(cartItem);

        return convertView;
    }
}
