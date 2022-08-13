package com.example.application.product;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.company.CompanyDiffCallback;
import com.example.application.data.Company;
import com.example.application.data.Product;
import com.example.application.databinding.ItemSearchResultBinding;

import java.util.function.Function;

public class ProductSearchAdapter extends ListAdapter<Product, ProductSearchAdapter.ViewHolder> {
    private final Function<Integer, Void> itemClickCallback;

    protected ProductSearchAdapter(@NonNull ProductDiffCallback diffCallback, @NonNull Function<Integer, Void> itemClickCallback) {
        super(diffCallback);
        this.itemClickCallback = itemClickCallback;
    }

    @NonNull
    @Override
    public ProductSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductSearchAdapter.ViewHolder(ItemSearchResultBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSearchAdapter.ViewHolder holder, int position) {
        Product item = getCurrentList().get(position);
        holder.bind(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemSearchResultBinding binding;

        public ViewHolder(@NonNull ItemSearchResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Product product) {
            binding.tvResult.setText(product.name);
            binding.getRoot().setOnClickListener(view ->
                    itemClickCallback.apply(product.id));
        }
    }
}
