package com.example.application.bug;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.data.Bug;
import com.example.application.databinding.ItemBugBinding;

import java.util.function.Function;

public class BugListAdapter extends ListAdapter<Bug, BugListAdapter.ViewHolder> {
    private final Function<Integer, Void> itemClickCallback;

    protected BugListAdapter(@NonNull BugDiffCallback diffCallback, @NonNull Function<Integer, Void> itemClickCallback) {
        super(diffCallback);
        this.itemClickCallback = itemClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBugBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bug item = getCurrentList().get(position);
        holder.bind(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemBugBinding binding;

        public ViewHolder(@NonNull ItemBugBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Bug bug) {
            binding.btnBugItem.setText(bug.getName());
            binding.getRoot().setOnClickListener(view -> {
                itemClickCallback.apply(bug.getId());
            });
        }
    }
}
