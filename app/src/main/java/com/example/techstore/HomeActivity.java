package com.example.techstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerProducts;
    EditText etSearch;
    ImageButton btnMenu;
    LinearLayout categoryContainer;
    BottomNavigationView bottomNav;

    List<Product> allProducts;
    List<Product> filteredProducts;
    ProductAdapter productAdapter;
    String selectedCategory = "All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        etSearch         = findViewById(R.id.etSearch);
        btnMenu          = findViewById(R.id.btnMenu);
        categoryContainer = findViewById(R.id.categoryContainer);
        recyclerProducts = findViewById(R.id.recyclerProducts);
        bottomNav        = findViewById(R.id.bottomNav);

        allProducts      = DataManager.getAllProducts();
        filteredProducts = new ArrayList<>(allProducts);

        productAdapter = new ProductAdapter(this, filteredProducts, product -> {
            Intent intent = new Intent(this, ProductDetailActivity.class);
            intent.putExtra("productId", product.getId());
            startActivity(intent);
        });

        recyclerProducts.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerProducts.setAdapter(productAdapter);

        setupCategories();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterProducts(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Menu 3 points
        btnMenu.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(this, v, Gravity.END);
            popup.getMenu().add(0, 1, 0, "À propos");
            popup.getMenu().add(0, 2, 1, "Informations");
            popup.getMenu().add(0, 3, 2, "Contact");

            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {

                    case 1: // À propos
                        new AlertDialog.Builder(this)
                                .setTitle("À propos de TechStore")
                                .setMessage(
                                        "Application développée par :\n\n" +
                                                "Bencharef Ramy\n" +
                                                "Nesrine Ferhati\n\n" +
                                                "Version 1.0\n" +
                                                "© 2026 TechStore. Tous droits réservés."
                                )
                                .setPositiveButton("Fermer", null)
                                .show();
                        return true;

                    case 2: // Informations
                        Toast.makeText(this,
                                "TechStore — Votre boutique tech en Algérie",
                                Toast.LENGTH_LONG).show();
                        return true;

                    case 3: // Contact
                        new AlertDialog.Builder(this)
                                .setTitle("Contact")
                                .setMessage(
                                        "Adresse : Boulevard Tella Ahcene, Cheraga\n\n" +
                                                "Tel : 0794081373\n\n" +
                                                "Facebook : facebook.com/TechStore\n\n" +
                                                "Instagram : instagram.com/slayer_ry"
                                )
                                .setPositiveButton("OK", null)
                                .show();
                        return true;
                }
                return false;
            });
            popup.show();
        });

        bottomNav.setSelectedItemId(R.id.nav_home);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_favorites) {
                startActivity(new Intent(this, FavoritesActivity.class));
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

    private void setupCategories() {
        categoryContainer.removeAllViews();
        String[] categories = {"All", "PC", "Phone", "Headphone", "Keyboard", "Mouse", "USB"};
        for (String category : categories) {
            Button btn = new Button(this);
            btn.setText(category);
            btn.setTextSize(13f);
            btn.setPadding(32, 8, 32, 8);

            boolean isSelected = category.equals(selectedCategory);
            btn.setTextColor(isSelected ? 0xFFFFFFFF : 0xFF0057B8);
            btn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(
                    isSelected ? 0xFF0057B8 : 0xFFE0EFFF));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 4, 8, 4);
            btn.setLayoutParams(params);

            btn.setOnClickListener(v -> {
                selectedCategory = category;
                setupCategories();
                filterProducts(etSearch.getText().toString());
            });

            categoryContainer.addView(btn);
        }
    }

    private void filterProducts(String query) {
        filteredProducts.clear();
        for (Product p : allProducts) {
            boolean matchCategory = selectedCategory.equals("All") ||
                    p.getCategory().equals(selectedCategory);
            boolean matchSearch = query.isEmpty() ||
                    p.getName().toLowerCase().contains(query.toLowerCase());
            if (matchCategory && matchSearch) filteredProducts.add(p);
        }
        productAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (productAdapter != null) productAdapter.notifyDataSetChanged();
    }
}
