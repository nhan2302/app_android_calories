package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AddFoodActivity extends Activity {
    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editTextFoodName;
    private EditText editTextCalories;
    private ImageView imageViewFood;
    private Button buttonSelectImage;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        editTextFoodName = findViewById(R.id.editTextFoodName);
        editTextCalories = findViewById(R.id.editTextCalories);
        imageViewFood = findViewById(R.id.food_image);
        buttonSelectImage = findViewById(R.id.buttonSelectImage);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSelectImage.setOnClickListener(view -> openImageChooser());
        buttonSave.setOnClickListener(view -> saveFood());
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageViewFood.setImageURI(data.getData());
        }
    }

    private void saveFood() {
        String name = editTextFoodName.getText().toString();
        int calories;
        try {
            calories = Integer.parseInt(editTextCalories.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid calorie amount", Toast.LENGTH_SHORT).show();
            return;
        }

        Drawable drawable = imageViewFood.getDrawable();
        int imageResId = R.drawable.mac_dinh;

        if (drawable != null && drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            imageResId = R.drawable.mac_dinh;
        }

        // Đặt giá trị mặc định cho hướng dẫn nấu
        String cookingInstructions = "No instructions provided";

        // Tạo đối tượng FoodItem với hướng dẫn nấu mặc định
        FoodItem newFood = new FoodItem(name, calories, imageResId, cookingInstructions);
        FoodDatabaseHelper dbHelper = new FoodDatabaseHelper(this);
        dbHelper.addFood(newFood);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("newFoodName", name);
        resultIntent.putExtra("newFoodCalories", calories);
        resultIntent.putExtra("newFoodImageResId", imageResId);
        resultIntent.putExtra("newFoodImageResId", imageResId);
        setResult(RESULT_OK, resultIntent);
        finish();

        // Thông báo cho người dùng và kết thúc Activity
        Toast.makeText(this, "Món ăn được thêm thành công", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }



}