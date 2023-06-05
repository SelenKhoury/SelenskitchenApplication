package com.example.selenskitchenapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class CartItem implements Parcelable {

    private String foodItem;

    public CartItem(String foodItem) {
        this.foodItem = foodItem;
    }

    protected CartItem(Parcel in) {
        foodItem = in.readString();
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(foodItem);
    }
}
