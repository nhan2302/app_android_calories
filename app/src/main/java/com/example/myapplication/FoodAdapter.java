package com.example.myapplication;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<FoodItem> foodList;
    private List<FoodItem> foodListFull; // Full list for filtering
    private List<FoodItem> selectedItems = new ArrayList<>(); // List of selected items
    private OnFoodClickListener onFoodClickListener;
    private OnSelectedFoodItemsChangedListener onSelectedFoodItemsChangedListener;

    // Interface for click listener
    public interface OnFoodClickListener {
        void onFoodClick(FoodItem foodItem);
    }

    public interface OnSelectedFoodItemsChangedListener {
        void onSelectedItemsChanged(List<FoodItem> selectedItems);
    }

    // Constructor for FoodAdapter
    public FoodAdapter(List<FoodItem> foodList, OnFoodClickListener onFoodClickListener, OnSelectedFoodItemsChangedListener onSelectedFoodItemsChangedListener) {
        this.foodList = new ArrayList<>(foodList);
        this.foodListFull = new ArrayList<>(foodList); // Keep a full copy for filtering
        this.onFoodClickListener = onFoodClickListener;
        this.onSelectedFoodItemsChangedListener = onSelectedFoodItemsChangedListener;
    }

    // Method to filter food items based on a search keyword
    public void filter(String keyword) {
        foodList.clear();
        if (keyword.isEmpty()) {
            foodList.addAll(foodListFull);
        } else {
            for (FoodItem item : foodListFull) {
                if (item.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    foodList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem foodItem = foodList.get(position);
        holder.bind(foodItem);

        // Set background color based on selection
        holder.itemView.setBackgroundColor(selectedItems.contains(foodItem) ? Color.LTGRAY : Color.WHITE);

        // Set click listener
        holder.itemView.setOnClickListener(v -> {
            toggleSelection(foodItem);
            onFoodClickListener.onFoodClick(foodItem); // Notify on click
            onSelectedFoodItemsChangedListener.onSelectedItemsChanged(getSelectedItems()); // Notify selection change
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    // Toggle selection of an item
    public void toggleSelection(FoodItem foodItem) {
        if (selectedItems.contains(foodItem)) {
            selectedItems.remove(foodItem);
        } else {
            selectedItems.add(foodItem);
        }
        notifyDataSetChanged(); // Refresh the adapter when selection changes
    }

    // Return selected items
    public List<FoodItem> getSelectedItems() {
        return new ArrayList<>(selectedItems);
    }

    // Update the food list with new data
    public void updateData(List<FoodItem> newFoodList) {
        foodList.clear();
        foodList.addAll(newFoodList);
        foodListFull.clear();
        foodListFull.addAll(newFoodList);
        notifyDataSetChanged(); // Refresh the adapter with new data
    }

    // Remove items from the list
    public void removeItems(List<FoodItem> itemsToRemove) {
        foodList.removeAll(itemsToRemove);
        foodListFull.removeAll(itemsToRemove); // Ensure both lists are synced
        selectedItems.clear(); // Deselect items after removal
        notifyDataSetChanged(); // Refresh the adapter
    }

    // ViewHolder for each food item
    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewCalories;
        ImageView imageViewFood;


        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewFoodName);
            textViewCalories = itemView.findViewById(R.id.textViewFoodCalories);
            imageViewFood = itemView.findViewById(R.id.food_image);
        }

        public void bind(FoodItem foodItem) {
            textViewName.setText(foodItem.getName());
            textViewCalories.setText("Calories: " + foodItem.getCalories() + " kcal");
            imageViewFood.setImageResource(foodItem.getImageResId());

        }
    }
}
