package com.example.application.company;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.bug.BugDiffCallback;
import com.example.application.data.Bug;
import com.example.application.data.Company;
import com.example.application.databinding.ItemSearchResultBinding;

import java.util.function.Function;

public class CompanySearchAdapter extends ListAdapter<Company, CompanySearchAdapter.ViewHolder> {
    private final Function<Integer, Void> itemClickCallback;

    protected CompanySearchAdapter(@NonNull CompanyDiffCallback diffCallback, @NonNull Function<Integer, Void> itemClickCallback) {
        super(diffCallback);
        this.itemClickCallback = itemClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemSearchResultBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Company item = getCurrentList().get(position);
        holder.bind(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemSearchResultBinding binding;

        public ViewHolder(@NonNull ItemSearchResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Company company) {
            binding.tvResult.setText(company.name);
            binding.getRoot().setOnClickListener(view ->
                    itemClickCallback.apply(company.id));
        }
    }
}
