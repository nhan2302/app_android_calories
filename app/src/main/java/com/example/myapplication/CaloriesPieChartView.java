package com.example.myapplication;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
public class CaloriesPieChartView extends View {
    private Paint paintConsumed;
    private Paint paintRemaining;
    private Paint paintText;
    private int maxCalories = 2000;
    private int consumedCalories = 0;

    public CaloriesPieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paintConsumed = new Paint();
        paintConsumed.setColor(Color.parseColor("#FF6347")); // Màu phần đã tiêu thụ
        paintConsumed.setStyle(Paint.Style.FILL);

        paintRemaining = new Paint();
        paintRemaining.setColor(Color.parseColor("#E0E0E0")); // Màu phần còn lại
        paintRemaining.setStyle(Paint.Style.FILL);

        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(50);
        paintText.setTextAlign(Paint.Align.CENTER);
    }

    public void setCalories(int consumedCalories, int maxCalories) {
        this.consumedCalories = consumedCalories;
        this.maxCalories = maxCalories;
        invalidate(); // Yêu cầu vẽ lại View
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();
        float radius = Math.min(width, height) / 2;

        // Tính toán góc phần đã tiêu thụ
        float angleConsumed = 360f * consumedCalories / maxCalories;

        // Vẽ phần còn lại
        canvas.drawCircle(width / 2, height / 2, radius, paintRemaining);

        // Vẽ phần đã tiêu thụ
        canvas.drawArc(
                width / 2 - radius,
                height / 2 - radius,
                width / 2 + radius,
                height / 2 + radius,
                -90, // Bắt đầu từ trên cùng
                angleConsumed,
                true,
                paintConsumed
        );

        // Vẽ tổng calo ở trung tâm biểu đồ
        canvas.drawText(consumedCalories + " / " + maxCalories + " kcal", width / 2, height / 2, paintText);
    }
}