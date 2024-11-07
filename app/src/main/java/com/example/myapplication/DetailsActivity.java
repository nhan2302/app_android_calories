package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private RecyclerView detailsRecyclerView;
    private FoodAdapter foodAdapter;
    private List<FoodItem> selectedFoods;
    private TextView cookingSuggestionsTextView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        selectedFoods = getIntent().getParcelableArrayListExtra("selectedFoods");
        if (selectedFoods == null) {
            selectedFoods = new ArrayList<>();
        }

        Log.d("DetailsActivity", "Received selected foods size: " + selectedFoods.size());
        detailsRecyclerView = findViewById(R.id.detailsRecyclerView);
        cookingSuggestionsTextView = findViewById(R.id.cookingSuggestionsTextView);

        selectedFoods = getIntent().getParcelableArrayListExtra("selectedFoods");
        if (selectedFoods == null) {
            selectedFoods = new ArrayList<>();
        }

        foodAdapter = new FoodAdapter(selectedFoods, foodItem -> {
            Log.d("DetailsActivity", "Selected Food Item: " + foodItem.getName());
            cookingSuggestionsTextView.setText("Gợi ý cách nấu: " + foodItem.getCookingInstructions());
        }, selectedFoodItems -> {
            Log.d("DetailsActivity", "Số món ăn đã chọn: " + selectedFoodItems.size());
        });

        detailsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailsRecyclerView.setAdapter(foodAdapter);
    }
}
