package com.example.application.mypage;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.application.R;
import com.example.application.data.MyProduct;
import com.example.application.data.MyReservation;
import com.example.application.databinding.ItemMypageProductBinding;
import com.example.application.databinding.ItemMypageReservationBinding;

import java.util.function.Function;

public class MyPageProductInterestListAdapter extends ListAdapter<MyProduct, MyPageProductInterestListAdapter.ViewHolder> {
    private final Function<Integer, Void> itemClickCallback;

    protected MyPageProductInterestListAdapter(@NonNull MyProductDiffCallback diffCallback, @NonNull Function<Integer, Void> itemClickCallback) {
        super(diffCallback);
        this.itemClickCallback = itemClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemMypageProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyProduct item = getCurrentList().get(position);
        holder.bind(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMypageProductBinding binding;

        public ViewHolder(@NonNull ItemMypageProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("ResourceAsColor")
        public void bind(MyProduct myProduct) {
            binding.getRoot().setOnClickListener(view -> itemClickCallback.apply(myProduct.productId));

            Glide.with(binding.getRoot())
                    .load(myProduct.thumbnail)
                    .thumbnail(0.25f)
                    .into(binding.ivProductBg);

            binding.tvNumOfInterests.setText(Integer.toString(myProduct.numOfInterestedUsers));
            binding.tvProductName.setText(myProduct.productName);
        }
    }
}