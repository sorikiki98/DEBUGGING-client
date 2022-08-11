package com.example.application.mypage;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.application.data.MyProduct;
import com.example.application.data.MySurvey;
import com.example.application.databinding.ItemMypageSurveyBinding;

import java.util.function.Function;

public class MyPageSurveyDetailListAdapter extends ListAdapter<MySurvey, MyPageSurveyDetailListAdapter.ViewHolder> {
    private final Function<Integer, Void> itemClickCallback;

    protected MyPageSurveyDetailListAdapter(@NonNull MySurveyDiffCallback diffCallback, @NonNull Function<Integer, Void> itemClickCallback) {
        super(diffCallback);
        this.itemClickCallback = itemClickCallback;
    }

    @NonNull
    @Override
    public MyPageSurveyDetailListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyPageSurveyDetailListAdapter.ViewHolder(ItemMypageSurveyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyPageSurveyDetailListAdapter.ViewHolder holder, int position) {
        MySurvey item = getCurrentList().get(position);
        holder.bind(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMypageSurveyBinding binding;

        public ViewHolder(@NonNull ItemMypageSurveyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("ResourceAsColor")
        public void bind(MySurvey mySurvey) {
            binding.getRoot().setOnClickListener(view -> itemClickCallback.apply(mySurvey.bugId));
            binding.ivBugBg.setImageResource(mySurvey.convertToDrawable());
            binding.tvBugName.setText(mySurvey.bugName);
            binding.tvSurveyDate.setText(mySurvey.surveyDate.substring(0, 10));
        }
    }
}