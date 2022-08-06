package com.example.application.product;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.application.R;
import com.example.application.company.CompanyListAdapter;
import com.example.application.data.Company;
import com.example.application.data.Product;
import com.example.application.databinding.ItemCompanyBinding;
import com.example.application.databinding.ItemProductBinding;

import java.util.function.Function;

public class ProductListAdapter extends ListAdapter<Product, ProductListAdapter.ViewHolder> {
    private final Function<Integer, Void> itemClickCallback;

    private final Function<Integer, Void> itemInterestClickCallback;

    protected ProductListAdapter(@NonNull ProductDiffCallback diffCallback, @NonNull Function<Integer, Void> itemClickCallback, @NonNull Function<Integer, Void> itemInterestClickCallback) {
        super(diffCallback);
        this.itemClickCallback = itemClickCallback;
        this.itemInterestClickCallback = itemInterestClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductListAdapter.ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {
        Product item = getCurrentList().get(position);
        holder.bind(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemProductBinding binding;

        public ViewHolder(@NonNull ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Product product) {
            binding.tvProductTitle.setText(product.name);
            binding.tvProductIntro.setText(product.shortIntro);

            Glide.with(binding.getRoot())
                    .load(product.thumbnail)
                    .into(binding.ivProductThumb);

            if (product.isProductInterested == 1) {
                binding.ibInterest.setBackgroundResource(R.drawable.ic_heart_fill);
            } else {
                binding.ibInterest.setBackgroundResource(R.drawable.ic_heart_line);
            }

            if (getAdapterPosition() % 2 == 0) {
                binding.getRoot().setBackgroundResource(R.color.white_grey);
            } else {
                binding.getRoot().setBackgroundResource(R.color.white);
            }

            binding.getRoot().setOnClickListener(view -> itemClickCallback.apply(product.id));

            binding.ibInterest.setOnClickListener(view -> itemInterestClickCallback.apply(product.id));
        }
    }
}
