package com.example.techstore;

import android.content.Context;
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

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private final Context context;
    private final List<Product> favoriteItems;
    private final Runnable onFavoritesChanged;

    public FavoriteAdapter(Context context, List<Product> favoriteItems, Runnable onFavoritesChanged) {
        this.context = context;
        this.favoriteItems = favoriteItems;
        this.onFavoritesChanged = onFavoritesChanged;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Product product = favoriteItems.get(position);

        holder.imgFavoriteProduct.setImageResource(product.getImageResId());
        holder.txtFavoriteName.setText(product.getName());
        holder.txtFavoritePrice.setText(String.format("%,.0f DA", product.getPrice()));

        holder.btnRemoveFavorite.setOnClickListener(v -> {
            product.setFavorite(false);
            favoriteItems.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
            Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show();
            onFavoritesChanged.run();
        });

        holder.btnFavoriteAddToCart.setOnClickListener(v -> {
            if (!DataManager.cartItems.contains(product)) {
                DataManager.cartItems.add(product);
                DataManager.cartQuantities.add(1);
            } else {
                int index = DataManager.cartItems.indexOf(product);
                DataManager.cartQuantities.set(index, DataManager.cartQuantities.get(index) + 1);
            }
            Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return favoriteItems.size();
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFavoriteProduct;
        ImageButton btnRemoveFavorite;
        TextView txtFavoriteName, txtFavoritePrice;
        Button btnFavoriteAddToCart;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFavoriteProduct = itemView.findViewById(R.id.imgFavoriteProduct);
            btnRemoveFavorite = itemView.findViewById(R.id.btnRemoveFavorite);
            txtFavoriteName = itemView.findViewById(R.id.txtFavoriteName);
            txtFavoritePrice = itemView.findViewById(R.id.txtFavoritePrice);
            btnFavoriteAddToCart = itemView.findViewById(R.id.btnFavoriteAddToCart);
        }
    }
}