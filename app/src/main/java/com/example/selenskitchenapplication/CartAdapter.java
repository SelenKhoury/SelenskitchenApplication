package com.example.selenskitchenapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CartAdapter extends ArrayAdapter<CartItem> {

    private Context context;
    private List<CartItem> cartItems;

    public CartAdapter(Context context, List<CartItem> cartItems) {
        super(context, R.layout.item_cart, cartItems);
        this.context = context;
        this.cartItems = cartItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.foodItemTextView = convertView.findViewById(R.id.item_name);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CartItem cartItem = cartItems.get(position);

        if (cartItem != null) {
            viewHolder.foodItemTextView.setText(cartItem.getFoodItem());
        }

        return convertView;
    }

    static class ViewHolder {
        TextView foodItemTextView;
    }
}