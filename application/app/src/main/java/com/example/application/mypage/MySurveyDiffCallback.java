package com.example.application.mypage;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.application.data.MySurvey;

public class MySurveyDiffCallback extends DiffUtil.ItemCallback<MySurvey> {
    @Override
    public boolean areItemsTheSame(@NonNull MySurvey oldItem, @NonNull MySurvey newItem) {
        return oldItem.surveyId == newItem.surveyId;
    }

    @Override
    public boolean areContentsTheSame(@NonNull MySurvey oldItem, @NonNull MySurvey newItem) {
        return oldItem.equals(newItem);
    }
}