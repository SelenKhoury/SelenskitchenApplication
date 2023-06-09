package com.example.selenskitchenapplication;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static List<String> cartItems = new ArrayList<>();

    public void addToCart(String item) {
        cartItems.add(item);
    }

    public static List<String> getCartItems() {
        return cartItems;
    }

    public static void clearCart() {
        cartItems.clear();
    }
}
