package com.example.application;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinearLayoutSpacingDecoration extends RecyclerView.ItemDecoration {
    private int halfSpace = 48;

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        parent.setClipToPadding(true);
        outRect.set(0, 0, 0, halfSpace);
    }
}