package com.example.selenskitchenapplication;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private List<String> cartItems;

    public CartManager() {
        cartItems = new ArrayList<>();
    }

    public void addToCart(String item) {
        cartItems.add(item);
    }

    public List<String> getCartItems() {
        return cartItems;
    }
}
