package com.example.application.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.application.R;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@Entity(tableName = "bugs")
public class Bug {
    @PrimaryKey
    @SerializedName("id")
    private final int id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private final String name;

    @SerializedName("appearance")
    @ColumnInfo(name = "appearance")
    private final String appearance;

    @SerializedName("movement")
    @ColumnInfo(name = "movement")
    private final String movement;

    @SerializedName("habitat")
    @ColumnInfo(name = "habitat")
    private final String habitat;

    @SerializedName("color")
    @ColumnInfo(name = "color")
    private final String color;

    @SerializedName("surveyResult")
    @ColumnInfo(name = "surveyResult")
    private final String surveyResult;

    public Bug(
            int id,
            String name,
            String appearance,
            String movement,
            String habitat,
            String color,
            String surveyResult) {
        this.id = id;
        this.name = name;
        this.appearance = appearance;
        this.movement = movement;
        this.habitat = habitat;
        this.color = color;
        this.surveyResult = surveyResult;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAppearance() {
        return this.appearance;
    }

    public String getMovement() {
        return this.movement;
    }

    public String getColor() {
        return this.color;
    }

    public String getHabitat() {
        return this.habitat;
    }

    public String getSurveyResult() {
        return this.surveyResult;
    }

    public int convertToDrawable() {
        switch (this.getId()) {
            case (1):
                return R.drawable.ic_silverfish;
            case (2):
                return R.drawable.ic_dust;
            case (3):
                return R.drawable.ic_cockroach;
            case (4):
                return R.drawable.ic_butterfly;
            case (5):
                return R.drawable.ic_rice;
            case (6):
                return R.drawable.ic_greema;
            case (7):
                return R.drawable.ic_bedbug;
            default:
                return R.drawable.ic_mosquito;
        }
    }

    public int convertToPadding(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, getDrawableDp(this.getId()), context.getResources().getDisplayMetrics());
    }

    private int convertToPadding(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    private float getDrawableDp(int bugId) {
        switch (bugId) {
            case (1):
                return 10F;
            case (2):
            case (5):
                return 12F;
            case (3):
            case (4):
            case (7):
                return 16F;
            default:
                return 14F;
        }
    }

    public TextView[] convertToSurveyResultSentences(Context context) {
        String[] traits = this.getSurveyResult().split(" / ");
        TextView[] results = new TextView[traits.length];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        int index = 0;
        for (String trait : traits) {
            TextView tv = new TextView(context);
            tv.setText(trait);
            tv.setTextSize(14F);
            tv.setTextColor(context.getResources().getColor(R.color.brown_grey, null));
            tv.setLayoutParams(params);
            tv.setPadding(0, 0, 0, convertToPadding(context, 8F));
            results[index++] = tv;
        }

        return results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bug bug = (Bug) o;
        return id == bug.id && Objects.equals(name, bug.name) && Objects.equals(appearance, bug.appearance) && Objects.equals(movement, bug.movement) && Objects.equals(habitat, bug.habitat) && Objects.equals(color, bug.color) && Objects.equals(surveyResult, bug.surveyResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, appearance, movement, habitat, color, surveyResult);
    }
}
