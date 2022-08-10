package com.example.application.mypage;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.application.data.Bug;
import com.example.application.data.MyReservation;

public class MyReservationDiffCallback extends DiffUtil.ItemCallback<MyReservation> {
    @Override
    public boolean areItemsTheSame(@NonNull MyReservation oldItem, @NonNull MyReservation newItem) {
        return oldItem.reservationId == newItem.reservationId;
    }

    @Override
    public boolean areContentsTheSame(@NonNull MyReservation oldItem, @NonNull MyReservation newItem) {
        return oldItem.equals(newItem);
    }
}
