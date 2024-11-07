package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodItem implements Parcelable {
    private String name;
    private int calories;
    private int imageResId;
    private String cookingInstructions; // Gợi ý cách nấu

    public FoodItem(String name, int calories, int imageResId, String cookingInstructions) {
        this.name = name;
        this.calories = calories;
        this.imageResId = imageResId;
        this.cookingInstructions = cookingInstructions;
    }

    protected FoodItem(Parcel in) {
        name = in.readString();
        calories = in.readInt();
        imageResId = in.readInt();
        cookingInstructions = in.readString(); // Đọc gợi ý cách nấu từ Parcel
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(calories);
        dest.writeInt(imageResId);
        dest.writeString(cookingInstructions); // Ghi gợi ý cách nấu vào Parcel
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getCookingInstructions() {
        return cookingInstructions;
    }
}
