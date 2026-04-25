package com.example.techstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrderConfirmationActivity extends AppCompatActivity {

    LinearLayout orderSummaryContainer;
    TextView txtOrderTotal;
    ImageButton btnOpenMap, btnOpenPhone, btnOpenFacebook, btnOpenInstagram;
    Button btnPlaceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        orderSummaryContainer = findViewById(R.id.orderSummaryContainer);
        txtOrderTotal         = findViewById(R.id.txtOrderTotal);
        btnOpenMap            = findViewById(R.id.btnOpenMap);
        btnOpenPhone          = findViewById(R.id.btnOpenPhone);
        btnOpenFacebook       = findViewById(R.id.btnOpenFacebook);
        btnOpenInstagram      = findViewById(R.id.btnOpenInstagram);
        btnPlaceOrder         = findViewById(R.id.btnPlaceOrder);

        loadSummary();

        btnOpenMap.setOnClickListener(v -> {
            String mapUrl = "geo:0,0?q=Ain+Benian+Alger+Algerie";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl)));
        });

        btnOpenPhone.setOnClickListener(v -> {
            String phone = "tel:0794081373"; // ← remplace par ton numéro
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(phone)));
        });

        btnOpenFacebook.setOnClickListener(v -> {
            String url = "https://www.facebook.com/VotrePage";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        });

        btnOpenInstagram.setOnClickListener(v -> {
            String url = "https://www.instagram.com/slayer_ry";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        });

        btnPlaceOrder.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Confirm purchase")
                    .setMessage("Are you sure you want to place this order?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        DataManager.cartItems.clear();
                        DataManager.cartQuantities.clear();
                        startActivity(new Intent(this, ThankYouActivity.class));
                        finish();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    private void loadSummary() {
        orderSummaryContainer.removeAllViews();
        double total = 0;

        for (int i = 0; i < DataManager.cartItems.size(); i++) {
            Product product = DataManager.cartItems.get(i);
            int qty = DataManager.cartQuantities.get(i);
            double subtotal = product.getPrice() * qty;
            total += subtotal;

            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setPadding(0, 10, 0, 10);

            TextView left = new TextView(this);
            left.setLayoutParams(new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            left.setText(product.getName() + " x" + qty);
            left.setTextSize(18f);
            left.setTextColor(0xFF10233D);

            TextView right = new TextView(this);
            right.setText(String.format("%,.0f DA", subtotal));
            right.setTextSize(18f);
            right.setTextColor(0xFF10233D);
            right.setTypeface(null, android.graphics.Typeface.BOLD);

            row.addView(left);
            row.addView(right);
            orderSummaryContainer.addView(row);
        }

        txtOrderTotal.setText(String.format("%,.0f DA", total));
    }
}