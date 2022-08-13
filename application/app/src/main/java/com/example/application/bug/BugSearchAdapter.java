package com.example.application.bug;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.data.Bug;
import com.example.application.databinding.ItemBugBinding;
import com.example.application.databinding.ItemSearchResultBinding;

import java.util.function.Function;

public class BugSearchAdapter extends ListAdapter<Bug, BugSearchAdapter.ViewHolder> {
    private final Function<Integer, Void> itemClickCallback;

    protected BugSearchAdapter(@NonNull BugDiffCallback diffCallback, @NonNull Function<Integer, Void> itemClickCallback) {
        super(diffCallback);
        this.itemClickCallback = itemClickCallback;
    }

    @NonNull
    @Override
    public BugSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BugSearchAdapter.ViewHolder(ItemSearchResultBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BugSearchAdapter.ViewHolder holder, int position) {
        Bug item = getCurrentList().get(position);
        holder.bind(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemSearchResultBinding binding;

        public ViewHolder(@NonNull ItemSearchResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Bug bug) {
            binding.tvResult.setText(bug.getName());
            binding.getRoot().setOnClickListener(view ->
                    itemClickCallback.apply(bug.getId()));
        }
    }
}
