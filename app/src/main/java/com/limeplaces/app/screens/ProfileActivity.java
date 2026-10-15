package com.limeplaces.app.screens;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.limeplaces.app.R;
import com.limeplaces.app.ui.Ui;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_profile);
        View root = findViewById(android.R.id.content);
        LinearLayout page = root.findViewById(R.id.profile_content);

        LinearLayout header = Ui.row(this);
        header.addView(Ui.header(this, "Không gian của bạn", "Hồ sơ"), new LinearLayout.LayoutParams(0, -2, 1));
        MaterialButton settings = Ui.softButton(this, "Thiết lập");
        header.addView(settings, Ui.margin(100, 44, 0, 0, 0, 0, this));
        page.addView(header);

        LinearLayout profile = new LinearLayout(this);
        profile.setGravity(Gravity.CENTER);
        profile.setOrientation(LinearLayout.VERTICAL);
        profile.setBackground(Ui.background(Ui.color(this, R.color.lime), 24, this));
        Ui.setPadding(profile, 20, 22);
        TextView avatar = Ui.text(this, "KL", 22, Ui.color(this, R.color.violet), true);
        avatar.setGravity(Gravity.CENTER);
        avatar.setBackground(Ui.background(Ui.color(this, R.color.violet_soft), 28, this));
        profile.addView(avatar, Ui.margin(76, 76, 0, 0, 0, 12, this));
        profile.addView(Ui.text(this, "Khánh Linh", 20, Ui.color(this, R.color.ink), true));
        profile.addView(Ui.text(this, "@khanhlinh · Đà Lạt", 10, Ui.color(this, R.color.muted), false));
        profile.addView(Ui.softButton(this, "Chỉnh sửa hồ sơ"), Ui.margin(160, 44, 0, 14, 0, 17, this));
        profile.addView(stats());
        page.addView(profile, Ui.margin(-1, -2, 0, 20, 0, 27, this));

        page.addView(Ui.text(this, "Bộ sưu tập của bạn", 17, Ui.color(this, R.color.ink), true));
        TextView collection = Ui.text(this, "♡   Những nơi muốn trở lại\n      8 địa điểm", 13, Ui.color(this, R.color.ink), true);
        collection.setGravity(Gravity.CENTER_VERTICAL);
        collection.setBackground(Ui.borderedBackground(Ui.color(this, R.color.surface), Ui.color(this, R.color.line), 18, this));
        Ui.setPadding(collection, 16, 16);
        page.addView(collection, Ui.margin(-1, 84, 0, 12, 0, 26, this));

        page.addView(Ui.text(this, "Hoạt động gần đây", 17, Ui.color(this, R.color.ink), true));
        page.addView(activity("Đã lưu Nép Coffee", "2 giờ trước"));
        page.addView(activity("Đã chia sẻ một nơi với Minh Anh", "Hôm qua"));
        page.addView(activity("Đã thêm Hiên Nhà Vàng", "3 ngày trước"));

        settings.setOnClickListener(view -> showSettings());
    }

    private LinearLayout stats() {
        LinearLayout row = Ui.row(this);
        String[][] values = {{"36", "Đã lưu"}, {"12", "Đã thêm"}, {"24", "Bạn bè"}};
        for (String[] value : values) {
            LinearLayout item = new LinearLayout(this);
            item.setGravity(Gravity.CENTER);
            item.setOrientation(LinearLayout.VERTICAL);
            item.addView(Ui.text(this, value[0], 17, Ui.color(this, R.color.ink), true));
            item.addView(Ui.text(this, value[1], 8, Ui.color(this, R.color.muted), false));
            row.addView(item, new LinearLayout.LayoutParams(0, -2, 1));
        }
        return row;
    }

    private View activity(String title, String time) {
        LinearLayout item = new LinearLayout(this);
        item.setOrientation(LinearLayout.VERTICAL);
        item.setPadding(0, Ui.dp(this, 12), 0, Ui.dp(this, 12));
        item.addView(Ui.text(this, title, 11, Ui.color(this, R.color.ink), true));
        item.addView(Ui.text(this, time, 8, Ui.color(this, R.color.muted), false));
        return item;
    }

    private void showSettings() {
        String[] options = {"Thông tin cá nhân", "Thông báo", "Quyền riêng tư", "Đăng xuất", "Xóa tài khoản"};
        new AlertDialog.Builder(this)
            .setTitle("Thiết lập tài khoản")
            .setItems(options, (dialog, which) -> {
                if (which == 4) confirmDelete();
                else Toast.makeText(this, options[which], Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton("Đóng", null)
            .show();
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
            .setTitle("Xóa tài khoản?")
            .setMessage("Tất cả địa điểm đã lưu, bộ sưu tập và kết nối bạn bè sẽ bị xóa vĩnh viễn. Hành động này không thể hoàn tác.")
            .setPositiveButton("Xóa tài khoản", (dialog, which) ->
                Toast.makeText(this, "Yêu cầu xóa tài khoản đã được ghi nhận", Toast.LENGTH_LONG).show())
            .setNegativeButton("Giữ lại tài khoản", null)
            .show();
    }
}
