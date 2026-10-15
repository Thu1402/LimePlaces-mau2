package com.limeplaces.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.limeplaces.app.screens.*;

public class MainActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            Class<?> target = id == R.id.nav_home ? HomeActivity.class :
                id == R.id.nav_map ? MapActivity.class :
                id == R.id.nav_add ? AddPlaceActivity.class :
                id == R.id.nav_friends ? FriendsActivity.class : ProfileActivity.class;
            startActivity(new Intent(this, target));
            return true;
        });
        navigation.setSelectedItemId(R.id.nav_home);
    }
}
