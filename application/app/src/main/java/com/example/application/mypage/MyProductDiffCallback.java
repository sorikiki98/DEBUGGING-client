package com.example.application.mypage;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.application.data.MyProduct;
import com.example.application.data.MyReservation;

public class MyProductDiffCallback extends DiffUtil.ItemCallback<MyProduct> {
    @Override
    public boolean areItemsTheSame(@NonNull MyProduct oldItem, @NonNull MyProduct newItem) {
        return oldItem.productInterestId == newItem.productInterestId;
    }

    @Override
    public boolean areContentsTheSame(@NonNull MyProduct oldItem, @NonNull MyProduct newItem) {
        return oldItem.equals(newItem);
    }
}
