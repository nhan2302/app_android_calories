package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerViewFoodList;
    private FoodAdapter adapter;
    private FoodDatabaseHelper dbHelper;
    private CaloriesPieChartView caloriesPieChartView;
    private Button buttonAddFood;
    private Button buttonDelete;
    private Button detailsButton;
    private SearchView searchViewFood;
    private static final int MAX_CALORIES = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        searchViewFood = findViewById(R.id.searchViewFood);
        caloriesPieChartView = findViewById(R.id.caloriesPieChartView);
        caloriesPieChartView.setCalories(0, MAX_CALORIES);

        recyclerViewFoodList = findViewById(R.id.recyclerViewFoodList);
        recyclerViewFoodList.setLayoutManager(new LinearLayoutManager(this));

        // Initialize database helper and default data
        dbHelper = new FoodDatabaseHelper(this);
        dbHelper.initializeDefaultData();

        // Fetch data from the database
        List<FoodItem> foodList = dbHelper.getAllFoods();

        // Initialize FoodAdapter with foodList and listeners
        adapter = new FoodAdapter(foodList, foodItem -> {
            // Handle individual food item click
        }, selectedFoodItems -> {
            int totalCalories = calculateTotalCalories(selectedFoodItems);
            caloriesPieChartView.setCalories(totalCalories, MAX_CALORIES);
        });

        recyclerViewFoodList.setAdapter(adapter);

        // Search functionality
        searchViewFood.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });

        // Initialize other buttons and set listeners
        buttonAddFood = findViewById(R.id.buttonAddFood);
        buttonAddFood.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddFoodActivity.class);
            startActivity(intent);
        });

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this::reloadFoodList);

        buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(v -> deleteSelectedFoods());

        detailsButton = findViewById(R.id.detailsButton);
        detailsButton.setOnClickListener(view -> {
            List<FoodItem> selectedFoods = adapter.getSelectedItems(); // Lấy danh sách đã chọn từ adapter
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putParcelableArrayListExtra("selectedFoods", new ArrayList<>(selectedFoods));
            startActivity(intent);
        });
    }

    // Calculate the total calories of selected items
    private int calculateTotalCalories(List<FoodItem> selectedFoodItems) {
        int total = 0;
        for (FoodItem item : selectedFoodItems) {
            total += item.getCalories();
        }
        return total;
    }

    // Reload the food list
    private void reloadFoodList() {
        List<FoodItem> updatedFoodList = dbHelper.getAllFoods();
        adapter.updateData(updatedFoodList);

        int totalCalories = calculateTotalCalories(adapter.getSelectedItems());
        caloriesPieChartView.setCalories(totalCalories, MAX_CALORIES);

        swipeRefreshLayout.setRefreshing(false);
    }

    // Delete selected food items from the database and adapter
    private void deleteSelectedFoods() {
        List<FoodItem> selectedItems = adapter.getSelectedItems();

        if (selectedItems == null || selectedItems.isEmpty()) {
            Toast.makeText(this, "Bạn chưa chọn món ăn cần xóa", Toast.LENGTH_SHORT).show();
            return;
        }

        for (FoodItem item : selectedItems) {
            dbHelper.deleteFood(item.getName());
        }

        adapter.removeItems(selectedItems);

        caloriesPieChartView.setCalories(0, MAX_CALORIES);
        int totalCalories = calculateTotalCalories(adapter.getSelectedItems());
        caloriesPieChartView.setCalories(totalCalories, MAX_CALORIES);

        Toast.makeText(this, "Đã xóa món ăn được chọn", Toast.LENGTH_SHORT).show();
    }
}
