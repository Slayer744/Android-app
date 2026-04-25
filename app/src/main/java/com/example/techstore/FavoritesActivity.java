package com.example.techstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FavoritesActivity extends AppCompatActivity {

    RecyclerView recyclerFavorites;
    LinearLayout emptyFavoritesLayout;
    BottomNavigationView bottomNavFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Toolbar toolbarFavorites = findViewById(R.id.toolbarFavorites);
        setSupportActionBar(toolbarFavorites);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        recyclerFavorites = findViewById(R.id.recyclerFavorites);
        emptyFavoritesLayout = findViewById(R.id.emptyFavoritesLayout);
        bottomNavFavorites = findViewById(R.id.bottomNavFavorites);

        recyclerFavorites.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerFavorites.setAdapter(new FavoriteAdapter(this, DataManager.favoriteItems, this::updateFavoritesUI));

        updateFavoritesUI();

        bottomNavFavorites.setSelectedItemId(R.id.nav_favorites);
        bottomNavFavorites.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (id == R.id.nav_cart) {
                startActivity(new Intent(this, CartActivity.class));
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return true;
        });
    }

    private void updateFavoritesUI() {
        if (DataManager.favoriteItems.isEmpty()) {
            emptyFavoritesLayout.setVisibility(android.view.View.VISIBLE);
            recyclerFavorites.setVisibility(android.view.View.GONE);
        } else {
            emptyFavoritesLayout.setVisibility(android.view.View.GONE);
            recyclerFavorites.setVisibility(android.view.View.VISIBLE);
        }

        if (recyclerFavorites.getAdapter() != null) {
            recyclerFavorites.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateFavoritesUI();
    }
}