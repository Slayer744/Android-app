package com.example.techstore;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailActivity extends AppCompatActivity {

    Button btnBack;
    ImageView imgDetailProduct;
    TextView txtDetailCategory, txtDetailName, txtDetailRating, txtDetailPrice, txtDetailDescription, txtQuantityDetail;
    Button btnMinusDetail, btnPlusDetail, detailBottomBar;

    Product selectedProduct;
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        btnBack              = findViewById(R.id.btnBack);
        imgDetailProduct     = findViewById(R.id.imgDetailProduct);
        txtDetailCategory    = findViewById(R.id.txtDetailCategory);
        txtDetailName        = findViewById(R.id.txtDetailName);
        txtDetailRating      = findViewById(R.id.txtDetailRating);
        txtDetailPrice       = findViewById(R.id.txtDetailPrice);
        txtDetailDescription = findViewById(R.id.txtDetailDescription);
        txtQuantityDetail    = findViewById(R.id.txtQuantityDetail);
        btnMinusDetail       = findViewById(R.id.btnMinusDetail);
        btnPlusDetail        = findViewById(R.id.btnPlusDetail);
        detailBottomBar      = findViewById(R.id.detailBottomBar);

        int productId = getIntent().getIntExtra("productId", -1);
        for (Product p : DataManager.getAllProducts()) {
            if (p.getId() == productId) {
                selectedProduct = p;
                break;
            }
        }

        if (selectedProduct != null) {
            imgDetailProduct.setImageResource(selectedProduct.getImageResId());
            txtDetailCategory.setText(selectedProduct.getCategory());
            txtDetailName.setText(selectedProduct.getName());
            txtDetailRating.setText(String.valueOf(selectedProduct.getRating()));
            txtDetailPrice.setText(String.format("%,.0f DA", selectedProduct.getPrice()));
            txtDetailDescription.setText(selectedProduct.getDescription());
        }

        btnBack.setOnClickListener(v -> finish());

        btnMinusDetail.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                txtQuantityDetail.setText(String.valueOf(quantity));
            }
        });

        btnPlusDetail.setOnClickListener(v -> {
            quantity++;
            txtQuantityDetail.setText(String.valueOf(quantity));
        });

        detailBottomBar.setOnClickListener(v -> {
            if (selectedProduct == null) return;
            if (!DataManager.cartItems.contains(selectedProduct)) {
                DataManager.cartItems.add(selectedProduct);
                DataManager.cartQuantities.add(quantity);
            } else {
                int index = DataManager.cartItems.indexOf(selectedProduct);
                int oldQty = DataManager.cartQuantities.get(index);
                DataManager.cartQuantities.set(index, oldQty + quantity);
            }
            Toast.makeText(this, "Product added to cart", Toast.LENGTH_SHORT).show();
        });
    }
}