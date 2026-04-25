package com.example.techstore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.txtName.setText(product.getName());
        holder.txtPrice.setText(String.format("%,.0f DA", product.getPrice()));
        holder.txtRating.setText(String.valueOf(product.getRating()));
        holder.imgProduct.setImageResource(product.getImageResId());

        // Favori
        updateFavoriteIcon(holder.btnFavorite, product.isFavorite());
        holder.btnFavorite.setOnClickListener(v -> {
            if (product.isFavorite()) {
                product.setFavorite(false);
                DataManager.favoriteItems.remove(product);
                Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show();
            } else {
                product.setFavorite(true);
                if (!DataManager.favoriteItems.contains(product))
                    DataManager.favoriteItems.add(product);
                Toast.makeText(context, "Added to favorites ❤", Toast.LENGTH_SHORT).show();
            }
            updateFavoriteIcon(holder.btnFavorite, product.isFavorite());
        });

        // Ajouter au panier
        holder.btnAddToCart.setOnClickListener(v -> {
            if (!DataManager.cartItems.contains(product)) {
                DataManager.cartItems.add(product);
                DataManager.cartQuantities.add(1);
                Toast.makeText(context, product.getName() + " added to cart 🛒", Toast.LENGTH_SHORT).show();
            } else {
                int idx = DataManager.cartItems.indexOf(product);
                DataManager.cartQuantities.set(idx, DataManager.cartQuantities.get(idx) + 1);
                Toast.makeText(context, "Quantity updated", Toast.LENGTH_SHORT).show();
            }
        });

        // Ouvrir détail produit
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("productId", product.getId());
            context.startActivity(intent);
        });
    }

    private void updateFavoriteIcon(ImageButton btn, boolean isFav) {
        if (isFav) {
            btn.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            btn.setImageResource(android.R.drawable.btn_star_big_off);
        }
    }

    public void updateList(List<Product> newList) {
        this.productList = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() { return productList.size(); }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        ImageButton btnFavorite;
        TextView txtName, txtPrice, txtRating;
        Button btnAddToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
            txtName = itemView.findViewById(R.id.txtProductName);
            txtPrice = itemView.findViewById(R.id.txtProductPrice);
            txtRating = itemView.findViewById(R.id.txtRating);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}