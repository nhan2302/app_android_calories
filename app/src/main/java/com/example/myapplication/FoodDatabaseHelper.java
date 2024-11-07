package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class FoodDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "foot_data_nhan";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FOODS = "foods";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CALORIES = "calories";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_GOIY = "goiy";

    public FoodDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FOODS_TABLE = "CREATE TABLE " + TABLE_FOODS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_CALORIES + " INTEGER,"
                + COLUMN_IMAGE + " BLOB,"
                + COLUMN_GOIY + " TEXT" + ")"; // Thêm COLUMN_GOIY vào đây
        db.execSQL(CREATE_FOODS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODS);
        onCreate(db);
    }

    public void addFood(FoodItem foodItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, foodItem.getName());
        values.put(COLUMN_CALORIES, foodItem.getCalories());
        values.put(COLUMN_IMAGE, foodItem.getImageResId());
        values.put(COLUMN_GOIY, foodItem.getCookingInstructions());
        db.insert(TABLE_FOODS, null, values);
        db.close();
    }

    public List<FoodItem> getAllFoods() {
        List<FoodItem> foodList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_FOODS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") int calories = cursor.getInt(cursor.getColumnIndex(COLUMN_CALORIES));
                @SuppressLint("Range") int imageResId = cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE));
                @SuppressLint("Range") String goiy = cursor.getString(cursor.getColumnIndex(COLUMN_GOIY));
                foodList.add(new FoodItem(name, calories, imageResId, goiy));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foodList;
    }

    public void deleteFood(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOODS, COLUMN_NAME + " = ?", new String[]{name});
        db.close();
    }

    public void initializeDefaultData() {
        if (getAllFoods().isEmpty()) {
            List<FoodItem> defaultFoodList = FoodData.getFoodList();
            for (FoodItem foodItem : defaultFoodList) {
                addFood(foodItem);
            }
        }
    }

}
