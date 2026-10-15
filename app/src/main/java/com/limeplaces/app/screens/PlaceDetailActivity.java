package com.limeplaces.app.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.limeplaces.app.R;
import com.limeplaces.app.data.AppData;
import com.limeplaces.app.model.Place;
import com.limeplaces.app.ui.Ui;

public class PlaceDetailActivity extends AppCompatActivity {
    public static final String PLACE_INDEX = "place_index";
    private boolean saved;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        int index = getIntent().getIntExtra(PLACE_INDEX, 0);
        Place place = AppData.PLACES.get(Math.min(index, AppData.PLACES.size() - 1));

        setContentView(R.layout.activity_place_detail);
        View root = findViewById(android.R.id.content);
        LinearLayout page = root.findViewById(R.id.detail_content);

        ImageView hero = new ImageView(this);
        hero.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(this).load(place.imageUrl).centerCrop().into(hero);
        page.addView(hero, new LinearLayout.LayoutParams(-1, Ui.dp(this, 340)));

        LinearLayout body = new LinearLayout(this);
        body.setOrientation(LinearLayout.VERTICAL);
        body.setPadding(Ui.dp(this, 20), Ui.dp(this, 22), Ui.dp(this, 20), Ui.dp(this, 20));
        TextView back = Ui.text(this, "‹  Quay lại", 11, Ui.color(this, R.color.violet), true);
        back.setOnClickListener(view -> finish());
        body.addView(back);
        body.addView(Ui.text(this, place.category.toUpperCase(), 10, Ui.color(this, R.color.violet), true), Ui.margin(-1, -2, 0, 20, 0, 0, this));
        body.addView(Ui.text(this, place.name, 28, Ui.color(this, R.color.ink), true), Ui.margin(-1, -2, 0, 5, 0, 0, this));
        body.addView(Ui.text(this, "⌖  " + place.address + "  ·  " + place.distance, 10, Ui.color(this, R.color.muted), false), Ui.margin(-1, -2, 0, 7, 0, 18, this));

        LinearLayout actions = Ui.row(this);
        actions.setGravity(Gravity.CENTER);
        MaterialButton save = actionButton("Lưu");
        save.setOnClickListener(view -> {
            saved = !saved;
            save.setText(saved ? "Đã lưu" : "Lưu");
            Toast.makeText(this, saved ? "Đã lưu địa điểm" : "Đã bỏ lưu", Toast.LENGTH_SHORT).show();
        });
        MaterialButton share = actionButton("Chia sẻ");
        share.setOnClickListener(view -> showShareDialog(place));
        MaterialButton route = actionButton("Dẫn đường");
        route.setOnClickListener(view -> {
            Uri location = Uri.parse("geo:0,0?q=" + Uri.encode(place.name + ", " + place.address));
            startActivity(new Intent(Intent.ACTION_VIEW, location));
        });
        MaterialButton edit = actionButton("Chỉnh sửa");
        edit.setOnClickListener(view -> Toast.makeText(this, "Bạn có quyền chỉnh sửa địa điểm này", Toast.LENGTH_SHORT).show());
        actions.addView(save, weighted());
        actions.addView(share, weighted());
        actions.addView(route, weighted());
        actions.addView(edit, weighted());
        body.addView(actions);

        body.addView(Ui.text(this, "Về địa điểm này", 17, Ui.color(this, R.color.ink), true), Ui.margin(-1, -2, 0, 28, 0, 0, this));
        body.addView(Ui.text(this, "Một khoảng hồ xanh trong veo ẩn mình giữa đồi thông, lý tưởng để đi dạo, ngắm hoàng hôn và dành một buổi chiều thật chậm.", 12, Ui.color(this, R.color.muted), false), Ui.margin(-1, -2, 0, 9, 0, 0, this));

        TextView map = Ui.text(this, "VỊ TRÍ\n\nĐồi Thông, Đà Lạt\nChạm để mở bản đồ", 12, Ui.color(this, R.color.ink), true);
        map.setGravity(Gravity.CENTER);
        map.setBackground(Ui.background(Ui.color(this, R.color.mint), 20, this));
        map.setOnClickListener(view -> route.performClick());
        body.addView(map, Ui.margin(-1, 150, 0, 24, 0, 0, this));
        page.addView(body);
    }

    private MaterialButton actionButton(String label) {
        MaterialButton button = Ui.softButton(this, label);
        button.setInsetTop(2);
        button.setMinHeight(2);
        return button;
    }

    private LinearLayout.LayoutParams weighted() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, Ui.dp(this, 48), 1);
        params.setMargins(Ui.dp(this, 3), 0, Ui.dp(this, 3), 0);
        return params;
    }

    private void showShareDialog(Place place) {
        String[] people = {"Minh Anh", "Quang Huy", "Thảo Vy", "Gia Linh"};
        boolean[] checked = {true, false, false, false};
        new AlertDialog.Builder(this)
            .setTitle("Chia sẻ " + place.name)
            .setMultiChoiceItems(people, checked, (dialog, which, isChecked) -> checked[which] = isChecked)
            .setPositiveButton("Chia sẻ", (dialog, which) ->
                Toast.makeText(this, "Đã gửi địa điểm cho bạn bè", Toast.LENGTH_SHORT).show())
            .setNegativeButton("Hủy", null)
            .show();
    }
}
