package com.example.techstore;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final Context context;
    private final List<Product> cartItems;
    private final List<Integer> quantities;
    private final Runnable onCartChanged;

    public CartAdapter(Context context, List<Product> cartItems, List<Integer> quantities, Runnable onCartChanged) {
        this.context = context;
        this.cartItems = cartItems;
        this.quantities = quantities;
        this.onCartChanged = onCartChanged;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartItems.get(position);
        int qty = quantities.get(position);

        holder.imgCartProduct.setImageResource(product.getImageResId());
        holder.txtCartName.setText(product.getName());
        holder.txtCartUnitPrice.setText(String.format("%,.0f DA", product.getPrice()));
        holder.txtCartQuantity.setText(String.valueOf(qty));
        holder.txtCartTotalItem.setText(String.format("%,.0f DA", product.getPrice() * qty));

        holder.btnPlusCart.setOnClickListener(v -> {
            quantities.set(holder.getAdapterPosition(), quantities.get(holder.getAdapterPosition()) + 1);
            notifyItemChanged(holder.getAdapterPosition());
            onCartChanged.run();
        });

        holder.btnMinusCart.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            int currentQty = quantities.get(currentPos);
            if (currentQty > 1) {
                quantities.set(currentPos, currentQty - 1);
                notifyItemChanged(currentPos);
                onCartChanged.run();
            }
        });

        holder.btnDeleteCart.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            new AlertDialog.Builder(context)
                    .setTitle("Remove product")
                    .setMessage("Do you want to remove this product from the cart?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        cartItems.remove(currentPos);
                        quantities.remove(currentPos);
                        notifyItemRemoved(currentPos);
                        onCartChanged.run();
                        Toast.makeText(context, "Product removed", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCartProduct;
        ImageButton btnDeleteCart;
        TextView txtCartName, txtCartUnitPrice, txtCartQuantity, txtCartTotalItem;
        Button btnMinusCart, btnPlusCart;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCartProduct = itemView.findViewById(R.id.imgCartProduct);
            btnDeleteCart = itemView.findViewById(R.id.btnDeleteCart);
            txtCartName = itemView.findViewById(R.id.txtCartName);
            txtCartUnitPrice = itemView.findViewById(R.id.txtCartUnitPrice);
            txtCartQuantity = itemView.findViewById(R.id.txtCartQuantity);
            txtCartTotalItem = itemView.findViewById(R.id.txtCartTotalItem);
            btnMinusCart = itemView.findViewById(R.id.btnMinusCart);
            btnPlusCart = itemView.findViewById(R.id.btnPlusCart);
        }
    }
}