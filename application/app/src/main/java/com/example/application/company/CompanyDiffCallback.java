package com.example.application.company;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.application.data.Bug;
import com.example.application.data.Company;

public class CompanyDiffCallback extends DiffUtil.ItemCallback<Company> {
    @Override
    public boolean areItemsTheSame(@NonNull Company oldItem, @NonNull Company newItem) {
        return oldItem.id == newItem.id;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Company oldItem, @NonNull Company newItem) {
        return oldItem.isCompanyInterested == newItem.isCompanyInterested;
    }
}
