package com.example.myapplication;
import java.util.ArrayList;
import java.util.List;
public class FoodData {
    public static List<FoodItem> getFoodList() {

            List<FoodItem> foodList = new ArrayList<>();
            foodList.add(new FoodItem("Cơm trắng 100g", 130, R.drawable.com, "Nấu cơm trong nồi với lượng nước vừa đủ cho 100g gạo."));
            foodList.add(new FoodItem("Cơm Gạo lứt 100g", 110, R.drawable.gaolut, "Ngâm gạo lứt 2-3 giờ, sau đó nấu với lượng nước nhiều hơn gạo trắng."));
            foodList.add(new FoodItem("Bánh mì 100g", 265, R.drawable.banh_mi, "Bánh mì có thể ăn kèm với trứng, thịt nguội, rau và nước sốt tùy thích."));
            foodList.add(new FoodItem("Trứng 100g", 70, R.drawable.trung, "Luộc trứng 6-8 phút cho trứng lòng đào, 10-12 phút cho trứng chín hoàn toàn."));
            foodList.add(new FoodItem("Thịt bò 100g", 250, R.drawable.thit_bo, "Nướng hoặc xào thịt bò với tỏi và rau củ. Nêm thêm muối, tiêu vừa ăn."));
            foodList.add(new FoodItem("Thịt gà 100g", 200, R.drawable.thit_ga, "Nướng, luộc hoặc xào thịt gà với hành và gia vị tùy thích."));
            foodList.add(new FoodItem("Cá hồi 100g", 200, R.drawable.ca_hoi, "Nướng cá hồi với một ít dầu olive, muối, tiêu và chanh cho đậm vị."));
            foodList.add(new FoodItem("Sữa chua 100g", 150, R.drawable.sua_chua, "Sữa chua có thể ăn trực tiếp hoặc kết hợp với trái cây và mật ong."));
            foodList.add(new FoodItem("Táo 100g", 52, R.drawable.tao, "Ăn táo trực tiếp hoặc dùng làm salad trái cây với các loại quả khác."));
            foodList.add(new FoodItem("Chuối 100g", 89, R.drawable.chuoi, "Chuối có thể ăn trực tiếp, dùng trong sinh tố hoặc làm bánh."));
            foodList.add(new FoodItem("Tôm 100g", 99, R.drawable.tom, "Luộc, hấp hoặc xào tôm với tỏi và rau củ. Thêm nước mắm và tiêu."));
            foodList.add(new FoodItem("Cá ngừ 100g", 132, R.drawable.banh_mi, "Nướng cá ngừ với tỏi, muối, và chanh để giữ hương vị thơm ngon."));
            foodList.add(new FoodItem("Bí đỏ 100g", 26, R.drawable.bi_do, "Nấu bí đỏ với một ít nước hoặc hấp chín. Dùng làm canh hoặc súp."));
            foodList.add(new FoodItem("Bắp (ngô) 100g", 96, R.drawable.bap, "Luộc bắp với muối hoặc nướng bắp cho thơm và ngọt."));
            foodList.add(new FoodItem("Rau cải xanh 100g", 16, R.drawable.rau_cai, "Xào rau cải với tỏi hoặc luộc chấm nước mắm."));
            foodList.add(new FoodItem("Bông cải xanh 100g", 34, R.drawable.bong_cai, "Luộc hoặc xào bông cải với thịt bò hoặc tỏi."));
            foodList.add(new FoodItem("Cà rốt 100g", 41, R.drawable.ca_rot, "Luộc, xào hoặc làm salad cà rốt trộn với nước cốt chanh và muối."));
            foodList.add(new FoodItem("Cam 100g", 47, R.drawable.cam, "Cam có thể ăn trực tiếp hoặc ép lấy nước."));
            foodList.add(new FoodItem("Nho 100g", 69, R.drawable.nho, "Nho có thể ăn trực tiếp hoặc thêm vào salad trái cây."));


            // Thêm hình ảnh và dữ liệu cho các món ăn khác...
            return foodList;

    }
}
