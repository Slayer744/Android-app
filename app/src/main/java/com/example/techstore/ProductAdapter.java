package com.example.techstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    public interface OnProductClickListener {
        void onProductClick(Product product);
    }

    private final Context context;
    private final List<Product> productList;
    private final OnProductClickListener listener;

    public ProductAdapter(Context context, List<Product> productList, OnProductClickListener listener) {
        this.context = context;
        this.productList = productList;
        this.listener = listener;
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

        holder.imgProduct.setImageResource(product.getImageResId());
        holder.txtProductName.setText(product.getName());
        holder.txtProductPrice.setText(String.format("%,.0f DA", product.getPrice()));
        holder.txtProductRating.setText(String.valueOf(product.getRating()));

        updateFavoriteIcon(holder.btnFavorite, product.isFavorite());

        holder.btnFavorite.setOnClickListener(v -> {
            if (product.isFavorite()) {
                product.setFavorite(false);
                DataManager.favoriteItems.remove(product);
                Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show();
            } else {
                product.setFavorite(true);
                if (!DataManager.favoriteItems.contains(product)) {
                    DataManager.favoriteItems.add(product);
                }
                Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
            }
            updateFavoriteIcon(holder.btnFavorite, product.isFavorite());
        });

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onProductClick(product);
        });
    }

    private void updateFavoriteIcon(ImageButton btn, boolean isFavorite) {
        btn.setImageResource(isFavorite
                ? R.drawable.ic_favorite
                : R.drawable.ic_favorite_off);
        btn.setColorFilter(isFavorite
                ? 0xFFE91E63
                : 0xFF888888);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtProductName, txtProductPrice, txtProductRating;
        ImageButton btnFavorite;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
            txtProductRating = itemView.findViewById(R.id.txtProductRating);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
        }
    }
}