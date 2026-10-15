package com.limeplaces.app.screens;

import android.os.Bundle;
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
import com.limeplaces.app.data.AppData;
import com.limeplaces.app.ui.Ui;

public class FriendsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_friends);
        View root = findViewById(android.R.id.content);
        LinearLayout page = root.findViewById(R.id.friends_content);
        page.addView(Ui.header(this, "Cùng khám phá", "Bạn bè"));
        page.addView(Ui.input(this, "Tìm tên hoặc @username", false), Ui.margin(-1, -2, 0, 22, 0, 18, this));

        LinearLayout tabs = Ui.row(this);
        MaterialButton all = Ui.primaryButton(this, "Bạn bè  24");
        MaterialButton requests = Ui.softButton(this, "Lời mời  2");
        tabs.addView(all, new LinearLayout.LayoutParams(0, Ui.dp(this, 48), 1));
        LinearLayout.LayoutParams tabParams = new LinearLayout.LayoutParams(0, Ui.dp(this, 48), 1);
        tabParams.setMargins(Ui.dp(this, 8), 0, 0, 0);
        tabs.addView(requests, tabParams);
        page.addView(tabs);

        TextView section = Ui.text(this, "TẤT CẢ BẠN BÈ", 10, Ui.color(this, R.color.muted), true);
        page.addView(section, Ui.margin(-1, -2, 0, 26, 0, 8, this));
        LinearLayout list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);
        showFriends(list);
        page.addView(list);

        all.setOnClickListener(view -> showFriends(list));
        requests.setOnClickListener(view -> showRequests(list));
    }

    private void showFriends(LinearLayout list) {
        list.removeAllViews();
        for (String[] friend : AppData.FRIENDS) {
            LinearLayout row = friendRow(friend[0], friend[1], friend[3]);
            TextView more = Ui.text(this, "•••", 14, Ui.color(this, R.color.muted), true);
            row.addView(more);
            list.addView(row);
        }
    }

    private void showRequests(LinearLayout list) {
        list.removeAllViews();
        for (int index = 2; index < AppData.FRIENDS.length; index++) {
            String[] friend = AppData.FRIENDS[index];
            LinearLayout row = friendRow(friend[0], friend[1], "Muốn kết nối và chia sẻ địa điểm với bạn");
            MaterialButton accept = Ui.softButton(this, "Chấp nhận");
            accept.setOnClickListener(view -> {
                row.setVisibility(View.GONE);
                Toast.makeText(this, "Đã chấp nhận lời mời", Toast.LENGTH_SHORT).show();
            });
            row.addView(accept, Ui.margin(100, 44, 8, 0, 0, 0, this));
            list.addView(row);
        }
    }

    private LinearLayout friendRow(String initials, String name, String status) {
        LinearLayout row = Ui.row(this);
        row.setPadding(0, Ui.dp(this, 10), 0, Ui.dp(this, 10));
        TextView avatar = Ui.text(this, initials, 12, Ui.color(this, R.color.violet), true);
        avatar.setGravity(android.view.Gravity.CENTER);
        avatar.setBackground(Ui.background(Ui.color(this, R.color.violet_soft), 24, this));
        row.addView(avatar, Ui.margin(48, 48, 0, 0, 12, 0, this));
        LinearLayout info = new LinearLayout(this);
        info.setOrientation(LinearLayout.VERTICAL);
        info.addView(Ui.text(this, name, 12, Ui.color(this, R.color.ink), true));
        info.addView(Ui.text(this, status, 9, Ui.color(this, R.color.muted), false));
        row.addView(info, new LinearLayout.LayoutParams(0, -2, 1));
        return row;
    }
}
