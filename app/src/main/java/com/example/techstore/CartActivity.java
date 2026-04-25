package com.example.techstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerCart;
    LinearLayout emptyCartLayout;
    TextView txtCartGrandTotal;
    Button btnCheckout;
    BottomNavigationView bottomNavCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar toolbarCart = findViewById(R.id.toolbarCart);
        setSupportActionBar(toolbarCart);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        recyclerCart = findViewById(R.id.recyclerCart);
        emptyCartLayout = findViewById(R.id.emptyCartLayout);
        txtCartGrandTotal = findViewById(R.id.txtCartGrandTotal);
        btnCheckout = findViewById(R.id.btnCheckout);
        bottomNavCart = findViewById(R.id.bottomNavCart);

        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerCart.setAdapter(new CartAdapter(this, DataManager.cartItems, DataManager.cartQuantities, this::updateCartUI));

        updateCartUI();

        btnCheckout.setOnClickListener(v -> {
            if (DataManager.cartItems.isEmpty()) {
                new AlertDialog.Builder(this)
                        .setTitle("Cart empty")
                        .setMessage("Please add products before checkout.")
                        .setPositiveButton("OK", null)
                        .show();
            } else {
                startActivity(new Intent(this, OrderConfirmationActivity.class));
            }
        });

        bottomNavCart.setSelectedItemId(R.id.nav_cart);
        bottomNavCart.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (id == R.id.nav_favorites) {
                startActivity(new Intent(this, FavoritesActivity.class));
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return true;
        });
    }

    private void updateCartUI() {
        if (DataManager.cartItems.isEmpty()) {
            emptyCartLayout.setVisibility(android.view.View.VISIBLE);
            recyclerCart.setVisibility(android.view.View.GONE);
        } else {
            emptyCartLayout.setVisibility(android.view.View.GONE);
            recyclerCart.setVisibility(android.view.View.VISIBLE);
        }

        double total = 0;
        for (int i = 0; i < DataManager.cartItems.size(); i++) {
            total += DataManager.cartItems.get(i).getPrice() * DataManager.cartQuantities.get(i);
        }
        txtCartGrandTotal.setText(String.format("%,.0f DA", total));

        if (recyclerCart.getAdapter() != null) {
            recyclerCart.getAdapter().notifyDataSetChanged();
        }
    }
}