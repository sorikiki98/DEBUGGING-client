package com.example.application.bug;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.application.data.Bug;

public class BugsDiffCallback extends DiffUtil.ItemCallback<Bug> {
    @Override
    public boolean areItemsTheSame(@NonNull Bug oldItem, @NonNull Bug newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Bug oldItem, @NonNull Bug newItem) {
        return oldItem.equals(newItem);
    }
}
