package com.example.application.product;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.application.data.Company;
import com.example.application.data.Product;

public class ProductDiffCallback extends DiffUtil.ItemCallback<Product> {
    @Override
    public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
        return oldItem.id == newItem.id;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
        return oldItem.isProductInterested == newItem.isProductInterested;
    }
}
