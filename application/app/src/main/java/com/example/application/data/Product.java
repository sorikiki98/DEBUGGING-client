package com.example.application.data;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.res.ResourcesCompat;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.application.R;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "products")
public class Product {
    @PrimaryKey
    @SerializedName("id")
    public final int id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    public final String name;

    @ColumnInfo(name = "type")
    @SerializedName("type")
    public final String type;

    @ColumnInfo(name = "shortIntro")
    @SerializedName("shortIntro")
    public final String shortIntro;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    public final String description;

    @ColumnInfo(name = "thumbnail")
    @SerializedName("thumbnail")
    public final String thumbnail;

    @ColumnInfo(name = "isProductInterested")
    @SerializedName("isProductInterested")
    public final int isProductInterested;

    @ColumnInfo(name = "numOfInterestedUsers")
    @SerializedName("numOfInterestedUsers")
    public final int numOfInterestedUsers;

    public Product(int id, String name, String type, String shortIntro, String description, String thumbnail, int isProductInterested, int numOfInterestedUsers) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.shortIntro = shortIntro;
        this.description = description;
        this.thumbnail = thumbnail;
        this.isProductInterested = isProductInterested;
        this.numOfInterestedUsers = numOfInterestedUsers;
    }

    public AppCompatButton[] makeTagButton(Context context) {
        String[] tagList = this.type.split(" \\+ ");
        AppCompatButton[] buttons = new AppCompatButton[tagList.length];
        int tagIndex = 0;
        for (String tag : tagList) {
            AppCompatButton button = new AppCompatButton(context);

            button.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.rectangle_r6_with_green_line, null));

            button.setText("#" +  tag); //
            button.setTextSize(16F); //
            button.setTextColor(context.getResources().getColor(R.color.brown_grey, null)); //

            int paddingLength = convertDpToFx(context, 18F);
            button.setPadding(paddingLength, 0, paddingLength, 0); //

            int buttonHeight = convertDpToFx(context, 38F);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, buttonHeight);

            int margin = convertDpToFx(context, 10F);
            params.setMargins(0, margin, margin, margin);
            button.setLayoutParams(params); //

            buttons[tagIndex] = button;
            tagIndex++;
        }

        return buttons;
    }

    public TextView[] makeTextView(Context context) {
        String[] descriptionList = this.description.split(" \\+ ");
        TextView[] textViews = new TextView[descriptionList.length];
        int descriptionIndex = 0;
        for (String description : descriptionList) {
            TextView textView = new TextView(context);
            textView.setText(description);
            textView.setTextSize(16F);
            textView.setTextColor(context.getResources().getColor(R.color.brown_grey, null));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(params);

            int margin = convertDpToFx(context, 12F);
            params.setMargins(0, 0, 0, margin);
            textViews[descriptionIndex] = textView;
            descriptionIndex++;
        }
        return textViews;
    }

    private int convertDpToFx(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }


}
