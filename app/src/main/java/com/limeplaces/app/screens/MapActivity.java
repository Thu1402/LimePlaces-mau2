package com.limeplaces.app.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.limeplaces.app.R;
import com.limeplaces.app.data.AppData;
import com.limeplaces.app.ui.PlaceCardView;
import com.limeplaces.app.ui.Ui;

public class MapActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_map);
        FrameLayout root = findViewById(R.id.map_root);
        root.addView(new MapCanvasView(this), 0, new FrameLayout.LayoutParams(-1, -1));

        TextInputLayout search = Ui.input(this, "Bạn muốn đi đâu?", false);
        FrameLayout.LayoutParams searchParams = new FrameLayout.LayoutParams(-1, Ui.dp(this, 58));
        searchParams.setMargins(Ui.dp(this, 16), Ui.dp(this, 24), Ui.dp(this, 16), 0);
        root.addView(search, searchParams);

        LinearLayout results = new LinearLayout(this);
        results.setOrientation(LinearLayout.VERTICAL);
        Ui.setPadding(results, 16, 14);
        results.setBackground(Ui.background(Ui.color(this, R.color.cream), 24, this));
        results.addView(Ui.text(this, "12 địa điểm quanh đây", 12, Ui.color(this, R.color.ink), true));
        PlaceCardView card = new PlaceCardView(this, AppData.PLACES.get(0), true,
            view -> startActivity(new android.content.Intent(this, PlaceDetailActivity.class).putExtra(PlaceDetailActivity.PLACE_INDEX, 0)));
        results.addView(card, Ui.margin(-1, -2, 0, 12, 0, 0, this));
        View add = Ui.softButton(this, "+  Thêm địa điểm tại vị trí này");
        add.setOnClickListener(view -> startActivity(new android.content.Intent(this, AddPlaceActivity.class)));
        results.addView(add, Ui.margin(-1, 48, 0, 10, 0, 0, this));
        FrameLayout.LayoutParams resultParams = new FrameLayout.LayoutParams(-1, Ui.dp(this, 220));
        resultParams.gravity = android.view.Gravity.BOTTOM;
        root.addView(results, resultParams);
    }
}
