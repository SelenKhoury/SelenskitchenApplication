package com.example.selenskitchenapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class CartItem implements Parcelable {
    private String name;
    private double price;
    private String description;

    public CartItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    protected CartItem(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        description = in.readString();
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

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(description);
    }
}
