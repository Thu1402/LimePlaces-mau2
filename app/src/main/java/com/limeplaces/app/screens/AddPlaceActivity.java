package com.limeplaces.app.screens;

import android.graphics.Color;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.limeplaces.app.R;
import com.limeplaces.app.ui.Ui;

public class AddPlaceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_add_place);
        View root = findViewById(android.R.id.content);
        LinearLayout page = root.findViewById(R.id.add_place_content);
        page.addView(Ui.header(this, "Địa điểm mới", "Chia sẻ một nơi hay"));

        TextView imagePicker = Ui.text(this, "▧\n\nThêm hình ảnh\nChọn từ thư viện hoặc chụp ảnh mới", 12, Ui.color(this, R.color.violet), true);
        imagePicker.setGravity(Gravity.CENTER);
        imagePicker.setBackground(Ui.borderedBackground(Ui.color(this, R.color.surface), Ui.color(this, R.color.line), 22, this));
        imagePicker.setOnClickListener(view -> Toast.makeText(this, "Mở thư viện ảnh hoặc máy ảnh", Toast.LENGTH_SHORT).show());
        page.addView(imagePicker, Ui.margin(-1, 185, 0, 22, 0, 24, this));

        page.addView(Ui.input(this, "Tên địa điểm", false), Ui.margin(-1, -2, 0, 0, 0, 14, this));
        page.addView(Ui.input(this, "Mô tả địa điểm", true), Ui.margin(-1, -2, 0, 0, 0, 18, this));

        MaterialButton location = Ui.softButton(this, "⌖  Chọn vị trí trên bản đồ");
        page.addView(location, Ui.margin(-1, 52, 0, 0, 0, 22, this));

        page.addView(Ui.text(this, "MÀU NHẬN DIỆN", 10, Ui.color(this, R.color.muted), true));
        LinearLayout colors = Ui.row(this);
        int[] values = {R.color.lime_strong, R.color.violet, R.color.peach, R.color.mint, R.color.danger};
        for (int value : values) {
            View dot = new View(this);
            dot.setBackground(Ui.background(Ui.color(this, value), 22, this));
            colors.addView(dot, Ui.margin(38, 38, 0, 12, 13, 0, this));
        }
        page.addView(colors);

        MaterialButton save = Ui.primaryButton(this, "Lưu địa điểm");
        save.setOnClickListener(view -> {
            save.setText("Đã lưu địa điểm");
            save.setBackgroundColor(Color.rgb(103, 136, 53));
            Toast.makeText(this, "Địa điểm mới đã được cập nhật", Toast.LENGTH_SHORT).show();
            view.postDelayed(() -> {
                Intent intent = new Intent(this, com.limeplaces.app.MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }, 700);
        });
        page.addView(save, Ui.margin(-1, 54, 0, 26, 0, 0, this));
    }
}
