package com.example.application.company;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.application.R;
import com.example.application.bug.BugDiffCallback;
import com.example.application.data.Bug;
import com.example.application.data.Company;
import com.example.application.databinding.ItemBugBinding;
import com.example.application.databinding.ItemCompanyBinding;

import java.util.function.Function;

public class CompanyListAdapter extends ListAdapter<Company, CompanyListAdapter.ViewHolder> {
    private final Function<Integer, Void> itemClickCallback;

    private final Function<Integer, Void> itemInterestClickCallback;

    protected CompanyListAdapter(@NonNull CompanyDiffCallback diffCallback, @NonNull Function<Integer, Void> itemClickCallback, @NonNull Function<Integer, Void> itemInterestClickCallback) {
        super(diffCallback);
        this.itemClickCallback = itemClickCallback;
        this.itemInterestClickCallback = itemInterestClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCompanyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Company item = getCurrentList().get(position);
        holder.bind(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCompanyBinding binding;

        public ViewHolder(@NonNull ItemCompanyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Company company) {
            binding.tvCompanyTitle.setText(company.name);
            binding.tvCompanyIntro.setText(company.shortIntro);

            Glide.with(binding.getRoot())
                    .load(company.thumbnail)
                    .into(binding.ivCompanyThumb);

            if (company.isCompanyInterested == 1) {
                binding.ibInterest.setBackgroundResource(R.drawable.ic_heart_fill);
            } else {
                binding.ibInterest.setBackgroundResource(R.drawable.ic_heart_line);
            }

            if (getAdapterPosition() % 2 == 0) {
                binding.getRoot().setBackgroundResource(R.color.white_grey);
            } else {
                binding.getRoot().setBackgroundResource(R.color.white);
            }

            binding.getRoot().setOnClickListener(view -> itemClickCallback.apply(company.id));

            binding.ibInterest.setOnClickListener(view -> itemInterestClickCallback.apply(company.id));
        }
    }
}
